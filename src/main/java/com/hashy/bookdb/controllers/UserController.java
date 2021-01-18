package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.CurrentUser;
import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String login(HttpServletRequest request){
        if(SessionHelper.getCurrentUser(request) != null){
            return "redirect:index";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") final String username, @RequestParam("password") final String password, HttpServletRequest request){
        User user = userService.findByUserNameAndPassword(username,password);
        if(user == null){
            return "login";
        }
        SessionHelper.setCurrentUser(request,user);
        return "redirect:index";
    }

    @GetMapping("register")
    public String getRegister(Model model, HttpServletRequest request){
        if(SessionHelper.getCurrentUser(request) != null){
            return "redirect:index";
        }
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }
        userService.saveUser(user);
        return "redirect:login";
    }
}
