package com.tavares.springredis.dao.imp;

import com.tavares.springredis.dao.IUserDao;
import com.tavares.springredis.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDaoImp implements IUserDao {

    final Logger logger = LoggerFactory.getLogger(UserDaoImp.class);

    RedisTemplate<String, User> redisTemplate;

    public UserDaoImp(@Qualifier("userTemplate") RedisTemplate<String, User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void create(User user) {
        redisTemplate.opsForHash().put("USER", user.getUserId(), user);
        logger.info(String.format("User with ID %s saved", user.getUserId()));
    }

    @Override
    public User get(String userId) {
        return (User) redisTemplate.opsForHash().get("USER", userId);
    }

    @Override
    public Map<Object, Object> getAll() {
        return redisTemplate.opsForHash().entries("USER");
    }

    @Override
    public void update(User user) {
        redisTemplate.opsForHash().put("USER", user.getUserId(), user);
        logger.info(String.format("User with ID %s updated", user.getUserId()));
    }

    @Override
    public void delete(String userId) {
        redisTemplate.opsForHash().delete("USER", userId);
        logger.info(String.format("User with ID %s deleted", userId));
    }

}
