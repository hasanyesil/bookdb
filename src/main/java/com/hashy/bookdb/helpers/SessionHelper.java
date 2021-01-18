package com.hashy.bookdb.helpers;

import com.hashy.bookdb.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionHelper {

    public static User getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            return (User) session.getAttribute("currentUser");
        }
        return null;
    }

    public static int setCurrentUser(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        if(session != null){
            session.setAttribute("currentUser",user);
            return 1;
        }
        return -1;
    }
}
