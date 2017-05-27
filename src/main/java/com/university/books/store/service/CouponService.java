package com.university.books.store.service;

import com.university.books.store.model.entity.CouponEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface CouponService {
    CouponEntity findById(long id);

    List<CouponEntity> findAllCoupons();

    void save(CouponEntity coupon);

    void update(CouponEntity coupon);

    void deleteById(long id);
}
