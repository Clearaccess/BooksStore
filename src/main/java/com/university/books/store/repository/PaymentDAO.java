package com.university.books.store.repository;

import com.university.books.store.model.entity.PaymentEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
public interface PaymentDAO {
    PaymentEntity findById(long id);

    List<PaymentEntity> findAllPayments();

    void save(PaymentEntity payment);

    void update(PaymentEntity payment);

    void deleteById(long id);
}
