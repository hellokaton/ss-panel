package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.Log;

public interface LogService {
	
	Log getLogById(Integer id);

	List<Log> getLogList(Take take);
	
	Paginator<Log> getLogPage(Take take);

	void update(Log log) throws Exception;

	void save(Log log) throws Exception;
	
	void delete(Integer id) throws Exception;
		
}
