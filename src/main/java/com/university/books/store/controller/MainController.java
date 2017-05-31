package com.university.books.store.controller;

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
public class MainController {
    /*
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	*/

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

    // Home controller
    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String homePage(ModelMap model) {
        List<BookEntity> saleBooks = bookService.findAllNewestDiscountBooks(0, 6);
        model.addAttribute("saleBooks", saleBooks);

        List<BookEntity> popularBooks = bookService.findAllMostPopularBooks(0, 10, new BookFilter());
        model.addAttribute("popularBooks", popularBooks);

        List<BookEntity> newestBooks = bookService.findAllNewestBooks(0, 10, new BookFilter());
        model.addAttribute("newestBooks", newestBooks);

        model.addAttribute("favouriteBooks", newestBooks);
        return "index";
    }

    //Bag controller
    @RequestMapping(value = {"/bag/"}, method = RequestMethod.GET)
    public String bagPage(ModelMap model) {
        int count = bookService.countAllBooks();
        System.out.println(count);
        return "bag";
    }

    //Checkout controller
    @RequestMapping(value = {"/checkout/"}, method = RequestMethod.GET)
    public String checkoutPage(ModelMap model) {
        int count = bookService.countAllBooks();
        System.out.println(count);
        return "checkout";
    }

    //Checkout controller
    @RequestMapping(value = {"/test/"}, method = RequestMethod.GET)
    public String testPage(ModelMap model) {
        int count = bookService.countAllBooks();
        System.out.println(count);
        return "payment";
    }

    //Account controller
    @RequestMapping(value = {"/account/{id}/"}, method = RequestMethod.GET)
    public String accountPage(ModelMap model) {
        int count = bookService.countAllBooks();
        System.out.println(count);
        return "account";
    }

    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        //User user = new User();
        //model.addAttribute("user", user);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid UserEntity user, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [Users].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		/*if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);*/

        model.addAttribute("success", "Users " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        //return "success";
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String ssoId, ModelMap model) {
        //UserDTO user = userService.findBySSO(ssoId);
        //model.addAttribute("user", user);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{ssoId}"}, method = RequestMethod.POST)
    public String updateUser(@Valid UserEntity user, BindingResult result,
                             ModelMap model, @PathVariable String ssoId) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a Users.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        //userService.updateUser(user);

        model.addAttribute("success", "Users " + user.getFirstName() + " " + user.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "registrationsuccess";
    }


    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-user-{ssoId}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        //userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


}