package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.DiscountEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.DiscountDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("discountRepository")
public class DiscountDAOImpl extends AbstractDao<Long,DiscountEntity> implements DiscountDAO {
    @Override
    public DiscountEntity findById(long id) {
        DiscountEntity discount=getByKey(id);
        return discount;
    }

    @Override
    public List<DiscountEntity> findAllDiscounts(int begPos, int limit) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<DiscountEntity> discounts = (List<DiscountEntity>) criteria.list();
        return discounts;
    }

    @Override
    public List<DiscountEntity> findNewestDiscounts(int begPos, int limit) {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.desc("startDate"))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<DiscountEntity> discounts = (List<DiscountEntity>) criteria.list();
        return discounts;
    }

    @Override
    public void save(DiscountEntity discount) {
        persist(discount);
    }

    @Override
    public void update(DiscountEntity discount) {
        super.update(discount);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("discount_id",id));
        DiscountEntity discount=(DiscountEntity) criteria.uniqueResult();
        delete(discount);
    }
}
