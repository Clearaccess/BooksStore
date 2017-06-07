package com.university.books.store.model.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Aleksandr on 5/23/2017.
 */
@Entity
@Table(name = "bag", schema = "books_store", catalog = "")
public class BagEntity {
    private long bagId;
    private double totalPrice;
    private long couponId;
    private int status;
    private UserEntity user;
    private Collection<BagDetailsEntity> bagDetails;

    @Id
    @Column(name = "bag_id", nullable = false)
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name="property", value="user"))
    public long getBagId() {
        return bagId;
    }

    public void setBagId(long bagId) {
        this.bagId = bagId;
    }

    @Basic
    @Column(name = "total_price", nullable = false)
    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "coupon_id", nullable = true)
    public long getCouponId() {
        return this.couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    @Fetch(FetchMode.SELECT)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "bag", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    public Collection<BagDetailsEntity> getBagDetails() {
        return bagDetails;
    }

    public void setBagDetails(Collection<BagDetailsEntity> bagDetails) {
        this.bagDetails = bagDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BagEntity bagEntity = (BagEntity) o;

        if (bagId != bagEntity.bagId) return false;
        if (Double.compare(bagEntity.totalPrice, totalPrice) != 0) return false;
        if (couponId != bagEntity.couponId) return false;
        return status == bagEntity.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (bagId ^ (bagId >>> 32));
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (couponId ^ (couponId >>> 32));
        result = 31 * result + status;
        return result;
    }
}
