package com.hashy.bookdb.repositories;

import com.hashy.bookdb.domain.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre,Long> {

}
