package com.hashy.bookdb.controllers;

import com.hashy.bookdb.domain.User;
import com.hashy.bookdb.helpers.SessionHelper;
import com.hashy.bookdb.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/profile")
@Controller
public class ProfileController {

    private final UserServiceImpl userService;

    public ProfileController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping({"/","","/profile/profile"})
    public String getProfile(Model model, HttpServletRequest request){
        User currentUser = SessionHelper.getCurrentUser(request);
        if(currentUser == null)
            return "redirect:index";

        User user = currentUser;
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
        User oldUser = SessionHelper.getCurrentUser(request);
        user.setId(oldUser.getId());
        user.setBookLists(oldUser.getBookLists());
        user.setComments(oldUser.getComments());
        userService.saveUser(user);
        SessionHelper.setCurrentUser(request,user);
        return "profile/profile";
    }
}
