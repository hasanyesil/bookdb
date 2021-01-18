package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Publisher;

import java.util.Set;

public interface PublisherService {
    Set<Publisher> findAll();
    Publisher findByBook(Book book);
    Publisher save(Publisher publisher);
}
