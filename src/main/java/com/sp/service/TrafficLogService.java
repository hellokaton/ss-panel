package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.TrafficLog;

public interface TrafficLogService {
	
	TrafficLog getTrafficLogById(Integer id);

	List<TrafficLog> getTrafficLogList(Take take);
	
	Paginator<TrafficLog> getTrafficLogPage(Take take);

	void update(TrafficLog trafficLog) throws Exception;

	void save(TrafficLog trafficLog) throws Exception;
	
	void delete(Integer id) throws Exception;

	Paginator<TrafficLog> getLogs(Take take);
}
