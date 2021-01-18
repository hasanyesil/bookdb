package com.hashy.bookdb.bootstrap;

import com.hashy.bookdb.domain.*;
import com.hashy.bookdb.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserServiceImpl userService;
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final GenreServiceImpl genreService;
    private final PublisherServiceImpl publisherService;
    private final CommentServiceImpl commentService;

    public DataLoader(UserServiceImpl userService, BookServiceImpl bookService, AuthorServiceImpl authorService, GenreServiceImpl genreService, PublisherServiceImpl publisherService, CommentServiceImpl commentService) {
        this.userService = userService;
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.publisherService = publisherService;
        this.commentService = commentService;
    }


    @Override
    public void run(String... args) throws Exception {

        for (int i = 1; i<30; i++) {
            Genre genre = new Genre();
            genre.setId((long) i);
            genre.setType("Test Genre" + i);


            Book book = new Book();
            book.setId((long) i);
            book.setName("Test Book"+i);
            book.setRating(4.3f);
            book.setIsbn("1111111");
            book.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed suscipit quis metus in fermentum. Nullam facilisis nisl at ligula commodo, sit amet venenatis lorem tempus. Aenean ac ex lorem. Donec ac sem a diam bibendum efficitur. Donec massa ligula, lobortis a quam quis, lacinia molestie odio. Donec fringilla dapibus dignissim. Pellentesque convallis tellus gravida, ultrices neque quis, tempus eros. Maecenas dolor nibh, maximus vitae massa at, scelerisque laoreet sem.");

            Comment comment = new Comment();
            comment.setId((long) i);
            comment.setMessage("comment" + 1);

            Author author = new Author();
            author.setId((long) i);
            author.setName("Test");
            author.setLastName("Author"+i);

            book.setAuthor(author);
            book.setGenre(genre);

            User user = new User();
            user.setId((long) i);
            user.setUserName("test"+i);
            user.setPassWord("test"+i);
            user.setFirstName("test"+i);
            user.setLastName("test"+i);

            Publisher publisher = new Publisher();
            publisher.setId((long) i);
            publisher.setName("Test Publisher"+i);

            authorService.save(author);
            genreService.save(genre);
            book.setGenre(genre);
            bookService.save(book);
            comment.setBook(book);
            commentService.save(comment);
            book.setComments(new HashSet<>(Arrays.asList(comment)));
            genre.setBooks(new HashSet<>(Arrays.asList(book)));
            genreService.save(genre);
            author.setBooks(new HashSet<>(Arrays.asList(book)));
            publisher.setBooks(new HashSet<>(Arrays.asList(book)));
            publisherService.save(publisher);
            book.setPublisher(publisher);
            bookService.save(book);
            authorService.save(author);
            user.setComments(new HashSet<>(Arrays.asList(comment)));
            userService.saveUser(user);
            comment.setUser(user);
            commentService.save(comment);
        }
    }
}
