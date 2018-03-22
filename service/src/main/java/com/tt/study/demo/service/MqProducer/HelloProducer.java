package com.tt.study.demo.service.MqProducer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * date: 2018/3/22
 * author: wt
 */
@Component
public class HelloProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String msg) {
        amqpTemplate.convertAndSend("hello", msg);
    }
}
