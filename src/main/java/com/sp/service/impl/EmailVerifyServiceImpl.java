package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.sp.config.SpConst;
import com.sp.ext.Envelope;
import com.sp.model.EmailVerify;
import com.sp.service.EmailVerifyService;
import com.sp.utils.EmailUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerifyServiceImpl implements EmailVerifyService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public boolean checkVerifyCode(String email, String verifycode) {
        if (StringKit.isNotBlank(email) && StringKit.isNotBlank(verifycode)) {
            EmailVerify temp = new EmailVerify();
            temp.setEmail(email);
            EmailVerify emailVerify = activeRecord.one(temp);
            if (null == emailVerify || emailVerify.getExpire_at() < DateKit.getCurrentUnixTime()
                    || !emailVerify.getToken().equals(verifycode)) {
                return false;
            }
            activeRecord.delete(EmailVerify.class, emailVerify.getId());
            return true;
        }
        return false;
    }

    @Override
    public void sendVerification(String email) {
        int ttl = SpConst.config.getInt("emailVerifyTTL");
        int len = SpConst.config.getInt("emailVerifyCodeLength");
        EmailVerify temp = new EmailVerify();
        temp.setEmail(email);
        EmailVerify emailVerify = activeRecord.one(temp);

        String token = StringKit.getRandomChar(len);
        int expire_at = DateKit.getCurrentUnixTime() + ttl;
        emailVerify.setToken(token);
        emailVerify.setExpire_at(expire_at);

        if (null == emailVerify) {
            emailVerify = new EmailVerify();
            emailVerify.setEmail(email);
            activeRecord.insert(emailVerify);
        } else {
            activeRecord.update(emailVerify);
        }

        String appName = SpConst.config.get("appName");
        String subject = appName + " 邮箱验证";
        Map<String, Object> ary = new HashMap<>();
        ary.put("token", token);
        ary.put("ttl", ttl);
        ary.put("appName", appName);

        Envelope envelope = new Envelope(email, subject, "/auth/verify.html", ary);
        EmailUtils.send(envelope);

    }
}
