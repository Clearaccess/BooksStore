package com.university.books.store.service.impl;

import com.university.books.store.model.entity.DiscountEntity;
import com.university.books.store.repository.DiscountDAO;
import com.university.books.store.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("discountService")
@Transactional
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    @Qualifier("discountRepository")
    DiscountDAO discountDAO;

    @Override
    public DiscountEntity findDiscountById(long id) {
        DiscountEntity discount=discountDAO.findById(id);
        return discount;
    }

    @Override
    public List<DiscountEntity> findNewestDiscounts(int begPos, int limit) {
        List<DiscountEntity> discounts=discountDAO.findNewestDiscounts(begPos, limit);
        return discounts;
    }
}
