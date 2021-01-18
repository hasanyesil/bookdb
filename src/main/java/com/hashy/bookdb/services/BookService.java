package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Author;
import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface BookService{
    Set<Book> findByBookList(BookList bookList);
    Book findById(Long id);
    Set<Book> findByAuthor(Author author);
    Set<Book> findAll();
    Set<Book> findByGenre(Genre genre);
    Book save(Book book);
}
