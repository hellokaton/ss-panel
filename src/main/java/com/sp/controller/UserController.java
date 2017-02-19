package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.kit.DateKit;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import com.blade.kit.json.JSONKit;
import com.blade.kit.json.JSONObject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.http.wrapper.Session;
import com.sp.config.SpConst;
import com.sp.dto.LoginUser;
import com.sp.ext.Functions;
import com.sp.ext.Result;
import com.sp.model.InviteCode;
import com.sp.model.Node;
import com.sp.model.TrafficLog;
import com.sp.model.User;
import com.sp.service.*;
import com.sp.utils.SessionUtils;
import com.sp.utils.Utils;

import java.util.Base64;
import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller(value = "user")
public class UserController extends BaseController {

    @Inject
    private ConfigService configService;

    @Inject
    private NodeService nodeService;

    @Inject
    private InviteCodeService inviteCodeService;

    @Inject
    private UserService userService;

    @Inject
    private TrafficLogService trafficLogService;

    @Inject
    private CheckinLogService checkinLogService;

    @Route(value = "/", method = HttpMethod.GET)
    public String index(Request request) {
        String msg = configService.getConfig("user-index");
        if(StringKit.isBlank(msg)){
            msg = "在后台修改用户中心公告...";
        }
        request.attribute("msg", msg);
        return this.render("user/index");
    }

    @Route(value = "node", method = HttpMethod.GET)
    public String node(Request request) {
        String msg = configService.getConfig("user-node");
        List<Node> nodes = nodeService.getNodes(new Take(Node.class).eq("type", 1).orderby("sort desc"));

        request.attribute("msg", msg);
        request.attribute("nodes", nodes);
        return this.render("user/node");
    }

    @Route(value = "node/:id", method = HttpMethod.GET)
    public String nodeInfo(@PathParam Integer id, Request request) {
        Node node = nodeService.byId(id);
        User user = this.user();
        if(null == node){
            return "";
        }
        JSONObject ary = new JSONObject();
        ary.put("server", node.getServer());
        ary.put("server_port", user.getPort());
        ary.put("password", user.getPasswd());
        ary.put("method", node.getMethod());
        if(node.getCustom_method()){
            ary.put("method", user.getMethod());
        }

        String json = ary.toString();
        String baseUrl = "";
        String ssurl = ary.getString("method") + ":" + ary.getString("password") + "@" + ary.getString("server") + ":" + ary.getString("server_port");
        String ssqr = "ss://" + Base64.getEncoder().encodeToString(ssurl.getBytes());

        String surge_base = Functions.config("baseUrl");
        String surge_proxy = "#!PROXY-OVERRIDE:ProxyBase.conf\\n" +
                "[Proxy]\\n" +
                "Proxy = custom," + ary.getString("server") + "," +
                ary.getString("server_port") + "," + ary.getString("method") + "," +
                ary.getString("password") + "," + baseUrl + "/downloads/SSEncrypt.module";

        request.attribute("json", json);
        request.attribute("json_show", JSONKit.toJSONString(ary, true));
        request.attribute("ssqr", ssqr);
        request.attribute("surge_base", surge_base);
        request.attribute("surge_proxy", surge_proxy);

        return this.render("user/nodeinfo");
    }

    @Route(value = "profile", method = HttpMethod.GET)
    public String profile() {
        return this.render("user/profile");
    }

    @Route(value = "invite", method = HttpMethod.GET)
    public String invite(Request request) {
        User user = this.user();
        List<InviteCode> codes = inviteCodeService.getCodes(new Take(InviteCode.class).eq("user_id", user.getId()));
        request.attribute("codes", codes);
        return this.render("user/invite");
    }

    @Route(value = "invite", method = HttpMethod.POST)
    @JSON
    public Result doInvite(Request request) {
        User user = this.user();
        int num = request.queryInt("num");
        if(num < 1 || num > user.getInvite_num()){
            return Result.fail();
        }
        inviteCodeService.saveCodes(user.getId(), num, "");
        user.setInvite_num( user.getInvite_num() - num );
        return Result.ok();
    }

