package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.sp.config.SpConst;
import com.sp.ext.Envelope;
import com.sp.ext.Functions;
import com.sp.model.PasswordReset;
import com.sp.service.PasswordResetService;
import com.sp.utils.EmailUtils;
import com.sp.utils.UUID;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by biezhi on 2017/2/19.
 */
@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public void sendResetEmail(String email) {
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.setEmail(email);
        String token = UUID.UU16();
        passwordReset.setToken(token);
        int time = DateKit.getCurrentUnixTime();
        passwordReset.setInit_time(time);
        passwordReset.setExpire_time(time + 3600 * 24);
        activeRecord.insert(passwordReset);

        String subject = Functions.config("appName") + " 重置密码";

        String resetUrl = SpConst.SITE_URL + "/password/token?c=" + token;
        Map<String, Object> cry = new HashMap<>();
        cry.put("resetUrl", resetUrl);
        cry.put("appName", Functions.config("appName"));

        Envelope envelope = new Envelope(email, "/email/password/reset.html", subject, cry);
        EmailUtils.send(envelope);

    }

    @Override
    public PasswordReset byToken(String token) {
        if(StringKit.isNotBlank(token)){
            PasswordReset temp = new PasswordReset();
            temp.setToken(token);
            return activeRecord.one(temp);
        }
        return null;
    }
}
