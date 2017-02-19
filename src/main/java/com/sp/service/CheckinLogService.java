package com.sp.service;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;
import com.sp.model.CheckinLog;

public interface CheckinLogService {
	
    void save(Integer user_id, int trafficToAdd);

    Paginator<CheckinLog> getLogs(Take take);
}
