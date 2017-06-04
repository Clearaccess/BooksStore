package com.university.books.store.controller;

/**
 * Created by Aleksandr on 5/31/2017.
 */

import com.university.books.store.model.dto.BagDTO;
import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.model.entity.CategoryEntity;
import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.*;
import com.university.books.store.service.filter.BookFilter;
import com.university.books.store.util.OrderMap;
import org.hibernate.mapping.Bag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private final String MIN="0";
    private final String MAX="10000";

    //Books controller

    @RequestMapping(value = {"/books/{id}/page/{page}/"
            , "/books/{id}/page/{page}/order/{order}/"
            , "/books/{id}/page/{page}/price/{price}/"
            , "/books/{id}/page/{page}/price/{price}/order/{order}/"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String booksPage(HttpServletRequest req, ModelMap model,
                            @PathVariable(value = "id") Integer categoryId,
                            @PathVariable(value = "page") Integer page,
                            @PathVariable(value = "order", required = false) Integer order,
                            @PathVariable(value = "price", required = false) String price,
                            @RequestParam(name = "minPrice", defaultValue = MIN) Integer minPrice,
                            @RequestParam(name = "maxPrice", defaultValue = MAX) Integer maxPrice) {

        String categoryName = getNameCategory(categoryId);
        List<CategoryEntity> categories = categoryService.findAllCategories();

        int limitPrice=(int)Math.ceil(bookService.maxPriceBooksByCategoryId(categoryId));
        minPrice=getMinPrice(price,minPrice);
        maxPrice=getMaxPrice(price,limitPrice);

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
        model.addAttribute("price", renderPrice(minPrice,maxPrice,limitPrice));
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("limitPrice", limitPrice);
        model.addAttribute("mapOrders", OrderMap.orders);

        System.out.println("Page: " + page);
        System.out.println("Category: " + categoryId);
        System.out.println("Order: " + order);
        System.out.println("Price: " + renderPrice(minPrice,maxPrice, limitPrice));
        System.out.println("MinPrice: " + minPrice);
        System.out.println("MaxPrice: " + maxPrice);
        System.out.println(req.getRequestURI());
        return "books";
    }


    @RequestMapping(value = {"/book/{id}/"}, method = RequestMethod.GET)
    public String bookPage(ModelMap model, @PathVariable(value = "id") long bookId) {
        BookEntity book = bookService.findById(bookId);
        List<BookEntity> relatedBooks = bookService.findAllBooks(0, 10, new BookFilter().setCategory(book.getCategory().getCategoryId()));
        model.addAttribute("book", book);
        model.addAttribute("relatedBooks", relatedBooks);
        return "book";
    }

    @RequestMapping(value = {"/book/{id}"}, method = RequestMethod.POST, params = {"buy"})
    public String bookPageBuy(HttpSession session,ModelMap model, @PathVariable(value = "id") long bookId) {
        BagDTO bag;
        if(session.getAttribute("bag")==null){
            bag=new BagDTO();
        } else {
            bag=(BagDTO) session.getAttribute("bag");
        }

        BookEntity book=bookService.findById(bookId);
        if(!bag.getBooks().containsKey(bookId)) {
            bag.getBooks().put(bookId, 1);
        }

        System.out.println("Book added in bag");
        return "book";
    }

    @RequestMapping(value = {"/book/{id}"}, method = RequestMethod.POST, params = {"addBook"})
    public String bookPageAddWish(HttpSession session, ModelMap model, @PathVariable(value = "id") long bookId) {
        BookEntity book = bookService.findById(bookId);
        System.out.println("Book added in wish list");
        return "book";
    }

    @RequestMapping(value = {"/book/{id}"}, method = RequestMethod.POST, params = {"delBook"})
    public String bookDelWish(HttpSession session, ModelMap model, @PathVariable(value = "id") long bookId) {
        System.out.println("Book deleted from wish list");
        return "book";
    }

    private BookFilter initBooksFilter(int categoryId, String price) {
        BookFilter filter = new BookFilter();

        filter.setCategory(categoryId);
        if (price!=null) {
            String[] prices = price.split("-");
            filter.setMinPrice(Integer.parseInt(prices[0]))
                    .setMaxPrice(Integer.parseInt(prices[1]));
        }

        return filter;
    }

    private List<BookEntity> getBooks(int begPos, int limit, BookFilter filter, Integer order) {

        if (order==null){
            return bookService.findAllBooks(begPos,limit,filter);
        } else if(order==1){
            return bookService.findAllMostPopularBooks(begPos,limit,filter);
        } else if(order==2){
            return bookService.findAllNewestBooks(begPos,limit,filter);
        } else {
            return bookService.findAllCheapBooks(begPos,limit,filter);
        }
    }

    private int calculateBooks(BookFilter filter) {
        return bookService.countAllBooksByFilter(filter);
    }

    private String getNameCategory(int categoryId){

        if(categoryId!=0) {
            return categoryService.findById(categoryId).getName();
        }

        return "Все книги";
    }

    private String renderPrice(int minPrice, int maxPrice, int limitPrice){
        if(minPrice!=Integer.parseInt(MIN) || maxPrice!=limitPrice){
            return Integer.toString(minPrice)+'-'+Integer.toString(maxPrice);
        }
        return null;
    }

    private Integer getMinPrice(String price, int defaultValue){
        if (price!=null) {

            String[] prices = price.split("-");
            return Integer.parseInt(prices[0]);
        }

        return defaultValue;
    }

    private Integer getMaxPrice(String price, int defaultValue){
        if (price!=null) {

            String[] prices = price.split("-");
            return Integer.parseInt(prices[1]);
        }

        return defaultValue;
    }
}
