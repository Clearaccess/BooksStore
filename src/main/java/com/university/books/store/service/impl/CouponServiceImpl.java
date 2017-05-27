package com.university.books.store.service.impl;

import com.university.books.store.model.entity.CouponEntity;
import com.university.books.store.repository.CouponDAO;
import com.university.books.store.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("couponService")
@Transactional
public class CouponServiceImpl implements CouponService {

    @Autowired
    @Qualifier("couponRepository")
    CouponDAO couponDAO;

    @Override
    public CouponEntity findById(long id) {
        CouponEntity coupon=couponDAO.findById(id);
        return coupon;
    }

    @Override
    public List<CouponEntity> findAllCoupons() {
        List<CouponEntity> coupons=couponDAO.findAllCoupons();
        return  coupons;
    }

    @Override
    public void save(CouponEntity coupon) {
        couponDAO.save(coupon);
    }

    @Override
    public void update(CouponEntity coupon) {
        couponDAO.update(coupon);
    }

    @Override
    public void deleteById(long id) {
        couponDAO.deleteById(id);
    }
}
