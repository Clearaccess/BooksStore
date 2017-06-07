package com.university.books.store.controller;

/**
 * Created by Aleksandr on 5/31/2017.
 */
import com.university.books.store.model.dto.UserInfoDTO;
import com.university.books.store.model.entity.AddressEntity;
import com.university.books.store.model.entity.RoleEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


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

    @Autowired
    EmailService emailService;

    private static final String MESSAGE_RESTORE_SUBJECT="Ваши данные для входа на сайт BooksStore.com";
    private static final String MESSAGE_RESTORE_TEXT="Данные для входа на сайт. \nЛогин: %s\nПароль: %s\n";

    //Authorization controller
    @RequestMapping(value = {"/login/"}, method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
        System.out.println("LOGIN");
        return "login";
    }

    @RequestMapping(value = {"/login/"}, params = {"btnLogin"})
    public String login(ModelMap model) {
        System.out.println("URL: /login/"+ " params: "+"login "+"method: "+"POST");
        return "redirect:/";
    }

    @RequestMapping(value = {"/login/"}, params = {"btnRegister"}, method = RequestMethod.GET)
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
    public String restorePasswordPage(ModelMap model, @RequestParam(value = "email", required = false) String email) {

        System.out.println("URL: /forget/"+ " params: "+"method: "+"GET");

        if(email==null || userService.findByEmail(email)==null){
            model.addAttribute("notice","Пользователя с данным email не существует");
            return "forget";
        }

        UserEntity user=userService.findByEmail(email);

        emailService.sendSimpleMessage(email,MESSAGE_RESTORE_SUBJECT,String.format(MESSAGE_RESTORE_TEXT,user.getLogin(), user.getPassword()));
        return "redirect:/login/";
    }

    @RequestMapping(value = {"/register/"}, method = RequestMethod.GET)
    public String registerPage(ModelMap model) {

        System.out.println("URL: /register/"+ " params: "+"method: "+"GET");

        model.addAttribute("infUser",new UserInfoDTO());

        return "registration";
    }

    @RequestMapping(value = {"/register/"}, params="btnReg", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute(value="infUser") UserInfoDTO infUser, BindingResult bindingResult, ModelMap model) {

        if(bindingResult.hasErrors()){
            return "registration";
        }

        if(existLogin(infUser.getLogin())){
            bindingResult.addError(new ObjectError("login","Данный логин уже существует"));
            return "registration";
        }

        if(existEmail(infUser.getEmail())){
            bindingResult.addError(new ObjectError("email","Данный email уже существует"));
            return "registration";
        }

        UserEntity user=fillUserEntityFromUserInfDTO(infUser);
        userService.save(user);
        System.out.println("URL: /register/"+ " params: "+"btnReg"+"method: "+"POST");
        return "redirect:/login/";
    }

    private boolean existLogin(String login) {

        if (userService.findByLogin(login) == null) {
            return false;
        }

        return true;
    }

    private boolean existEmail(String email) {

        if (userService.findByEmail(email) == null) {
            return false;
        }

        return true;
    }

    private UserEntity fillUserEntityFromUserInfDTO(UserInfoDTO infoUser){
        UserEntity user=new UserEntity();
        //Fill main information
        user.setFirstName(infoUser.getFirstName());
        user.setLastName(infoUser.getLastName());
        user.setLogin(infoUser.getLogin());
        user.setPassword(infoUser.getPassword());
        user.setEmail(infoUser.getEmail());
        user.setPhone(infoUser.getPhone());
        //Add role
        RoleEntity role=new RoleEntity();
        role.setRoleId(2);
        role.setDescription("customer");
        ArrayList<RoleEntity>roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        //Fill address information
        AddressEntity address=new AddressEntity();
        address.setCountry(infoUser.getCountry());
        address.setCity(infoUser.getCity());
        address.setPostCode(infoUser.getPostcode());
        address.setStreet(infoUser.getAddress());
        address.setUser(user);
        ArrayList<AddressEntity> ads=new ArrayList<AddressEntity>();
        ads.add(address);
        user.setAddresses(ads);

        return user;
    }
}
