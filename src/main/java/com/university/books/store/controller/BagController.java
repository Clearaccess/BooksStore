package com.university.books.store.controller;

import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.service.*;
import com.university.books.store.service.filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Aleksandr on 6/4/2017.
 */
@Controller
@RequestMapping("/")
public class BagController {
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

    /*
    //Book controller
    @RequestMapping(value = {"/book/{id}/"}, method = RequestMethod.GET)
    public String bookPage(ModelMap model, @PathVariable(value = "id") long bookId) {
        BookEntity book = bookService.findById(bookId);
        List<BookEntity> relatedBooks = bookService.findAllBooks(0, 10, new BookFilter().setCategory(book.getCategory().getCategoryId()));
        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", relatedBooks);
        return "book";
    }*/
}
