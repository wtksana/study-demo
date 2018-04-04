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
@RabbitListener(queues = "user")
@Slf4j
public class UserConsumer {

    @RabbitHandler
    public void process(User user){
        log.info("user queue msg : " + user);
    }

}
