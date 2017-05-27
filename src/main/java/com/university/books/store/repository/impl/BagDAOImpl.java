package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.BagEntity;
import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.BagDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
@Repository("bagRepository")
public class BagDAOImpl extends AbstractDao<Long, BagEntity> implements BagDAO {

    @Override
    public BagEntity findById(long id) {
        BagEntity bag=getByKey(id);
        return bag;
    }

    @Override
    public List<BagEntity> findAllBags() {
        Criteria criteria=createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<BagEntity> bags = (List<BagEntity>) criteria.list();
        return bags;
    }

    @Override
    public void save(BagEntity bag) {
        persist(bag);
    }

    @Override
    public void update(BagEntity bag) {
        super.update(bag);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("bag_id",id));
        BagEntity bag=(BagEntity) criteria.uniqueResult();
        delete(bag);
    }
}
