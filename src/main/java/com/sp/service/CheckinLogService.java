package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.CheckinLog;

public interface CheckinLogService {
	
	CheckinLog getCheckinLogById(Integer id);

	List<CheckinLog> getCheckinLogList(Take take);
	
	Paginator<CheckinLog> getCheckinLogPage(Take take);

	void update(CheckinLog checkinLog) throws Exception;

	void save(CheckinLog checkinLog) throws Exception;
	
	void delete(Integer id) throws Exception;
		
}
