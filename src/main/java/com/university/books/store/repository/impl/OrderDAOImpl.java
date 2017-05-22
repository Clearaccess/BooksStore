package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.OrderEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.OrderDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("orderRepositoryDao")
public class OrderDAOImpl extends AbstractDao<Integer,OrderEntity> implements OrderDAO {
    @Override
    public OrderEntity findById(int id) {
        OrderEntity order=getByKey(id);
        return order;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<OrderEntity> orders = (List<OrderEntity>) criteria.list();
        return orders;
    }

    @Override
    public void save(OrderEntity order) {
        persist(order);
    }

    @Override
    public void change(OrderEntity order) {
        update(order);
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("order_id",id));
        OrderEntity order=(OrderEntity) criteria.uniqueResult();
        delete(order);
    }
}
