package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.BookList;
import com.hashy.bookdb.domain.CurrentUser;
import com.hashy.bookdb.domain.ReadingStatus;
import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.BookListServiceImpl;
import com.hashy.bookdb.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@RequestMapping("/profile")
@Controller
public class ProfileController {

    private final UserServiceImpl userService;
    private final BookListServiceImpl bookListService;

    public ProfileController(UserServiceImpl userService, BookListServiceImpl bookListService) {
        this.userService = userService;
        this.bookListService = bookListService;
    }

    @GetMapping({"/","","/profile/profile"})
    public String getProfile(Model model, HttpServletRequest request){
        CurrentUser currentUser = SessionHelper.getCurrentUser(request);
        if(currentUser == null)
            return "redirect:index";

        User user = userService.findByUserId(currentUser.getUserId());
        if(user == null){
            request.getSession().invalidate();
            return "redirect:login";
        }
        model.addAttribute("currentUser",currentUser);
        model.addAttribute("user",user);
        return "profile/profile";
    }

    @PostMapping("updateprofile")
    public String update(@ModelAttribute("user") User user, HttpServletRequest request){
        User oldUser = userService.findByUserId(SessionHelper.getCurrentUser(request).getUserId());
        user.setId(oldUser.getId());
        user.setBookLists(oldUser.getBookLists());
        user.setComments(oldUser.getComments());
        userService.saveUser(user);
        SessionHelper.setCurrentUser(request,user);
        return "redirect:/profile/";
    }

    @GetMapping("/booklist")
    public String getBookList(@RequestParam("type") String type, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = userService.findByUserId(SessionHelper.getCurrentUser(request).getUserId());
        if (user == null) {
            return "redirect:/login";
        }
        BookList bookList = new BookList();
        ReadingStatus readingStatus;
        switch (type.toUpperCase(Locale.ROOT)) {
            case "READ":
                readingStatus = ReadingStatus.READ;
                model.addAttribute("listname","READ");
                break;
            case "READING":
                readingStatus = ReadingStatus.READING;
                model.addAttribute("listname","READING");
                break;
            case "WILLREAD":
                readingStatus = ReadingStatus.WANT_TO_READ;
                model.addAttribute("listname","WILLREAD");
                break;
            default:
                readingStatus = null;
                break;
        }
        for (BookList bl :
                user.getBookLists()) {
            if (bl.getReadingStatus() == readingStatus) {
                bookList = bl;
                break;
            }
        }
        model.addAttribute("books",bookList.getBooks());
        model.addAttribute("currentUser",SessionHelper.getCurrentUser(request));
        return "/profile/booklist";
    }
}
