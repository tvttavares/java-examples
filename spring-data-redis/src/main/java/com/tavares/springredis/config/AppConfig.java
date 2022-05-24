package com.tavares.springredis.config;

import com.tavares.springredis.domain.Employee;
import com.tavares.springredis.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

;

@Configuration
public class AppConfig {

    //Creating Connection with Redis
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(); -- using Lettuce
        return new JedisConnectionFactory(); // -- using jedis
    }

    //Creating RedisTemplate for Entity 'Employee'
    @Bean(name = "employeeTemplate")
    public RedisTemplate<String, Employee> redisTemplate() {
        RedisTemplate<String, Employee> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    //Creating RedisTemplate for Entity 'Employee'
    @Bean(name = "userTemplate")
    public RedisTemplate<String, User> redisUserTemplate() {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
}