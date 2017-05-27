package com.university.books.store.repository;

import com.university.books.store.model.entity.DiscountEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface DiscountDAO {
    DiscountEntity findById(long id);

    List<DiscountEntity> findAllDiscounts(int begPos, int limit);

    List<DiscountEntity> findNewestDiscounts(int begPos, int limit);

    void save(DiscountEntity discount);

    void update(DiscountEntity discount);

    void deleteById(long id);
}
