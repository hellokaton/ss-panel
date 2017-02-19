package com.sp.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.core.Take;
import com.blade.kit.StringKit;
import com.sp.model.SSConfig;
import com.sp.service.ConfigService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Inject
	private ActiveRecord activeRecord;

	@Override
	public String getConfig(String key) {
		SSConfig config = new SSConfig();
		config.setKey_(key);
		SSConfig c = activeRecord.one(config);
		if(null != c){
			return c.getValue_();
		}
		return "";
	}

	@Override
	public Map<String, String> getConfigs(String... keys) {
		Map<String, String> map = new HashMap<>();
		List<SSConfig> list = activeRecord.list(new Take(SSConfig.class).in("key_", keys));
		if(null != list){
			for(SSConfig ssConfig : list){
				map.put(ssConfig.getKey_(), ssConfig.getValue_());
			}
		}
		return map;
	}

	@Override
	public void updateByKey(String key, String value) {
		if(StringKit.isNotBlank(key) && StringKit.isNotBlank(value)){
			String sql = "update sp_config set `value_` = ? where `key_` = ?";
			activeRecord.execute(sql, value, key);
		}
	}
}
