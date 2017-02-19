package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.EncrypKit;
import com.blade.kit.PatternKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.QueryParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.wrapper.Session;
import com.sp.ext.Functions;
import com.sp.model.InviteCode;
import com.sp.ext.Result;
import com.sp.model.User;
import com.sp.service.ConfigService;
import com.sp.service.EmailVerifyService;
import com.sp.service.InviteCodeService;
import com.sp.service.UserService;
import com.sp.utils.SessionUtils;
import com.sp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("auth")
public class AuthController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    // Register Error Code
    private final int WrongCode = 501;
    private final int IllegalEmail = 502;
    private final int PasswordTooShort = 511;
    private final int PasswordNotEqual = 512;
    private final int EmailUsed = 521;

    // Login Error Code
    private final int UserNotExist = 601;
    private final int UserPasswordWrong = 602;

    // Verify Email
    private final int VerifyEmailWrongEmail = 701;
    private final int VerifyEmailExist = 702;

    @Inject
    private UserService userService;

    @Inject
    private ConfigService configService;

    @Inject
    private InviteCodeService inviteCodeService;

    @Inject
    private EmailVerifyService emailVerifyService;

    /**
     * 登录页面
     * @return
     */
    @Route(value = "login", method = HttpMethod.GET)
    public String login() {
        return this.render("auth/login");
    }

    @Route(value = "login", method = HttpMethod.POST)
    @JSON
    public Result doLogin(@QueryParam String email, @QueryParam String passwd,
                          @QueryParam String remember_me, Request request, Response response) {
        email = email.toLowerCase();
        User user = userService.byEmail(email);

        if(null == user){
            return Result.fail(UserNotExist, "邮箱或者密码错误");
        }

        if(!EncrypKit.md5(passwd).equals(user.getPass())){
            return Result.fail(UserPasswordWrong, "邮箱或者密码错误");
        }
        int time = 3600 * 24;
        if(StringKit.isNotBlank(remember_me)){
            time = 3600 * 24 * 7;
        }
        LOGGER.info("login user {}", user.getId());
        SessionUtils.setLoginUser(request.session(), user);
        SessionUtils.setLoginCookie(response, user.getId(), time);

        return Result.ok("欢迎回来");
    }

    /**
     * 注册页面
     * @return
     */
    @Route(value = "register", method = HttpMethod.GET)
    public String register(@QueryParam String code, Request request) {
        String requireEmailVerification = Functions.config("emailVerifyEnabled");
        request.attribute("code", code);
        request.attribute("requireEmailVerification", requireEmailVerification);
        return this.render("auth/register");
    }

    @Route(value = "register", method = HttpMethod.POST)
    @JSON
    public Result doRegister(@QueryParam String name, @QueryParam String email,
                             @QueryParam String passwd,@QueryParam String repasswd,
                             @QueryParam String code, @QueryParam String verifycode,
                             Request request, Response response) {
        email = email.toLowerCase();

        InviteCode c = inviteCodeService.byCode(code);
        if(null == c){
            return Result.fail(WrongCode, "邀请码无效");
        }

        if(!PatternKit.isEmail(email)){
            return Result.fail(IllegalEmail, "邮箱无效");
        }

        if(passwd.length() < 8){
            return Result.fail(PasswordTooShort, "密码太短");
        }

        if(!passwd.equals(repasswd)){
            return Result.fail(PasswordNotEqual, "两次密码输入不符");
        }

        User user = userService.byEmail(email);
        if(null != user){
            return Result.fail(EmailUsed, "邮箱已经被注册了");
        }

        if(Functions.config("emailVerifyEnabled").equals("true") &&
                !emailVerifyService.checkVerifyCode(email, verifycode)){
            return Result.fail("邮箱验证代码不正确");
        }

        String ip = request.address();
        int ipRegCount = userService.getIpRegCount(ip);
        if(ipRegCount >= Integer.valueOf(Functions.config("ipDayLimit"))){
            return Result.fail("当前IP注册次数超过限制");
        }
        User temp = new User();
        temp.setUser_name(name);
        temp.setEmail(email);
        temp.setPass(EncrypKit.md5(passwd));
        temp.setPasswd(StringKit.getRandomChar(6));
        temp.setPort(userService.getLastPort() + 1);
        temp.setT(0);
        temp.setU(0);
        temp.setD(0);
        temp.setTransfer_enable(Utils.toGB(Integer.valueOf(Functions.config("defaultTraffic"))));
        temp.setInvite_num(Integer.valueOf(Functions.config("inviteNum")));
        temp.setReg_ip(ip);
        temp.setRef_by(c.getUser_id());

        try {
            userService.save(temp);
            inviteCodeService.delete(c.getId());
        } catch (Exception e){
            LOGGER.error("注册失败", e);
            return Result.fail("注册失败");
        }
        return Result.ok("注册成功");
    }

    @Route(value = "sendcode", method = HttpMethod.POST)
    @JSON
    public Result sendVerifyEmail(@QueryParam String email, Request request, Response response) {
        email = email.toLowerCase();

        if(!PatternKit.isEmail(email)){
            return Result.fail(VerifyEmailWrongEmail, "邮箱无效");
        }

        User user = userService.byEmail(email);
        if(null != user){
            return Result.fail(VerifyEmailExist, "邮箱已经被注册了");
        }

        try {
            emailVerifyService.sendVerification(email);
        } catch (Exception e){
            LOGGER.error("邮件发送失败", e);
            return Result.fail("邮件发送失败，请联系管理员");
        }
        return Result.ok("验证代码已发送至您的邮箱，请在登录邮箱后将验证码填到相应位置.");
    }

    @Route(value = "logout", method = HttpMethod.GET)
    public void logout(Request request, Response response) {
        Session session = request.session();
        SessionUtils.removeUser(session);
        SessionUtils.removeCookie(response);
        response.go("/auth/login");
    }

}
