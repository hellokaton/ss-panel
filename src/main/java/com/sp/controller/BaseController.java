package com.sp.controller;

import com.sp.dto.LoginUser;
import com.sp.utils.SessionUtils;

/**
 * Created by biezhi on 2017/2/19.
 */
public abstract class BaseController {

    public static String THEME = "default";

    protected String render(String viewName) {
        return THEME + "/" + viewName;
    }

    protected LoginUser user(){
        return SessionUtils.getLoginUser();
    }

}
