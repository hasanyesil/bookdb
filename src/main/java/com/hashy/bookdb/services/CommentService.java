package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Comment;

import java.util.Set;

public interface CommentService {
    Set<Comment> findByBook(Book book);
    Comment save(Comment comment);
}
