package com.university.books.store.controller;

/**
 * Created by Aleksandr on 5/31/2017.
 */
import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.*;
import com.university.books.store.service.filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class AuthorizationController {
    @Autowired
    @Qualifier("bagService")
    BagService bagService;

    @Autowired
    @Qualifier("bookService")
    BookService bookService;

    @Autowired
    @Qualifier("categoryService")
    CategoryService categoryService;

    @Autowired
    @Qualifier("couponService")
    CouponService couponService;

    @Autowired
    @Qualifier("discountService")
    DiscountService discountService;

    @Autowired
    @Qualifier("orderService")
    OrderService orderService;

    @Autowired
    @Qualifier("paymentService")
    PaymentService paymentService;

    @Autowired
    @Qualifier("reviewService")
    ReviewService reviewService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    //Authorization controller
    @RequestMapping(value = {"/login/"}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        int count = bookService.countAllBooks();
        System.out.println(count);
        return "login";
    }

    @RequestMapping(value = {"/login/"}, params = {"btnLogin"})
    public String login(ModelMap model) {
        System.out.println("URL: /login/"+ " params: "+"login "+"method: "+"POST");
        return "redirect:/";
    }

    @RequestMapping(value = {"/login/"}, params = {"btnRegister"})
    public String loginToRegisterPage(ModelMap model) {
        System.out.println("URL: /login/"+ " params: "+"register "+"method: "+"POST");
        return "redirect:/register/";
    }

    @RequestMapping(value = {"/forget/"}, method = RequestMethod.GET)
    public String forgetPasswordPage(ModelMap model) {
        System.out.println("URL: /forget/"+ " params: "+"method: "+"GET");
        return "forget";
    }

    @RequestMapping(value = {"/forget/"}, params = {"restore"}, method = RequestMethod.POST)
    public String restorePasswordPage(ModelMap model) {
        System.out.println("URL: /forget/"+ " params: "+"method: "+"GET");
        return "redirect:/login/";
    }

    @RequestMapping(value = {"/register/"}, method = RequestMethod.GET)
    public String registerPage(ModelMap model) {
        System.out.println("URL: /register/"+ " params: "+"method: "+"GET");
        return "registration";
    }

    @RequestMapping(value = {"/register/"}, params="btnReg")
    public String register(ModelMap model) {
        System.out.println("URL: /register/"+ " params: "+"btnReg"+"method: "+"POST");
        return "redirect:/";
    }
}
