package com.university.books.store.service.impl;

import com.university.books.store.model.entity.CategoryEntity;
import com.university.books.store.repository.CategoryDAO;
import com.university.books.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    @Qualifier("categoryRepository")
    CategoryDAO categoryDAO;

    @Override
    public List<CategoryEntity> findAllCategories() {
        List<CategoryEntity> categories = categoryDAO.findAllCategories();
        return categories;
    }
}
