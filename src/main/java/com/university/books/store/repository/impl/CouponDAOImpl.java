package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.CouponEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.CouponDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("couponRepositoryDao")
public class CouponDAOImpl extends AbstractDao<Integer,CouponEntity> implements CouponDAO {
    @Override
    public CouponEntity findById(int id) {
        CouponEntity coupon=getByKey(id);
        return coupon;
    }

    @Override
    public List<CouponEntity> findAllCoupons() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<CouponEntity> coupons = (List<CouponEntity>) criteria.list();
        return coupons;
    }

    @Override
    public void save(CouponEntity coupon) {
        persist(coupon);
    }

    @Override
    public void change(CouponEntity coupon) {
        update(coupon);
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("coupon_id",id));
        CouponEntity coupon=(CouponEntity) criteria.uniqueResult();
        delete(coupon);
    }
}