    @Route(value = "edit", method = HttpMethod.GET)
    public String edit(Request request) {
        request.attribute("method", SpConst.METHODS);
        return this.render("user/edit");
    }

    @Route(value = "password", method = HttpMethod.POST)
    @JSON
    public Result updatePassword(@QueryParam String oldpwd,
                                 @QueryParam String pwd,
                                 @QueryParam String repwd) {

        User user = this.user();
        if(StringKit.isBlank(oldpwd) || !EncrypKit.md5(oldpwd).equals(user.getPass())){
            return Result.fail("旧密码错误");
        }

        if(StringKit.isBlank(pwd)){
            return Result.fail("请输入新密码");
        }

        if(pwd.length() < 8){
            return Result.fail("密码太短啦");
        }

        if(!pwd.equals(repwd)){
            return Result.fail("两次输入不符合");
        }

        String hashPwd = EncrypKit.md5(pwd);
        User temp = new User();
        temp.setId(user.getId());
        temp.setPass(hashPwd);
        userService.update(temp);
        return Result.ok("密码修改成功");
    }

    @Route(value = "sspwd", method = HttpMethod.POST)
    @JSON
    public Result updateSsPwd(Request request) {
        User user = this.user();
        String pwd = request.query("sspwd");
        if(StringKit.isBlank(pwd)){
            pwd = StringKit.getRandomChar(8);
        } else if(pwd.length() < 5){
            return Result.fail("密码要大于4位或者留空生成随机密码");
        }

        User temp = new User();
        temp.setId(user.getId());
        temp.setPasswd(pwd);
        userService.update(temp);
        user.setPasswd(pwd);

        return Result.ok("新密码为: " + pwd);
    }

    @Route(value = "method", method = HttpMethod.POST)
    @JSON
    public Result updateMethod(Request request) {
        User user = this.user();
        String method = request.query("method").toLowerCase();

        User temp = new User();
        temp.setId(user.getId());
        temp.setMethod(method);
        userService.update(temp);
        return Result.ok();
    }

    @Route(value = "sys", method = HttpMethod.GET)
    public String sys(Request request) {
        request.attribute("ana", "");
        return this.render("user/sys");
    }

    @Route(value = "trafficlog", method = HttpMethod.GET)
    public String trafficlog(Request request) {
        int page = request.queryInt("page", 1);
        User user = this.user();
        Paginator<TrafficLog> paginator = trafficLogService.getLogs(new Take(TrafficLog.class).eq("user_id", user.getId()).orderby("id desc").page(page, 15));
        request.attribute("logs", paginator);
        return this.render("user/sys");
    }

    @Route(value = "kill", method = HttpMethod.GET)
    public String kill() {
        return this.render("user/kill");
    }

    @Route(value = "kill", method = HttpMethod.POST)
    @JSON
    public Result handleKill(Request request) {
        User user = this.user();
        String passwd = request.query("passwd");
        return Result.ok();
    }

    @Route(value = "logout", method = HttpMethod.GET)
    public void logout(Request request, Response response) {
        Session session = request.session();
        SessionUtils.removeUser(session);
        SessionUtils.removeCookie(response);
        response.go("/auth/login");
    }

    @Route(value = "checkin", method = HttpMethod.POST)
    @JSON
    public Result doCheckin(Request request) {
        LoginUser user = this.user();
        if(!user.isAbleToCheckin()){
            return Result.ok("您似乎已经签到过了...");
        }
        int traffic = Utils.rand(Integer.valueOf(Functions.config("checkinMin")), Integer.valueOf(Functions.config("checkinMax")));
        int trafficToAdd = Utils.toMB(traffic);
        user.setTransfer_enable(user.getTransfer_enable() + trafficToAdd);
        User temp = new User();
        temp.setId(user.getId());
        temp.setTransfer_enable(user.getTransfer_enable() + trafficToAdd);
        temp.setLast_check_in_time(DateKit.getCurrentUnixTime());
        userService.update(temp);

        checkinLogService.save(user.getId(), trafficToAdd);

        String msg = String.format("获得了 %s MB流量.", trafficToAdd);
        return Result.ok(msg);
    }

}
