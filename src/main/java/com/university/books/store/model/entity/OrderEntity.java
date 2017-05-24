package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "order", schema = "books_store", catalog = "")
public class OrderEntity {
    private long orderId;
    private Calendar orderDate;
    private Calendar executedDate;
    private int status;
    private double totalPrice;
    private String country;
    private String city;
    private String street;
    private String home;
    private String apartment;
    private String floor;
    private String postCode;
    private UserEntity user;
    private PaymentEntity payment;
    private CouponEntity coupon;
    private Collection<OrderDetailEntity> orderDetails;
    private Collection<OrderPaymentEntity> orderPayments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "executed_date", nullable = false)
    public Calendar getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Calendar executedDate) {
        this.executedDate = executedDate;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "total_price", nullable = false, precision = 0)
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = false, length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "home", nullable = true, length = 50)
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Basic
    @Column(name = "apartment", nullable = true, length = 50)
    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Basic
    @Column(name = "floor", nullable = true, length = 50)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "post_code", nullable = false, length = 50)
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<OrderPaymentEntity> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(Collection<OrderPaymentEntity> orderPayments) {
        this.orderPayments = orderPayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (user.getUserId() != that.user.getUserId()) return false;
        if (payment.getPaymentId() != that.payment.getPaymentId()) return false;
        if (status != that.status) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (coupon.getCouponId() != that.coupon.getCouponId()) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (executedDate != null ? !executedDate.equals(that.executedDate) : that.executedDate != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (postCode != null ? !postCode.equals(that.postCode) : that.postCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (orderId ^ (orderId >>> 32));
        result = 31 * result + (int) (user.getUserId() ^ (user.getUserId() >>> 32));
        result = 31 * result + (int) (payment.getPaymentId() ^ (payment.getPaymentId() >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (executedDate != null ? executedDate.hashCode() : 0);
        result = 31 * result + status;
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (postCode != null ? postCode.hashCode() : 0);
        result = 31 * result + (int) (coupon.getCouponId() ^ (coupon.getCouponId() >>> 32));
        return result;
    }
}
