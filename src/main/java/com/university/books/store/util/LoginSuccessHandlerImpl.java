package com.university.books.store.util;

import com.university.books.store.model.entity.BagDetailsEntity;
import com.university.books.store.model.entity.BagEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.BagService;
import com.university.books.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aleksandr on 6/6/2017.
 */
@Component
public class LoginSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        UserEntity user=getUserFromDB(authentication.getName());

        HttpSession session=httpServletRequest.getSession();
        session.setAttribute("userId",user.getUserId());
        setBagInSessionFromDB(user,session);
        httpServletResponse.sendRedirect("/");
    }

    private UserEntity getUserFromDB(String username) {
        UserEntity userByLogin = userService.findByLogin(username);
        UserEntity userByEmail = userService.findByEmail(username);
        if (userByLogin != null) {
            return userByLogin;
        }
        if (userByEmail != null) {
            return userByEmail;
        }
        return null;
    }

    private void setBagInSessionFromDB(UserEntity user, HttpSession session){
        if(user.getBag()!=null){
            session.setAttribute("bag",user.getBag());
        } else {
            BagEntity bag=new BagEntity();
            bag.setBagId(user.getUserId());
            bag.setUser(user);
            bag.setBagDetails(new ArrayList<BagDetailsEntity>());
            session.setAttribute("bag",new BagEntity());
        }
    }
}
