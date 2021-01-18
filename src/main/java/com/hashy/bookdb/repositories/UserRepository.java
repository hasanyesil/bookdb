package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUserNameAndPassWord(String userName,String password);
}
