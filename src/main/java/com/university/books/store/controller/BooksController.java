package com.university.books.store.controller;

/**
 * Created by Aleksandr on 5/31/2017.
 */

import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.model.entity.CategoryEntity;
import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.*;
import com.university.books.store.service.filter.BookFilter;
import com.university.books.store.util.OrderMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
public class BooksController {
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

    //Books controller
    @RequestMapping(value = {"/books/{id}/page/{page}/", "/books/{id}/page/{page}/order/{orderId}/", "/books/{id}/page/{page}/price/{priceId}/", "/books/{id}/page/{page}/price/{priceId}/order/{orderId}/"}, method = RequestMethod.GET)
    public String booksPage(HttpServletRequest req, ModelMap model,
                            @PathVariable(value = "id") int categoryId,
                            @PathVariable(value = "page") int page,
                            @RequestParam(value = "priceId", defaultValue = "") String price,
                            @RequestParam(value = "orderId", defaultValue = "") String order) {

        String categoryName = "Все книги";
        if (categoryId != 0) {
            categoryName = categoryService.findById(categoryId).getName();
        }
        List<CategoryEntity> categories = categoryService.findAllCategories();

        BookFilter bookFilter = initBooksFilter(categoryId,price);
        int countBooks = calculateBooks(bookFilter);
        int limit = 12;
        int countPages = countBooks / limit + (countBooks % limit != 0 ? 1 : 0);


        List<BookEntity> books = getBooks((page - 1) * limit, limit, bookFilter, order);

        model.addAttribute("countBooks", countBooks);
        model.addAttribute("countPages", countPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("limitBooksOnPage", limit);
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        model.addAttribute("currentCategory", categoryId);
        model.addAttribute("currentCategoryName", categoryName);
        model.addAttribute("order", order);
        model.addAttribute("mapOrders", OrderMap.orders);

        System.out.println("Page: " + page);
        System.out.println("Category: " + categoryId);
        System.out.println("Order: "+order);
        System.out.println("Price: "+price);
        System.out.println(req.getRequestURI());
        return "books";
    }

    //Book controller
    @RequestMapping(value = {"/book/{id}/"}, method = RequestMethod.GET)
    public String bookPage(ModelMap model, @PathVariable(value = "id") long bookId) {
        BookEntity book = bookService.findById(bookId);
        List<BookEntity> relatedBooks = bookService.findAllBooks(0, 10, new BookFilter().setCategory(book.getCategory().getCategoryId()));
        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", relatedBooks);
        return "book";
    }

    @RequestMapping(value = {"/book/{id}"}, method = RequestMethod.POST, params = {"buy"})
    public String bookPageBuy(ModelMap model, @PathVariable(value = "id") long bookId) {
        BookEntity book = bookService.findById(bookId);
        return "book";
    }

    private BookFilter initBooksFilter(int categoryId, String price) {
        BookFilter filter = new BookFilter();

        filter.setCategory(categoryId);
        if (!price.isEmpty()) {
            String[] prices = price.split("-");
            filter.setMinPrice(Integer.parseInt(prices[0]))
                    .setMaxPrice(Integer.parseInt(prices[1]));
        }

        return filter;
    }

    private List<BookEntity> getBooks(int begPos, int limit, BookFilter filter, String order) {
        if(order.isEmpty()){
            return bookService.findAllBooks(begPos,limit,filter);
        }

        if(order=="1"){
            return bookService.findAllMostPopularBooks(begPos,limit,filter);
        } else if(order=="2"){
            return bookService.findAllNewestBooks(begPos,limit,filter);
        } else {
            return bookService.findAllCheapBooks(begPos,limit,filter);
        }
    }

    private int calculateBooks(BookFilter filter) {
        return bookService.countAllBooksByFilter(filter);
    }
}
