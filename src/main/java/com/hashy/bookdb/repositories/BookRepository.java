package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Author;
import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book,Long> {

    Set<Book> findByBookList(BookList bookList);
    Set<Book> findByAuthor(Author author);
    Set<Book> findByGenre(Genre genre);
    Set<Book> findAllByOrderByIdAsc();
    Set<Book> findTop10ByOrderByRatingDesc();
}
