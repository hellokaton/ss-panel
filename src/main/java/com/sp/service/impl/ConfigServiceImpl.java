package com.sp.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.Config;
import com.sp.exception.TipException;
import com.sp.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public Config getConfigById(Integer id) {
		if(null == id){
			return null;
		}
		return activeRecord.byId(Config.class, id);
	}

	@Override
	public List<Config> getConfigList(Take take) {
		if(null != take){
			if(null != take.getPageRow()){
				return this.getConfigPage(take).getList();
			}
			return activeRecord.list(take);
		}
		return null;
	}
	
	@Override
	public Paginator<Config> getConfigPage(Take take) {
		if(null != take){
			return activeRecord.page(take);
		}
		return null;
	}
	
	@Override
	public void save(Config config) throws Exception {
		if(null == config){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.insert(config);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void update(Config config) throws Exception {
		if(null == config){
			throw new TipException("对象为空");
		}
		try {
			activeRecord.update(config);
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
			activeRecord.delete(Config.class, id);
		} catch (Exception e){
			throw e;
		}
	}

	@Override
	public String getConfig(String key) {
		String sql = "select `value` from sp_config where `key` = ?";
		return activeRecord.one(String.class, sql, key);
	}
}
