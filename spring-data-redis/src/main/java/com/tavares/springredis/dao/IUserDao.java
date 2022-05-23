package com.tavares.springredis.dao;

import com.tavares.springredis.domain.User;

import java.util.Map;

public interface IUserDao {

    void create(User user);

    User get(String userId);

    Map<String, User> getAll();

    void update(User user);

    void delete(String userId);
}
