package com.university.books.store.service.impl;

import com.university.books.store.model.entity.OrderEntity;
import com.university.books.store.repository.OrderDAO;
import com.university.books.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    @Qualifier("orderRepository")
    OrderDAO orderDAO;

    @Override
    public OrderEntity findById(long id) {
        OrderEntity order=orderDAO.findById(id);
        return order;
    }

    @Override
    public List<OrderEntity> findAllOrderByUserId(long userId) {
        List<OrderEntity> orders=orderDAO.findAllOrderByUserId(userId);
        return orders;
    }

    @Override
    public List<OrderEntity> findAllOrders() {
        List<OrderEntity> orders=orderDAO.findAllOrders();
        return orders;
    }

    @Override
    public void save(OrderEntity order) {
        orderDAO.save(order);
    }

    @Override
    public void update(OrderEntity order) {
        orderDAO.update(order);
    }

    @Override
    public void deleteById(long id) {
        orderDAO.deleteById(id);
    }
}
