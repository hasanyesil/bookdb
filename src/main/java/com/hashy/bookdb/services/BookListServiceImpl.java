package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.ReadingStatus;
import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.repositories.BookListRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookListServiceImpl implements BookListService{

    private final BookListRepository bookListRepository;

    public BookListServiceImpl(BookListRepository bookListRepository) {
        this.bookListRepository = bookListRepository;
    }

    @Override
    public BookList save(BookList bookLists) {
        return bookListRepository.save(bookLists);
    }

    @Override
    public BookList findByUserAndReadingStatus(User user, ReadingStatus readingStatus) {
        return bookListRepository.findByUserAndReadingStatus(user,readingStatus).orElse(null);
    }

    @Override
    public Set<BookList> findByReadingStatus(ReadingStatus readingStatus) {
        return bookListRepository.findByReadingStatus(readingStatus);
    }
}
