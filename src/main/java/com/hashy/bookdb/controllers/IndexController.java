package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Genre;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookServiceImpl;
import com.hashy.bookdb.services.GenreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class IndexController {

    private final BookServiceImpl bookService;
    private final GenreServiceImpl genreService;

    public IndexController(BookServiceImpl bookService, GenreServiceImpl genreService) {
        this.bookService = bookService;
        this.genreService = genreService;
    }

    @GetMapping({"/", "index", "index.html"})
    public String getHomePage(Model model, HttpServletRequest request){
        Set<Book> books = bookService.findAll();
        if(books != null){
            model.addAttribute("books",books);
        }
        model.addAttribute("currentUser", SessionHelper.getCurrentUser(request));
        return "index";
    }

    @GetMapping("/top10")
    public String getTop10Books(Model model, HttpServletRequest request){
        Set<Book> books = bookService.findTop10();
        if (books != null){
            model.addAttribute("books",books);
        }
        model.addAttribute("currentUser",SessionHelper.getCurrentUser(request));
        return "index";
    }

    @GetMapping("/genre/{name}")
    public String getBooksByGenre(@PathVariable("name") String type, Model model, HttpServletRequest request){
        Set<Book> books = genreService.findByType(type).getBooks();
        model.addAttribute("books",books);
        model.addAttribute("currentUser",SessionHelper.getCurrentUser(request));
        return "index";
    }

    @ResponseBody
    @GetMapping("/check")
    public Set<Genre> checkGenres(HttpServletRequest request){
        if(SessionHelper.getGenres(request) == null){
            Set<Genre> genres = genreService.findAll();
            Set<String> genreNames = new HashSet<>();
            genres.forEach(genre -> genreNames.add(genre.getType()));
            SessionHelper.setGenres(request,genreNames);
        }
        return (Set<Genre>) SessionHelper.getGenres(request);
    }
}
