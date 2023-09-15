package com.tyza66.orderquerytool.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: tyza66
 * Date: 2023/9/15 14:17
 * Github: https://github.com/tyza66
 **/

public class SaTokenConfig {
    @Configuration
    public class SaTokenConfigure implements WebMvcConfigurer {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            // 注册 Sa-Token 拦截器，打开注解式鉴权功能
            registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");
        }
    }
}
