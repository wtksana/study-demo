package com.tt.study.demo.service.MqConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * date: 2018/3/22
 * author: wt
 */
@Component
@RabbitListener(queues = "hello")
@Slf4j
public class HelloConsumer {

    @RabbitHandler
    public void process(String msg) {
        log.info("mq msg : " + msg);
    }

}
