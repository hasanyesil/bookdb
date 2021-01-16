package com.hashy.bookdb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class BookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus;

    @OneToMany(mappedBy = "bookList")
    private Set<Book> books = new HashSet<>();

    @ManyToOne
    private User user;
}
