package com.sp.controller.admin;

import com.blade.kit.DateKit;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.QueryParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.sp.config.SpConst;
import com.sp.controller.BaseController;
import com.sp.ext.Envelope;
import com.sp.ext.Result;
import com.sp.model.Node;
import com.sp.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("admin")
public class TestController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Route(value = "test/sendmail", method = HttpMethod.GET)
    public String sendmail(){
        return this.render("admin/test/sendmail");
    }

    @Route(value = "test/sendmail", method = HttpMethod.POST)
    @JSON
    public Result sendmailPost(@QueryParam String email){
        Map<String, Object> ary = new HashMap<>();
        ary.put("time", DateKit.getToday("yyyy-MM-dd HH:mm:ss"));
        ary.put("appName", SpConst.config.get("app.appName"));
        try {
            Envelope envelope = new Envelope(email, "/email/test.html", "Test", ary);
            EmailUtils.send(envelope);
        } catch (Exception e){
            LOGGER.error("邮件发送失败", e);
            return Result.fail("发送失败");
        }
        return Result.ok("发送成功");
    }

}
