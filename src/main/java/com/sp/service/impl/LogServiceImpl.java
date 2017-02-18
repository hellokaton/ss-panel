package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.Log;
import com.sp.exception.TipException;
import com.sp.service.LogService;

@Service
public class LogServiceImpl implements LogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public Log getLogById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(Log.class, id);
	}

	@Override
	public List<Log> getLogList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getLogPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<Log> getLogPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(Log log) throws Exception {
		if(null == log){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(log);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Log log) throws Exception {
		if(null == log){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(log);
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
			activeRecord.delete(Log.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
