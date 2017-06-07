package com.university.books.store.model.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Aleksandr on 6/7/2017.
 */
public class UserInfoDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50)
    private String login;

    @NotNull
    @Size(min = 1, max = 50)
    private String password;

    @NotNull
    @Size(min = 1, max = 50)
    private String rePassword;

    @NotNull
    private String email;

    @NotNull
    @Pattern(regexp = "(((\\+7)|(8))([0-9]{10}))")
    private String phone;

    @NotNull
    @Size(min = 1, max = 50)
    private String country;

    @NotNull
    @Size(min = 1, max = 50)
    private String city;

    @NotNull
    @Pattern(regexp = "[0-9]{6}")
    private String postcode;

    @NotNull
    @Size(min = 1, max = 50)
    private String address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
