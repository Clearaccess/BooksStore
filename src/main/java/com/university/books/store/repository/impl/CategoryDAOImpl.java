package com.university.books.store.repository.impl;

import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.CategoryDAO;
import com.university.books.store.model.entity.CategoryEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("categoryRepository")
public class CategoryDAOImpl extends AbstractDao<Long,CategoryEntity> implements CategoryDAO {
    @Override
    public CategoryEntity findById(long id) {
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
    public void update(CategoryEntity category) {
        super.update(category);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("category_id",id));
        CategoryEntity category=(CategoryEntity) criteria.uniqueResult();
        delete(category);
    }

    @Override
    public int countCategories() {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        int count=((Long)criteria.list().get(0)).intValue();
        return count;
    }
}
