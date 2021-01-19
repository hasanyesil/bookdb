package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.ReadingStatus;
import com.hashy.bookdb.domain.User;

import java.util.Set;

public interface BookListService {
    BookList save(BookList bookList);
    BookList findByUserAndReadingStatus(User user, ReadingStatus readingStatus);
}
