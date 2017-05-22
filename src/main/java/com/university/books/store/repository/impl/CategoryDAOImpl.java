package com.university.books.store.repository.impl;

import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.CategoryDAO;
import com.university.books.store.model.entity.CategoryEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("categoryRepositoryDao")
public class CategoryDAOImpl extends AbstractDao<Integer,CategoryEntity> implements CategoryDAO {
    @Override
    public CategoryEntity findById(int id) {
        CategoryEntity category=getByKey(id);
        return category;
    }

    @Override
    public List<CategoryEntity> findAllCategories() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<CategoryEntity> categories = (List<CategoryEntity>) criteria.list();
        return categories;
    }

    @Override
    public void save(CategoryEntity category) {
        persist(category);
    }

    @Override
    public void change(CategoryEntity category) {
        update(category);
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("category_id",id));
        CategoryEntity category=(CategoryEntity) criteria.uniqueResult();
        delete(category);
    }

    @Override
    public int countCategories(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<CategoryEntity> categories = (List<CategoryEntity>) criteria.list();
        if(categories!=null){
            return categories.size();
        }
        return 0;
    }
}
