package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.*;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookListServiceImpl;
import com.hashy.bookdb.services.BookServiceImpl;
import com.hashy.bookdb.services.CommentServiceImpl;
import com.hashy.bookdb.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Controller
public class BookController {
    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;
    private final CommentServiceImpl commentService;
    private final BookListServiceImpl bookListService;

    public BookController(BookServiceImpl bookService, UserServiceImpl userService, CommentServiceImpl commentService, BookListServiceImpl bookListService) {
        this.bookService = bookService;
        this.userService = userService;
        this.commentService = commentService;
        this.bookListService = bookListService;
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") String id, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Book book = bookService.findById(Long.parseLong(id));
        CurrentUser currentUser = SessionHelper.getCurrentUser(request);
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
        CurrentUser user = SessionHelper.getCurrentUser(request);
        if(user == null){
            response.sendRedirect("/index");
            return;
        }
        if(comment.getMessage().equals("")){
            response.sendRedirect("/book?id=" + bookId);
            return;
        }
        Book book = bookService.findById(Long.parseLong(bookId));
        User currentUser = userService.findByUserId(user.getUserId());

        Comment comment1 = new Comment();
        comment1.setMessage(comment.getMessage());
        comment1.setBook(book);
        comment1.setUser(currentUser);

        book.getComments().add(commentService.save(comment1));

        bookService.save(book);
        response.sendRedirect("/book/" + bookId);
    }

    @GetMapping("/book/{id}/addtolist")
    @ResponseBody
    public String addToList(@PathVariable("id") int id, @RequestParam("type") String type, HttpServletRequest request){
        CurrentUser currentUser = SessionHelper.getCurrentUser(request);
        if(currentUser == null)
            return "redirect:/login";
        User user = userService.findByUserId(currentUser.getUserId());
        Book book = bookService.findById((long)id);
        ReadingStatus readingStatus;
        switch (type.toUpperCase(Locale.ROOT)){
            case "READ":
                readingStatus = ReadingStatus.READ;
                break;
            case "READING":
                readingStatus = ReadingStatus.READING;
                break;
            case "WILLREAD":
                readingStatus = ReadingStatus.WANT_TO_READ;
                break;
            default:
                readingStatus = null;
                break;
        }
        BookList bookList = bookListService.findByUserAndReadingStatus(user,readingStatus);
        if(bookList == null) {
            bookList = new BookList();
            bookList.setUser(user);
            bookList.setBooks(new HashSet<>(Arrays.asList(book)));
            bookList.setReadingStatus(readingStatus);
            book.getBookLists().add(bookListService.save(bookList));
            bookService.save(book);
        }else{
            book.getBookLists().add(bookList);
            bookList.getBooks().add(book);
            bookService.save(book);
            bookListService.save(bookList);
        }
        return type;
    }
}
