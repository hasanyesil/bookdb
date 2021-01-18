package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.ReadingStatus;
import com.hashy.bookdb.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookListRepository extends CrudRepository<BookList,Long> {

    Optional<BookList> findByUserAndReadingStatus(User user, ReadingStatus readingStatus);
}