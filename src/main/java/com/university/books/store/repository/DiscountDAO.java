package com.university.books.store.repository;

import com.university.books.store.model.entity.DiscountEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface DiscountDAO {
    DiscountEntity findById(int id);

    List<DiscountEntity> findAllDiscounts();

    void save(DiscountEntity discount);

    void change(DiscountEntity discount);

    void deleteById(int id);
}
