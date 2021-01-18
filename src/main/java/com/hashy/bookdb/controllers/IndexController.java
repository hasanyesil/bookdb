package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Controller
public class IndexController {

    private final BookServiceImpl bookService;

    public IndexController(BookServiceImpl bookService) {
        this.bookService = bookService;
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

}
