package com.tt.study.dubbo.provider.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//扫描dubbo相关注解
@DubboComponentScan("com.tt.study.dubbo.provider")
public class DubboConfig {
    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        //协议使用zk
        registryConfig.setProtocol("zookeeper");
        //zk地址
        registryConfig.setAddress("118.126.82.20:2181");
        //client
        registryConfig.setClient("curator");
        return registryConfig;
    }
}
