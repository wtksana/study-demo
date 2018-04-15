package com.tt.study.demo.service.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * date: 2018/4/9
 * author: wt
 */
@Component
@Slf4j
public class TestTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        //集群部署时需要加锁
        String key = "task";
        if (!redisTemplate.opsForValue().setIfAbsent(key, 1)) {
            return;
        }
        redisTemplate.expire(key, 3, TimeUnit.SECONDS);
        //执行任务内容
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
