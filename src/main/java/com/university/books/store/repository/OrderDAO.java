package com.university.books.store.repository;

import com.university.books.store.model.entity.OrderEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface OrderDAO {

    OrderEntity findById(long id);

    List<OrderEntity> findAllOrderByUserId(long userId);

    List<OrderEntity> findAllOrders();

    void save(OrderEntity order);

    void update(OrderEntity order);

    void deleteById(long id);
}
