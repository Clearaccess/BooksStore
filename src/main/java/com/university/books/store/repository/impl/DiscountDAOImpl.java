package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.DiscountEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.DiscountDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("discountRepositoryDao")
public class DiscountDAOImpl extends AbstractDao<Integer,DiscountEntity> implements DiscountDAO {
    @Override
    public DiscountEntity findById(int id) {
        DiscountEntity discount=getByKey(id);
        return discount;
    }

    @Override
    public List<DiscountEntity> findAllDiscounts() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<DiscountEntity> discounts = (List<DiscountEntity>) criteria.list();
        return discounts;
    }

    @Override
    public void save(DiscountEntity discount) {
        persist(discount);
    }

    @Override
    public void change(DiscountEntity discount) {
        update(discount);
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("discount_id",id));
        DiscountEntity discount=(DiscountEntity) criteria.uniqueResult();
        delete(discount);
    }
}
