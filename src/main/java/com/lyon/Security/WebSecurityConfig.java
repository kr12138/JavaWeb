package com.lyon.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


// 拦截器配置
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    //    public static final String SESSION_KEY="name";
    @Bean
    public userHandlerInterceptor getUserHandlerInterceptor() {
        return new userHandlerInterceptor();
    }

    @Bean
    public adminHandlerInterceptor getAdminHandlerInterceptor() {
        return new adminHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration user = registry.addInterceptor(getUserHandlerInterceptor());
        // 排除配置
        user.excludePathPatterns("/**/login/**");
        user.excludePathPatterns("/**/register/**");
        // 拦截配置
        user.addPathPatterns("/**/**");

        InterceptorRegistration admin = registry.addInterceptor(getAdminHandlerInterceptor());
//        admin.addPathPatterns("/**/getAll/**");
//        admin.addPathPatterns("/**/get/**");
//        admin.addPathPatterns("/**/getByUid/**");
//        admin.addPathPatterns("/**/getByCid/**");
        admin.excludePathPatterns("/**/login/**");
        admin.excludePathPatterns("/**/register/**");
        admin.addPathPatterns("/**/user/**");
        admin.addPathPatterns("/**/add");
        admin.addPathPatterns("/**/delete");
        admin.addPathPatterns("/**/update");
    }

}
