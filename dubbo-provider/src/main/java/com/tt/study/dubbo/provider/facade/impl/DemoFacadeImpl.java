package com.tt.study.dubbo.provider.facade.impl;

import com.tt.study.dubbo.api.facade.DemoFacade;

@com.alibaba.dubbo.config.annotation.Service(timeout = 5000)
public class DemoFacadeImpl implements DemoFacade {

    @Override
    public Integer safeAdd(Integer a, Integer b) {
        if (a == null) {
            a = 0;
        }
        if (b == null) {
            return a;
        }
        return a + b;
    }

}