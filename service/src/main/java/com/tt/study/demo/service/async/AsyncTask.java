package com.tt.study.demo.service.async;

import com.tt.study.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask {
    @Async("taskExecutor")
    public void sendMessage(User user) throws Exception {
        //模拟发送短信耗时
        Thread.sleep(500);
        log.info("发送短信成功 user:{}", user);
    }

    @Async("taskExecutor")
    public void sendEmail(User user) throws Exception {
        //模拟发送邮件耗时
        Thread.sleep(1000);
        log.info("发送邮件成功 user:{}", user);
    }
}
