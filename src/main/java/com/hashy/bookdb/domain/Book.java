package com.hashy.bookdb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String isbn;
    private Float rating;

    @Lob
    private String description;

    @Lob
    private byte[] pic;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Author author;

    @ManyToOne
    private BookList bookList;

    @ManyToOne
    private Publisher publisher;
}
