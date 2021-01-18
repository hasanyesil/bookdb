package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
