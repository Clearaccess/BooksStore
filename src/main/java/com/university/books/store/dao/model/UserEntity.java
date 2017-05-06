package com.university.books.store.dao.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "User", schema = "public", catalog = "postgres")
public class UserEntity {
    private int userId;
    private String firstName;
    private String lastName;
    private Integer gender;
    private String login;
    private String password;
    private String email;
    private String phone;
    private Collection<AddressEntity> addressesByUserId;
    private Collection<OrderEntity> ordersByUserId;
    private Collection<ReviewEntity> reviewsByUserId;
    private Collection<RoleEntity> rolesByUserId;
    private Collection<BookEntity> wishBooks;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = -1)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = -1)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "login", nullable = false, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = -1)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<AddressEntity> getAddressesByUserId() {
        return addressesByUserId;
    }

    public void setAddressesByUserId(Collection<AddressEntity> addressesByUserId) {
        this.addressesByUserId = addressesByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<OrderEntity> getOrdersByUserId() {
        return ordersByUserId;
    }

    public void setOrdersByUserId(Collection<OrderEntity> ordersByUserId) {
        this.ordersByUserId = ordersByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<ReviewEntity> getReviewsByUserId() {
        return reviewsByUserId;
    }

    public void setReviewsByUserId(Collection<ReviewEntity> reviewsByUserId) {
        this.reviewsByUserId = reviewsByUserId;
    }

    @ManyToMany
    @JoinTable(name = "User_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    public Collection<RoleEntity> getRolesByUserId() {
        return rolesByUserId;
    }

    public void setRolesByUserId(Collection<RoleEntity> rolesByUserId) {
        this.rolesByUserId = rolesByUserId;
    }

    @ManyToMany
    @JoinTable(name = "Wish",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="book_id"))
    public Collection<BookEntity> getWishBooks() {
        return wishBooks;
    }

    public void setWishBooks(Collection<BookEntity> wishesByUserId) {
        this.wishBooks = wishBooks;
    }
}
