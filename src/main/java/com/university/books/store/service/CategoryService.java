package com.university.books.store.service;

import com.university.books.store.model.entity.CategoryEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface CategoryService {
    List<CategoryEntity> findAllCategories();
}
