package com.hashy.bookdb.helpers;

import com.hashy.bookdb.domain.CurrentUser;
import com.hashy.bookdb.domain.Genre;
import com.hashy.bookdb.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class SessionHelper {

    public static CurrentUser getCurrentUser(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session != null){
            return (CurrentUser) session.getAttribute("currentUser");
        }
        return null;
    }

    public static int setCurrentUser(HttpServletRequest request, User user){
        HttpSession session = request.getSession();
        if(session != null){
            session.setAttribute("currentUser",new CurrentUser(user.getFirstName(),user.getId()));
            return 1;
        }
        return -1;
    }
}
