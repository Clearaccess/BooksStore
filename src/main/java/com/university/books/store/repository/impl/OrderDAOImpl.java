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
@Repository("orderRepository")
public class OrderDAOImpl extends AbstractDao<Long,OrderEntity> implements OrderDAO {
    @Override
    public OrderEntity findById(long id) {
        OrderEntity order=getByKey(id);
        return order;
    }

    @Override
    public List<OrderEntity> findAllOrderByUserId(long userId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("user_id",userId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<OrderEntity> orders = (List<OrderEntity>) criteria.list();
        return orders;
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
    public void update(OrderEntity order) {
        super.update(order);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("order_id",id));
        OrderEntity order=(OrderEntity) criteria.uniqueResult();
        delete(order);
    }
}
