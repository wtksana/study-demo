package com.tt.study.demo.service.MqConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * date: 2018/3/30
 * author: wt
 */
@Component
@RabbitListener(queues = "fanout.b")
@Slf4j
public class FanoutBConsumer {

    @RabbitHandler
    public void process(String msg) {
        log.info("fanout.b msg :{}", msg);
    }

}
