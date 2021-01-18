package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.CurrentUser;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookController {
    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public String getBook(@RequestParam String id, Model model, HttpServletRequest request){
        Book book = bookService.findById(Long.parseLong(id));
        CurrentUser currentUser = SessionHelper.getCurrentUser(request);
        if(book == null){
            return "redirect:index";
        }
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("book",book);
        return "book";
    }
}
