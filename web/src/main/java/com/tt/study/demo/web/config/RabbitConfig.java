package com.tt.study.demo.web.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * date: 2018/3/22
 * author: wt
 */
@Configuration
public class RabbitConfig {

    //修改默认的序列化方式为jackson序列化方式
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    //--------------------------直连--------------------------
    @Bean
    public Queue stringQueue() {
        return new Queue("string");
    }

    @Bean
    public Queue userQueue() {
        return new Queue("user");
    }

    //--------------------------广播--------------------------
    @Bean
    public Queue fanoutAQueue() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue fanoutBQueue() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue fanoutCQueue() {
        return new Queue("fanout.c");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * 把fanoutAQueue和fanoutExchange绑定起来
     *
     * @param fanoutAQueue   注意这个参数名必须和你定义Queue的方法名相同
     * @param fanoutExchange 注意这个参数名必须和你定义FanoutExchange的方法名相同
     * @return
     */
    @Bean
    public Binding bindingFanoutAQueue(Queue fanoutAQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutAQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutBQueue(Queue fanoutBQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutBQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanoutCQueue(Queue fanoutCQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutCQueue).to(fanoutExchange);
    }

    //--------------------------主题--------------------------
    @Bean
    public Queue topicAQueue() {
        return new Queue("topic.a");
    }

    @Bean
    public Queue topicBQueue() {
        return new Queue("topic.b");
    }

    @Bean
    public Queue topicCQueue() {
        return new Queue("topic.c");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingTopicAQueue(Queue topicAQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicAQueue).to(topicExchange).with("key.a");
    }

    @Bean
    public Binding bindingTopicBQueue(Queue topicBQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicBQueue).to(topicExchange).with("key.*");
    }

    @Bean
    public Binding bindingTopicCQueue(Queue topicCQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicCQueue).to(topicExchange).with("key.#");
    }

}
