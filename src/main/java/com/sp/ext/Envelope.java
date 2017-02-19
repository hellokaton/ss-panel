package com.sp.ext;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by biezhi on 2017/2/19.
 */
public class Envelope implements Serializable {

    private String to;
    private String tpl;
    private String subject;
    private Map<String, Object> cry;

    public Envelope(String to, String tpl, String subject) {
        this.to = to;
        this.tpl = tpl;
        this.subject = subject;
    }

    public Envelope(String to, String tpl, String subject, Map<String, Object> cry) {
        this.to = to;
        this.tpl = tpl;
        this.subject = subject;
        this.cry = cry;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getCry() {
        return cry;
    }

    public void setCry(Map<String, Object> cry) {
        this.cry = cry;
    }
}
