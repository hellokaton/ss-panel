package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.kit.DateKit;
import com.sp.model.CheckinLog;
import com.sp.service.CheckinLogService;

@Service
public class CheckinLogServiceImpl implements CheckinLogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public void save(Integer user_id, int trafficToAdd) {
		if(null != user_id && trafficToAdd > 0){
			CheckinLog checkinLog = new CheckinLog();
			checkinLog.setUser_id(user_id);
			checkinLog.setTraffic(trafficToAdd);
			checkinLog.setCheckin_at(DateKit.getCurrentUnixTime());
			activeRecord.insert(checkinLog);
		}
	}
}
