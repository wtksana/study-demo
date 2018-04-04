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
@RabbitListener(queues = "topic.b")
@Slf4j
public class TopicBConsumer {
    @RabbitHandler
    public void process(String msg) {
        log.info("topic.b msg : {}", msg);
    }
}
