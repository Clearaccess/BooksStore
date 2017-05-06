package com.university.books.store.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Coupon", schema = "public", catalog = "postgres")
public class CouponEntity {
    private int couponId;
    private String description;
    private double couponPercentage;
    private Date startDate;
    private Date stopDate;
    private int status;
    private Collection<OrderEntity> ordersByCouponId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id", nullable = false)
    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "coupon_percentage", nullable = false, precision = 0)
    public double getCouponPercentage() {
        return couponPercentage;
    }

    public void setCouponPercentage(double couponPercentage) {
        this.couponPercentage = couponPercentage;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stop_date", nullable = false)
    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CouponEntity that = (CouponEntity) o;

        if (couponId != that.couponId) return false;
        if (Double.compare(that.couponPercentage, couponPercentage) != 0) return false;
        if (status != that.status) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (stopDate != null ? !stopDate.equals(that.stopDate) : that.stopDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = couponId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(couponPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @OneToMany(mappedBy = "couponByCouponId")
    public Collection<OrderEntity> getOrdersByCouponId() {
        return ordersByCouponId;
    }

    public void setOrdersByCouponId(Collection<OrderEntity> ordersByCouponId) {
        this.ordersByCouponId = ordersByCouponId;
    }
}
