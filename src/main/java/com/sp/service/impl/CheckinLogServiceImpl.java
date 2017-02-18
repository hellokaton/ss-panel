package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.CheckinLog;
import com.sp.exception.TipException;
import com.sp.service.CheckinLogService;

@Service
public class CheckinLogServiceImpl implements CheckinLogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public CheckinLog getCheckinLogById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(CheckinLog.class, id);
	}

	@Override
	public List<CheckinLog> getCheckinLogList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getCheckinLogPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<CheckinLog> getCheckinLogPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(CheckinLog checkinLog) throws Exception {
		if(null == checkinLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(checkinLog);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(CheckinLog checkinLog) throws Exception {
		if(null == checkinLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(checkinLog);
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
			activeRecord.delete(CheckinLog.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
