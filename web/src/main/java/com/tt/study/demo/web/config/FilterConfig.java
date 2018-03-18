package com.tt.study.demo.web.config;

import com.tt.study.demo.web.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * date: 2018/3/16
 * author: wt
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean myFilter2Register() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter2());
        filterRegistrationBean.addUrlPatterns("/home");
        return filterRegistrationBean;
    }
}
