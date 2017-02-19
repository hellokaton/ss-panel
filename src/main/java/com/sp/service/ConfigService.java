package com.sp.service;

import java.util.Map;

public interface ConfigService {

    Map<String, String> getConfigs(String...keys);

	String getConfig(String key);

    void updateByKey(String key, String value);
}
