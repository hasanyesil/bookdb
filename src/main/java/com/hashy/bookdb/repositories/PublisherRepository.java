package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
    Publisher findByBooks(Book book);
}
