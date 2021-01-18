package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Genre;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book,Long> {

    Set<Book> findByGenresContains(Genre genre);
}
