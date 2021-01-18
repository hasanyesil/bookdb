package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassWord(userName,password).orElse(null);
    }

    @Override
    public User findByUserId(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
