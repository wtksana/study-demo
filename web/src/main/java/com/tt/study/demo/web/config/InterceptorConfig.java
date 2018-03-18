package com.tt.study.demo.web.config;

import com.tt.study.demo.web.interceptor.MyInterceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * date: 2018/3/16
 * author: wt
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns 表示那些url需要拦截
        //excludePathPatterns 表示那些不需要拦截
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/home").excludePathPatterns("/");
    }
}
