package com.university.books.store.service;

import com.university.books.store.model.entity.BagEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/24/2017.
 */
public interface BagService {
    BagEntity findById(long id);

    List<BagEntity> findAllBags();

    void save(BagEntity bag);

    void update(BagEntity bag);

    void deleteById(long id);
}
