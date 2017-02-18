package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.NodeOnlineLog;
import com.sp.exception.TipException;
import com.sp.service.NodeOnlineLogService;

@Service
public class NodeOnlineLogServiceImpl implements NodeOnlineLogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public NodeOnlineLog getNodeOnlineLogById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(NodeOnlineLog.class, id);
	}

	@Override
	public List<NodeOnlineLog> getNodeOnlineLogList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getNodeOnlineLogPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<NodeOnlineLog> getNodeOnlineLogPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(NodeOnlineLog nodeOnlineLog) throws Exception {
		if(null == nodeOnlineLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(nodeOnlineLog);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(NodeOnlineLog nodeOnlineLog) throws Exception {
		if(null == nodeOnlineLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(nodeOnlineLog);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void delete(Integer id) throws Exception {
		if(null == id){
			throw new TipException("主键为空");
		}
		try {
			activeRecord.delete(NodeOnlineLog.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
