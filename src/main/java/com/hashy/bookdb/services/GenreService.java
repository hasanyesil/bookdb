package com.hashy.bookdb.services;

import com.hashy.bookdb.domain.Genre;

import java.util.Set;

public interface GenreService {
    Genre save(Genre genre);
    Set<Genre> findAll();
}
