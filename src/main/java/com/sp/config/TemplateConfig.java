package com.sp.config;

import com.blade.annotation.Order;
import com.blade.config.BaseConfig;
import com.blade.config.Configuration;
import com.blade.ioc.annotation.Component;
import com.blade.mvc.view.ViewSettings;
import com.blade.mvc.view.template.JetbrickTemplateEngine;

@Component
@Order(sort = 2)
public class TemplateConfig implements BaseConfig {

    @Override
    public void config(Configuration configuration) {
        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
        ViewSettings.$().templateEngine(templateEngine);
    }
}
