package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.NodeInfoLog;
import com.sp.exception.TipException;
import com.sp.service.NodeInfoLogService;

@Service
public class NodeInfoLogServiceImpl implements NodeInfoLogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public NodeInfoLog getNodeInfoLogById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(NodeInfoLog.class, id);
	}

	@Override
	public List<NodeInfoLog> getNodeInfoLogList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getNodeInfoLogPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<NodeInfoLog> getNodeInfoLogPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(NodeInfoLog nodeInfoLog) throws Exception {
		if(null == nodeInfoLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(nodeInfoLog);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(NodeInfoLog nodeInfoLog) throws Exception {
		if(null == nodeInfoLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(nodeInfoLog);
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
			activeRecord.delete(NodeInfoLog.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
