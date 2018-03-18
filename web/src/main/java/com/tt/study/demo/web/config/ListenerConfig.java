package com.tt.study.demo.web.config;

import com.tt.study.demo.web.listener.MyListener2;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * date: 2018/3/16
 * author: wt
 */
@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean myListener2Register() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new MyListener2());
        return bean;
    }
}
