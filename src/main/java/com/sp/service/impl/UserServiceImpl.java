package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.sp.model.User;
import com.sp.service.UserService;

/**
 * Created by biezhi on 2017/2/19.
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public User getUserById(Integer uid) {
        if(null != uid){
            return activeRecord.byId(User.class, uid);
        }
        return null;
    }

    @Override
    public void update(User user) {
        if(null != user && null != user.getId()){
            activeRecord.update(user);
        }
    }

    @Override
    public User byEmail(String email) {
        if(StringKit.isNotBlank(email)){
            User user = new User();
            user.setEmail(email);
            return activeRecord.one(user);
        }
        return null;
    }

    @Override
    public int getIpRegCount(String ip) {
        if(StringKit.isNotBlank(ip)){
            Take take = new Take(User.class);
            take.eq("reg_ip", ip);
            take.gt("reg_date", DateKit.getCurrentUnixTime());
            return activeRecord.count(take);
        }
        return 0;
    }

    @Override
    public int getLastPort() {
        String sql = "select port from sp_user order by port desc limit 1";
        Integer port = activeRecord.one(Integer.class, sql);
        if(null == port){
            port = 1024;
        }
        return port;
    }

    @Override
    public void save(User user) {
        if(null != user){
            activeRecord.insert(user);
        }
    }

    @Override
    public Paginator<User> getUsers(Take take) {
        if(null != take){
            return activeRecord.page(take);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        if(null != id){
            activeRecord.delete(User.class, id);
        }
    }

}
