package com.university.books.store.repository;

import com.university.books.store.model.entity.CategoryEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface CategoryDAO {
    CategoryEntity findById(int id);

    List<CategoryEntity> findAllCategories();

    void save(CategoryEntity category);

    void change(CategoryEntity category);

    void deleteById(int id);

    int countCategories(int id);
}
