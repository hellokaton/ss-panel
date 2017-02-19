package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.DateKit;
import com.blade.kit.EncrypKit;
import com.blade.kit.PatternKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.sp.ext.Result;
import com.sp.model.PasswordReset;
import com.sp.model.User;
import com.sp.service.PasswordResetService;
import com.sp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("password")
public class PasswordController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

    @Inject
    private UserService userService;

    @Inject
    private PasswordResetService passwordResetService;

    @Route(value = "reset", method = HttpMethod.GET)
    public String reset() {
        return this.render("password/reset");
    }

    @Route(value = "reset", method = HttpMethod.POST)
    @JSON
    public Result handleReset(@QueryParam String email) {
        if (!PatternKit.isEmail(email)) {
            return Result.fail("错误的邮箱格式");
        }
        User user = userService.byEmail(email);
        if (null == user) {
            return Result.fail("此邮箱不存在.");
        }
        try {
            passwordResetService.sendResetEmail(email);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Result.fail("重置密码失败");
        }
        return Result.ok("重置邮件已经发送,请检查邮箱.");
    }

    @Route(value = "token", method = HttpMethod.GET)
    public String token(@QueryParam String c, Request request) {
        request.attribute("token", c);
        return this.render("password/token");
    }

    @Route(value = "token", method = HttpMethod.POST)
    @JSON
    public Result handleToken(@QueryParam String token,
                              @QueryParam String password) {

        PasswordReset passwordReset = passwordResetService.byToken(token);
        if (null == passwordReset || passwordReset.getExpire_time() < DateKit.getCurrentUnixTime()) {
            return Result.fail("链接已经失效,请重新获取");
        }

        User user = userService.byEmail(passwordReset.getEmail());
        if (null == user) {
            return Result.fail("链接已经失效,请重新获取");
        }

        if(StringKit.isBlank(password)){
            return Result.fail("请输入密码");
        }

        if(password.length() < 6){
            return Result.fail("密码太短了");
        }

        String pwd = EncrypKit.md5(password);
        User temp = new User();
        temp.setId(user.getId());
        temp.setPass(pwd);
        userService.update(temp);

        return Result.ok("重置成功");
    }

}
