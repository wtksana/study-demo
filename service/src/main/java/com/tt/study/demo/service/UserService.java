package com.tt.study.demo.service;

import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
import com.tt.study.demo.entity.UserExample;
import com.tt.study.demo.mapper.UserMapper;
import com.tt.study.demo.service.async.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date: 2018/3/15
 * author: wt
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AsyncTask asyncTask;

    public RespInfo<String> register(User user) throws Exception {
        //添加新用户记录
        int count = userMapper.insert(user);
        if (count == 1) {
            //用户注册成功
            //发送用户注册成功短信 不需要等结果 可以改成异步调用
            asyncTask.sendMessage(user);
            //发送用户注册成功邮件 不需要等结果 可以改成异步调用
            asyncTask.sendEmail(user);
            log.info("注册成功 user:{}", user);
            return RespInfo.success("注册成功");
        }
        return RespInfo.error("注册失败");
    }

    private void sendMessage(User user) throws Exception {
        //模拟发送短信耗时
        Thread.sleep(500);
        log.info("发送短信成功 user:{}", user);
    }

    private void sendEmail(User user) throws Exception {
        //模拟发送邮件耗时
        Thread.sleep(1000);
        log.info("发送邮件成功 user:{}", user);
    }

    @Cacheable(cacheNames = "Demo")
    public RespInfo<User> login(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(user.getUsername())
                .andPasswordEqualTo(user.getPassword());
        List<User> list = userMapper.selectByExample(userExample);
        if (list != null && list.size() > 0) {
            return RespInfo.success(list.get(0));
        }
        return RespInfo.error("用户名或密码错误");

    }

    public RespInfo<List<User>> getUserList() {
        List<User> list = userMapper.selectByExample(null);
        return RespInfo.success(list);
    }

    @Cacheable(cacheNames = "Demo")
    public RespInfo<User> getUser(Long id) {
        if (id == null) {
            return RespInfo.error("请输入用户ID");
        }
        User user = userMapper.selectByPrimaryKey(id);
        redisTemplate.opsForValue().set("test", user);
        return RespInfo.success(user);
    }
}
