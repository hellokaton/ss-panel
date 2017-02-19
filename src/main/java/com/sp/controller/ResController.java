package com.sp.controller;

import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.PathParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.patchca.DefaultPatchca;
import com.blade.patchca.Patchca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("res")
public class ResController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResController.class);

    private Patchca patchca = new DefaultPatchca();

    @Route(value = "captcha/:id", method = HttpMethod.GET)
    public void captcha(Request request, Response response){
        try {
            patchca.render(request, response);
        } catch (Exception e) {
            LOGGER.error("获取验证码失败", e);
        }
    }

}
