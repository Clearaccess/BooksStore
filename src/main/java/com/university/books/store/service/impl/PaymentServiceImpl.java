package com.university.books.store.service.impl;

import com.university.books.store.model.entity.PaymentEntity;
import com.university.books.store.repository.PaymentDAO;
import com.university.books.store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    @Qualifier("paymentRepository")
    PaymentDAO paymentDAO;

    @Override
    public PaymentEntity findById(long id) {
        PaymentEntity payment=paymentDAO.findById(id);
        return payment;
    }

    @Override
    public List<PaymentEntity> findAllPayments() {
        List<PaymentEntity>payments=paymentDAO.findAllPayments();
        return payments;
    }

    @Override
    public void save(PaymentEntity payment) {
        paymentDAO.save(payment);
    }

    @Override
    public void update(PaymentEntity payment) {
        paymentDAO.update(payment);
    }

    @Override
    public void deleteById(long id) {
        paymentDAO.deleteById(id);
    }
}
