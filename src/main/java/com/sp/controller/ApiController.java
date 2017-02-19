package com.sp.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.sp.ext.Result;
import com.sp.model.Node;
import com.sp.model.Token;
import com.sp.model.User;
import com.sp.service.NodeService;
import com.sp.service.TokenService;
import com.sp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by biezhi on 2017/2/19.
 */
@RestController("api")
public class ApiController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @Inject
    private TokenService tokenService;

    @Inject
    private UserService userService;

    @Inject
    private NodeService nodeService;

    @Route(value = "token", method = HttpMethod.GET)
    public Result token(@QueryParam String token) {
        Token t = tokenService.byToken(token);
        if(null == t){
            return Result.fail("token is null");
        }
        return Result.ok("ok").setData(token);
    }

    @Route(value = "token", method = HttpMethod.POST)
    public Result newToken(@QueryParam String email,
                           @QueryParam String passwd) {

        if(StringKit.isBlank(email)){
            return Result.fail("邮箱不能为空");
        }

        User user = userService.byEmail(email);
        if(null == user){
            return Result.fail("401 邮箱或者密码错误");
        }

        if(!user.getPass().equals(EncrypKit.md5(passwd))){
            return Result.fail("402 邮箱或者密码错误");
        }

        try {
            String token = tokenService.create(user.getId());
            return Result.ok("ok").setData(token);
        } catch (Exception e){
            LOGGER.error("创建token失败", e);
            return Result.fail("system error");
        }
    }

    @Route(value = "node", method = HttpMethod.GET)
    public Result node() {
        List<Node> nodes = nodeService.getNodes(new Take(Node.class).eq("type", 1).orderby("sort desc"));
        return Result.ok("ok").setData(nodes);
    }

    @Route(value = "user/:id", method = HttpMethod.GET)
    public Result userInfo(@PathParam int id, Request request) {
        String token = request.header("token");
        if(StringKit.isBlank(token)){
            token = request.query("access_token");
        }
        Token t = tokenService.byToken(token);
        if(null == t || !t.getUser_id().equals(id)){
            return Result.fail("access denied");
        }
        User user = userService.getUserById(t.getUser_id());
        return Result.ok("ok").setData(user);
    }

}