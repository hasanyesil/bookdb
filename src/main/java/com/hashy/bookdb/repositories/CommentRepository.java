package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CommentRepository extends CrudRepository<Comment,Long> {
    Set<Comment> findByBook(Book book);
}
