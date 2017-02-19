package com.sp.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.blade.Blade;
import com.blade.annotation.Order;
import com.blade.config.BaseConfig;
import com.blade.config.Configuration;
import com.blade.ioc.annotation.Component;
import com.blade.jdbc.ActiveRecord;
import com.blade.jdbc.ar.SampleActiveRecord;
import com.blade.kit.base.Config;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@Component
@Order(sort = 2)
public class DBConfig implements BaseConfig {

    public ActiveRecord activeRecord;

    @Override
    public void config(Configuration configuration) {
        try {
            InputStream in = DBConfig.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties props = new Properties();
            props.load(in);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
            activeRecord = new SampleActiveRecord(dataSource);
            Blade.$().ioc().addBean(activeRecord);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
