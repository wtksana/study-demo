package com.tt.study.demo.web.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * date: 2018/3/16
 * author: wt
 */
@Slf4j
public class MyListener2 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("MyListener2 初始化");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("MyListener2 销毁");
    }
}
