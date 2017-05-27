package com.university.books.store.service;

import com.university.books.store.model.entity.DiscountEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface DiscountService {
    public DiscountEntity findDiscountById(long id);
    public List<DiscountEntity> findNewestDiscounts(int begPos, int limit);

}
