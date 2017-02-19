package com.sp.config;

import com.blade.kit.base.Config;

import java.util.Map;

/**
 * Created by biezhi on 2017/2/19.
 */
public class SpConst {

    public static final String LOGIN_SESSION_KEY = "login_user";

    public static final String USER_IN_COOKIE = "SH_SIGNIN_USER";

    public static final String JC_REFERRER_COOKIE = "JC_REFERRER_COOKIE";

    public static String SITE_URL;

    public static String AES_SALT = "0123456789abcdef";

    public static Config config;

    /**
     * 邮件配置
     */
    public static String MAIL_HOST;
    public static String MAIL_USER;
    public static String MAIL_USERNAME;
    public static String MAIL_PASS;

    public static String[] METHODS = {"rc4-md5", "aes-128-cfb", "aes-192-cfb", "aes-256-cfb", "des-cfb", "bf-cfb", "cast5-cfb", "chacha20", "salsa20"};
}
