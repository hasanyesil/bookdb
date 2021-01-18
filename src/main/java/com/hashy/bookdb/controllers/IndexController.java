package com.hashy.bookdb.controllers;

import com.hashy.bookdb.helpers.SessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping({"/", "index", "index.html"})
    public String getHomePage(Model model, HttpServletRequest request){
        model.addAttribute("currentUser", SessionHelper.getCurrentUser(request));
        return "index";
    }

}
