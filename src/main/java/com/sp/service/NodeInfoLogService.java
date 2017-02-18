package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.NodeInfoLog;

public interface NodeInfoLogService {
	
	NodeInfoLog getNodeInfoLogById(Integer id);

	List<NodeInfoLog> getNodeInfoLogList(Take take);
	
	Paginator<NodeInfoLog> getNodeInfoLogPage(Take take);

	void update(NodeInfoLog nodeInfoLog) throws Exception;

	void save(NodeInfoLog nodeInfoLog) throws Exception;
	
	void delete(Integer id) throws Exception;
		
}
