package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends CrudRepository<Genre,Long> {
    Genre findByType(String name);
}
