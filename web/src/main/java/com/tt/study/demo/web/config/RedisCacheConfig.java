package com.tt.study.demo.web.config;

import com.google.gson.Gson;
import com.tt.study.demo.common.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;


/**
 * date: 2018/3/20
 * author: wt
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean//这个注解必须要
    public RedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        //将序列化方式改为GenericJackson2JsonRedisSerializer
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean//这个注解必须要
    @Override
    public CacheManager cacheManager() {
        //新建一个默认的RedisCacheConfiguration
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        //在默认的配置上修改key过期时间为6小时//修改值的序列化方式为GenericJackson2JsonRedisSerializer
        configuration = configuration.entryTtl(Duration.ofHours(6)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        //设置RedisCacheManager
        RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(configuration).build();
        return cacheManager;
    }

    @Bean//这个注解必须要
    @Override
    public KeyGenerator keyGenerator() {
        //使用包名+类名+方法名+参数的格式生成key，防止key冲突导致数据异常
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":").append(method.getName());
            sb.append(":").append(getParamsString(params));
            return sb.toString();
        };
    }

    private String getParamsString(Object params) {
        try {
            String s = new Gson().toJson(params);
            return MD5Util.MD5(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }
}
