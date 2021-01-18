package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.Book;
import com.hashy.bookdb.domain.Comment;
import com.hashy.bookdb.domain.CurrentUser;
import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookServiceImpl;
import com.hashy.bookdb.services.CommentServiceImpl;
import com.hashy.bookdb.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BookController {
    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;
    private final CommentServiceImpl commentService;

    public BookController(BookServiceImpl bookService, UserServiceImpl userService, CommentServiceImpl commentService) {
        this.bookService = bookService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") String id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = bookService.findById(Long.parseLong(id));
        User currentUser = SessionHelper.getCurrentUser(request);
        if(book == null){
            response.sendRedirect("/login");
            return null;
        }
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("book",book);
        model.addAttribute("comment",new Comment());
        return "book";
    }

    @PostMapping("/book/{id}/addcomment")
    public void newComment(@PathVariable("id") String bookId, @ModelAttribute("comment") Comment comment, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User currentUser = SessionHelper.getCurrentUser(request);
        if(currentUser == null){
            response.sendRedirect("/index");
            return;
        }
        if(comment.getMessage().equals("")){
            response.sendRedirect("/book?id=" + bookId);
            return;
        }
        Book book = bookService.findById(Long.parseLong(bookId));

        Comment comment1 = new Comment();
        comment1.setMessage(comment.getMessage());
        comment1.setBook(book);
        comment1.setUser(currentUser);

        book.getComments().add(commentService.save(comment1));

        bookService.save(book);
        response.sendRedirect("/book/" + bookId);
    }
}
