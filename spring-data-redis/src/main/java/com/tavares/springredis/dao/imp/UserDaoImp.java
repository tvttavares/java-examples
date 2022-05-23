package com.tavares.springredis.dao.imp;

import com.tavares.springredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImp {

//    final Logger logger = LoggerFactory.getLogger(UserDaoImp.class);
//
//    private final HashOperations hashOperations;
//
//    public UserDaoImp(RedisTemplate redisTemplate) {
//        this.hashOperations = redisTemplate.opsForHash();
//    }
//
//    public void create(User user) {
//        hashOperations.put("USER", user.getUserId(), user);
//        logger.info(String.format("User with ID %s saved", user.getUserId()));
//    }
//
//    public User get(String userId) {
//        return (User) hashOperations.get("USER", userId);
//    }
//
//    public Map<String, User> getAll() {
//        return hashOperations.entries("USER");
//    }
//
//    public void update(User user) {
//        hashOperations.put("USER", user.getUserId(), user);
//        logger.info(String.format("User with ID %s updated", user.getUserId()));
//    }
//
//    public void delete(String userId) {
//        hashOperations.delete("USER", userId);
//        logger.info(String.format("User with ID %s deleted", userId));
//    }
}
