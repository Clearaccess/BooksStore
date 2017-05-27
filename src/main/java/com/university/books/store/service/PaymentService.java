package com.university.books.store.service;

import com.university.books.store.model.entity.PaymentEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/24/2017.
 */
public interface PaymentService {
    PaymentEntity findById(long id);

    List<PaymentEntity> findAllPayments();

    void save(PaymentEntity payment);

    void update(PaymentEntity payment);

    void deleteById(long id);
}
