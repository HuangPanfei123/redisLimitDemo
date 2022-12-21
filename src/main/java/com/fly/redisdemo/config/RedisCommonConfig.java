package com.fly.redisdemo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.core.io.ClassPathResource;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *  * @author: huang.panfei
 *  * @className: RedisCommonConfig
 *  * @packageName: com.fly.redislimitdemo.config
 *  * @description:redis配置类
 *  * @data: 2022年12月20日13:44
 *  *
 */
@Component
public class RedisCommonConfig {
    /**
     * 读取限流脚本
     *
     * @return
     */
    @Bean
    public DefaultRedisScript<Long> redisluaScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redisLimit.lua")));
        //返回类型
        redisScript.setResultType(Long.class);
        return redisScript;
    }
    /**
     * RedisTemplate
     *
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
