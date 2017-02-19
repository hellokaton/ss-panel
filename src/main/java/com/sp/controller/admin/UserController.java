package com.sp.controller.admin;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.sp.controller.BaseController;
import com.sp.ext.Result;
import com.sp.model.User;
import com.sp.service.UserService;
import com.sp.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by biezhi on 2017/2/19.
 */
@Controller("admin")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    @Route(value = "user", method = HttpMethod.GET)
    public String index(Request request) {
        int page = request.queryInt("page", 1);
        Paginator<User> users = userService.getUsers(new Take(User.class).page(page, 15));
        request.attribute("users", users);
        return this.render("admin/user/index");
    }

    @Route(value = "user/:id/edit", method = HttpMethod.GET)
    public String edit(@PathParam int id, Request request) {
        User user = userService.getUserById(id);
        request.attribute("user", user);
        return this.render("admin/user/edit");
    }

    @Route(value = "user/:id", method = HttpMethod.PUT)
    @JSON
    public Result update(@PathParam Integer id,
                         @QueryParam String user_name, @QueryParam String email,
                         @QueryParam String pass, @QueryParam String passwd,
                         @QueryParam int port, @QueryParam int transfer_enable,
                         @QueryParam int invite_num, @QueryParam String method,
                         @QueryParam int enable, @QueryParam int is_admin,
                         @QueryParam int ref_by) {

        User user = new User();
        user.setId(id);
        user.setUser_name(user_name);
        user.setEmail(email);
        if (StringKit.isNotBlank(pass)) {
            user.setPass(EncrypKit.md5(pass));
        }
        if (StringKit.isNotBlank(passwd)) {
            user.setPasswd(passwd);
        }
        user.setPort(port);
        user.setTransfer_enable(Utils.toGB(transfer_enable));
        user.setInvite_num(invite_num);
        user.setEnable(enable);
        user.setMethod(method);
        user.setIs_admin(is_admin);
        user.setRef_by(ref_by);
        try {
            userService.update(user);
        } catch (Exception e) {
            LOGGER.error("修改用户失败", e);
            return Result.fail("修改失败");
        }
        return Result.ok("修改成功");
    }

    @Route(value = "user/:id", method = HttpMethod.DELETE)
    @JSON
    public Result delete(@PathParam Integer id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除用户失败", e);
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }

    @Route(value = "user/:id/delete", method = HttpMethod.GET)
    public void deleteGet(@PathParam Integer id, Response response){
        try {
            userService.delete(id);
            response.go("/admin/user");
        } catch (Exception e){
            LOGGER.error("删除用户失败", e);
        }
    }

}
