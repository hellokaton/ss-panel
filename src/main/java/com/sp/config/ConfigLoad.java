package com.sp.config;

import com.blade.annotation.Order;
import com.blade.config.BaseConfig;
import com.blade.config.Configuration;
import com.blade.ioc.annotation.Component;
import com.sp.ext.Functions;

/**
 * Created by biezhi on 2017/2/19.
 */
@Component
@Order(sort = 1)
public class ConfigLoad implements BaseConfig {

    @Override
    public void config(Configuration configuration) {
        com.blade.kit.base.Config config = configuration.config();
        Functions.config = config;
        SpConst.MAIL_HOST = config.get("mail.smtp.host");
        SpConst.MAIL_USER = config.get("mail.user");
        SpConst.MAIL_USERNAME = config.get("mail.from");
        SpConst.MAIL_PASS = config.get("mail.pass");
    }
}
