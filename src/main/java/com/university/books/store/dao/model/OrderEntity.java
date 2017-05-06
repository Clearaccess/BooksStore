package com.university.books.store.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Order", schema = "public", catalog = "postgres")
public class OrderEntity {
    private int orderId;
    private long userId;
    private long paymentId;
    private Date orderDate;
    private Date executedDate;
    private int status;
    private double totalPrice;
    private String country;
    private String city;
    private String street;
    private String home;
    private String apartment;
    private Integer floor;
    private int postCode;
    private Long couponId;
    private UserEntity userByUserId;
    private PaymentEntity paymentByPaymentId;
    private CouponEntity couponByCouponId;
    private Collection<OrderDetailEntity> orderDetailsByOrderId;
    private Collection<OrderPaymentEntity> orderPaymentsByOrderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "payment_id", nullable = false)
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "executed_date", nullable = true)
    public Date getExecutedDate() {
        return executedDate;
    }

    public void setExecutedDate(Date executedDate) {
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
    @Column(name = "country", nullable = false, length = -1)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = false, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "street", nullable = false, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "home", nullable = true, length = -1)
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Basic
    @Column(name = "apartment", nullable = true, length = -1)
    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Basic
    @Column(name = "floor", nullable = true)
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "post_code", nullable = false)
    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Basic
    @Column(name = "coupon_id", nullable = true)
    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (userId != that.userId) return false;
        if (paymentId != that.paymentId) return false;
        if (status != that.status) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (postCode != that.postCode) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (executedDate != null ? !executedDate.equals(that.executedDate) : that.executedDate != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (couponId != null ? !couponId.equals(that.couponId) : that.couponId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (paymentId ^ (paymentId >>> 32));
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
        result = 31 * result + postCode;
        result = 31 * result + (couponId != null ? couponId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false)
    public PaymentEntity getPaymentByPaymentId() {
        return paymentByPaymentId;
    }

    public void setPaymentByPaymentId(PaymentEntity paymentByPaymentId) {
        this.paymentByPaymentId = paymentByPaymentId;
    }

    @ManyToOne
    @JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id")
    public CouponEntity getCouponByCouponId() {
        return couponByCouponId;
    }

    public void setCouponByCouponId(CouponEntity couponByCouponId) {
        this.couponByCouponId = couponByCouponId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderDetailEntity> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetailEntity> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderPaymentEntity> getOrderPaymentsByOrderId() {
        return orderPaymentsByOrderId;
    }

    public void setOrderPaymentsByOrderId(Collection<OrderPaymentEntity> orderPaymentsByOrderId) {
        this.orderPaymentsByOrderId = orderPaymentsByOrderId;
    }
}
