package com.sp.service;

import java.util.List;

import com.blade.jdbc.core.Take;
import com.blade.jdbc.model.Paginator;

import com.sp.model.Config;

public interface ConfigService {
	
	Config getConfigById(Integer id);

	List<Config> getConfigList(Take take);
	
	Paginator<Config> getConfigPage(Take take);

	void update(Config config) throws Exception;

	void save(Config config) throws Exception;
	
	void delete(Integer id) throws Exception;

	String getConfig(String key);
}
