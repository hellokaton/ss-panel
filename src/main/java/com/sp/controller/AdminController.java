package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.QueryParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.sp.dto.Analytics;
import com.sp.ext.Result;
import com.sp.model.CheckinLog;
import com.sp.model.InviteCode;
import com.sp.model.TrafficLog;
import com.sp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("admin")
public class AdminController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Inject
    private TrafficLogService trafficLogService;

    @Inject
    private CheckinLogService checkinLogService;

    @Inject
    private ConfigService configService;

    @Inject
    private AdminService adminService;

    @Inject
    private InviteCodeService inviteCodeService;

    @Route(value = "/", method = HttpMethod.GET)
    public String index(Request request) {
        Analytics sts = adminService.getAnalytics(3600);
        request.attribute("sts", sts);
        return this.render("admin/index");
    }

    @Route(value = "trafficlog", method = HttpMethod.GET)
    public String trafficlog(Request request) {
        int page = request.queryInt("page", 1);
        Paginator<TrafficLog> paginator = trafficLogService.getLogs(new Take(TrafficLog.class).orderby("id desc").page(page, 15));
        request.attribute("logs", paginator);
        return this.render("admin/trafficlog");
    }

    @Route(value = "checkinlog", method = HttpMethod.GET)
    public String checkinlog(Request request) {
        int page = request.queryInt("page", 1);
        Paginator<CheckinLog> paginator = checkinLogService.getLogs(new Take(CheckinLog.class).orderby("id desc").page(page, 15));
        request.attribute("logs", paginator);
        return this.render("admin/checkinlog");
    }

    @Route(value = "config", method = HttpMethod.GET)
    public String config(Request request) {
        Map<String, String> config = configService.getConfigs("app_name", "home_code", "analytics_code",
                "user_index", "user_node");
        request.attribute("config", config);
        return this.render("admin/config");
    }

    @Route(value = "config", method = HttpMethod.PUT)
    @JSON
    public Result updateConfig(@QueryParam String analyticsCode,
                               @QueryParam String homeCode,
                               @QueryParam String appName,
                               @QueryParam String userIndex,
                               @QueryParam String userNode) {

        configService.updateByKey("analytics_code", analyticsCode);
        configService.updateByKey("home_code", homeCode);
        configService.updateByKey("app_name", appName);
        configService.updateByKey("user_index", userIndex);
        configService.updateByKey("user_node", userNode);
        return Result.ok("更新成功");
    }

    @Route(value = "invite", method = HttpMethod.GET)
    public String invite(Request request) {
        List<InviteCode> codes = inviteCodeService.getCodes(new Take(InviteCode.class).eq("user_id", 0));
        request.attribute("codes", codes);
        return this.render("admin/invite");
    }

    @Route(value = "invite", method = HttpMethod.POST)
    @JSON
    public Result addInvite(@QueryParam int num, @QueryParam String prefix, @QueryParam int uid) {
        if (num < 1) {
            return Result.fail();
        }
        try {
            inviteCodeService.saveCodes(uid, num, prefix);
        } catch (Exception e) {
            LOGGER.error("邀请码添加失败", e);
            return Result.fail("邀请码添加失败");
        }
        return Result.ok("邀请码添加成功");
    }

}
