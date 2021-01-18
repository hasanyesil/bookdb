package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Comment;
import com.hashy.bookdb.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CommentServiceImpl  implements CommentService{
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Set<Comment> findByBook(Book book) {
        return commentRepository.findByBook(book);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
