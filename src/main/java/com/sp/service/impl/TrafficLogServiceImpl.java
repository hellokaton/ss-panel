package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.TrafficLog;
import com.sp.exception.TipException;
import com.sp.service.TrafficLogService;

@Service
public class TrafficLogServiceImpl implements TrafficLogService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public TrafficLog getTrafficLogById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(TrafficLog.class, id);
	}

	@Override
	public List<TrafficLog> getTrafficLogList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getTrafficLogPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<TrafficLog> getTrafficLogPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(TrafficLog trafficLog) throws Exception {
		if(null == trafficLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(trafficLog);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(TrafficLog trafficLog) throws Exception {
		if(null == trafficLog){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(trafficLog);
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
			activeRecord.delete(TrafficLog.class, id);
		} catch (Exception e){
			throw e;
		}
	}
		
}
