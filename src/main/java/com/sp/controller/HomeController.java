package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.sp.model.InviteCode;
import com.sp.service.ConfigService;
import com.sp.service.InviteCodeService;

import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller
public class HomeController extends BaseController {

    @Inject
    private ConfigService configService;

    @Inject
    private InviteCodeService inviteCodeService;

    @Route(value = {"/", "/index"}, method = HttpMethod.GET)
    public String index(Request request) {
        String homeIndexMsg = configService.getConfig("home-index");
        request.attribute("homeIndexMsg", homeIndexMsg);
        return this.render("index");
    }

    @Route(value = "code", method = HttpMethod.GET)
    public String code(Request request) {
        String msg = configService.getConfig("home-code");
        List<InviteCode> codes = inviteCodeService.getCodes(new Take(InviteCode.class).eq("user_id", 0).orderby("id desc limit 10"));
        request.attribute("msg", msg);
        request.attribute("codes", codes);
        return this.render("code");
    }

    @Route(value = "tos", method = HttpMethod.GET)
    public String tos() {
        return this.render("tos");
    }

}
