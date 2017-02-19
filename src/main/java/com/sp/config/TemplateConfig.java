package com.sp.config;

import com.blade.annotation.Order;
import com.blade.config.BaseConfig;
import com.blade.config.Configuration;
import com.blade.ioc.annotation.Component;
import com.blade.mvc.view.ViewSettings;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import com.sp.ext.Functions;
import jetbrick.template.resolver.GlobalResolver;

@Component
@Order(sort = 3)
public class TemplateConfig implements BaseConfig {

    @Override
    public void config(Configuration configuration) {
        JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
//        JetGlobalContext context = templateEngine.getGlobalContext();
        GlobalResolver resolver = templateEngine.getGlobalResolver();
        resolver.registerFunctions(Functions.class);
        ViewSettings.$().templateEngine(templateEngine);
    }
}
