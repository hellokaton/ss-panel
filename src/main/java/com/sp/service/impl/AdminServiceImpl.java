package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.kit.DateKit;
import com.sp.dto.Analytics;
import com.sp.model.Node;
import com.sp.model.User;
import com.sp.service.AdminService;

/**
 * Created by biezhi on 2017/2/19.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Inject
    private ActiveRecord activeRecord;

    @Override
    public Analytics getAnalytics(int time) {
        Analytics analytics = new Analytics();

        int users = activeRecord.count(new Take(User.class));
        int nodes = activeRecord.count(new Take(Node.class));
        int checkInUsers = activeRecord.count(new Take(User.class).gt("last_check_in_time", 0));

        int t = DateKit.getCurrentUnixTime() - time;
        int online_users = activeRecord.count(new Take(User.class).gt("t", t));

        analytics.setUsers(users);
        analytics.setNodes(nodes);
        analytics.setCheckInUsers(checkInUsers);
        analytics.setOnline_users(online_users);

        return analytics;
    }
}
