package com.sp.utils;

import com.sp.config.SpConst;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by biezhi on 2017/2/13.
 */
public final class EmailUtils {

    /**
     * 发送注册激活邮件
     *
     * @param email
     * @param username
     * @param code
     */
    public static void sendSignup(String email, String username, String code){
        String url = SpConst.SITE_URL + "/active/" + code;
        String content = "您的激活链接是：<a href='"+url+"'>"+url+"</a> 点击链接激活账号！";
        send(username + ", 欢迎你加入" + SpConst.MAIL_USERNAME, email, content);
    }

    public static void send(final String subject, final String to_addr, final String content){
        try {
            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setHostName(SpConst.MAIL_HOST);
            email.addTo(to_addr);
            //email.setStartTLSEnabled(true);
            email.setFrom(SpConst.MAIL_USER, SpConst.MAIL_USERNAME);
            email.setAuthentication(SpConst.MAIL_USER, SpConst.MAIL_PASS);
            email.setCharset("UTF-8");

            email.setSubject(subject);
            // set the html message
            email.setHtmlMsg(content);
            // send the email
            email.send();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
