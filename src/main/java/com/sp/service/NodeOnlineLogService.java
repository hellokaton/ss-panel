package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.NodeOnlineLog;

public interface NodeOnlineLogService {
	
	NodeOnlineLog getNodeOnlineLogById(Integer id);

	List<NodeOnlineLog> getNodeOnlineLogList(Take take);
	
	Paginator<NodeOnlineLog> getNodeOnlineLogPage(Take take);

	void update(NodeOnlineLog nodeOnlineLog) throws Exception;

	void save(NodeOnlineLog nodeOnlineLog) throws Exception;
	
	void delete(Integer id) throws Exception;
		
}
