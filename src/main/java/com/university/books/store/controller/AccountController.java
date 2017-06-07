package com.university.books.store.controller;

import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;

/**
 * Created by Aleksandr on 6/8/2017.
 */
@Controller
@RequestMapping("/")
public class AccountController {
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

    //Account controller
    @RequestMapping(value = {"/account/"}, method = RequestMethod.GET)
    public String accountPage(ModelMap model, HttpSession session) {
        Long userId=(Long)session.getAttribute("userId");
        UserEntity user=userService.findUserById(userId);
        model.addAttribute("userLogin",user.getLogin());
        return "account";
    }
}
