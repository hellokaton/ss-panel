package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.kit.DateKit;
import com.sp.exception.TipException;
import com.sp.model.Token;
import com.sp.service.TokenService;
import com.sp.utils.UUID;

/**
 * Created by biezhi on 2017/2/20.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public Token byToken(String token) {
        if(null != token){
            Token t = new Token();
            t.setToken(token);
            return activeRecord.one(t);
        }
        return null;
    }

    @Override
    public String create(Integer uid) {
        if(null == uid){
            throw new TipException("uid不能为空");
        }
        String token = UUID.UU32();
        Token t = new Token();
        t.setToken(token);
        t.setUser_id(uid);
        int time = DateKit.getCurrentUnixTime();
        int exp = time + 3600*24*7;
        t.setCreate_time(time);
        t.setExpire_time(exp);
        activeRecord.insert(t);
        return token;
    }

    public static void main(String[] args) {
        System.out.println(UUID.UU32());
    }
}
