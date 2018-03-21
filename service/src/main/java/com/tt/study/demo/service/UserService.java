package com.tt.study.demo.service;

import com.tt.study.demo.common.RespInfo;
import com.tt.study.demo.entity.User;
import com.tt.study.demo.entity.UserExample;
import com.tt.study.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * date: 2018/3/15
 * author: wt
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public RespInfo<String> addUser(User user) {
        int count = userMapper.insert(user);
        if (count == 1) {
            return RespInfo.success("添加成功");
        }
        return RespInfo.error("添加失败");
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
