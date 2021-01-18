package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.User;

import java.util.Set;

public interface UserService {

    User findByUserId(Long id);

    User saveUser(User user);

    User findByUserNameAndPassword(String userName, String password);

}
