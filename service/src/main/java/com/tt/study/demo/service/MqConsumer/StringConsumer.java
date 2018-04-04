package com.tt.study.demo.service.MqConsumer;

import com.tt.study.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * date: 2018/3/22
 * author: wt
 */
@Component
@RabbitListener(queues = "string")
@Slf4j
public class StringConsumer {

    @RabbitHandler
    public void process(String msg) {
        log.info("string Queue msg : " + msg);
    }

    /**
     * 要消费User类型的消息，需要再定义一个参数为User类型的handler
     */
    @RabbitHandler
    public void process(User user) {
        log.info("user msg : " + user);
    }

}
