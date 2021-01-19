package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Author;
import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.Genre;
import com.hashy.bookdb.repositories.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Book> findByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public Set<Book> findAll() {
        Set<Book> books = new HashSet<>();
        books = bookRepository.findAllByOrderByIdAsc();
        return books;
    }

    @Override
    public Set<Book> findByGenre(Genre genre) {
        Set<Book> books = new HashSet<>();
        bookRepository.findByGenre(genre).forEach(books::add);
        return books;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Set<Book> findTop10() {
        return bookRepository.findTop10ByOrderByRatingDesc();
    }
}
