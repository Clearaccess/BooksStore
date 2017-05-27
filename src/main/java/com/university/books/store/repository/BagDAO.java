package com.university.books.store.repository;

import com.university.books.store.model.entity.BagEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
public interface BagDAO {
    BagEntity findById(long id);

    List<BagEntity> findAllBags();

    void save(BagEntity bag);

    void update(BagEntity bag);

    void deleteById(long id);
}
