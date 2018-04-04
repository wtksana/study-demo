package com.tt.study.demo.service.MqProducer;

import com.tt.study.demo.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * date: 2018/3/22
 * author: wt
 */
@Component
public class HelloProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToStringQueue(String msg) {
        rabbitTemplate.convertAndSend("string", msg);
    }

    /**
     * 把User发送到string Queue会怎么样
     *
     * @param user
     */
    public void sendToStringQueue(User user) {
        rabbitTemplate.convertAndSend("string", user);
    }

    public void sendToUserQueue(User user) {
        rabbitTemplate.convertAndSend("user", user);
    }

    public void sendToFanoutExchange(String msg) {
        rabbitTemplate.convertAndSend("fanoutExchange", null, msg);
    }

    public void sendWithTopicA(String msg) {
        rabbitTemplate.convertAndSend("topicExchange", "key.a", msg);
    }


    public void sendWithTopicB(String msg) {
        rabbitTemplate.convertAndSend("topicExchange", "key.b", msg);
    }

    public void sendWithTopicBC(String msg) {
        rabbitTemplate.convertAndSend("topicExchange", "key.b.c", msg);
    }
}
