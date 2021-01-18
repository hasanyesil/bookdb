package com.hashy.bookdb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String passWord;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user",fetch = FetchType.EAGER)
    private Set<BookList> bookLists = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Comment> comments = new HashSet<>();
}
