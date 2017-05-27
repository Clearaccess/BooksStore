package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.OrderDAO;
import com.university.books.store.repository.ReviewDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
@Repository("reviewRepository")
public class ReviewDAOImpl extends AbstractDao<Long, ReviewEntity> implements ReviewDAO {
    @Override
    public ReviewEntity findById(long id) {
        ReviewEntity review = getByKey(id);
        return review;
    }

    @Override
    public List<ReviewEntity> findAllReviews(int begPos, int limit) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ReviewEntity> reviews = (List<ReviewEntity>) criteria.list();
        return reviews;
    }

    @Override
    public List<ReviewEntity> findAllReviewsByBookId(int begPos, int limit, long bookId) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("book_id", bookId))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<ReviewEntity> reviews = (List<ReviewEntity>) criteria.list();
        return reviews;
    }

    @Override
    public void save(ReviewEntity review) {
        persist(review);
    }

    @Override
    public void update(ReviewEntity review) {
        super.update(review);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("review_id", id))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        ReviewEntity review = (ReviewEntity) criteria.uniqueResult();
        delete(review);
    }

    @Override
    public int countReviewByBookId(long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("book_id", id))
                .setProjection(Projections.rowCount());
        int count = ((Long) criteria.list().get(0)).intValue();
        return count;
    }

    @Override
    public double findAvgRateByBookId(long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("book_id", id))
                .setProjection(Projections.avg("rate"));
        double avgRate = ((Double) criteria.list().get(0)).doubleValue();
        return avgRate;
    }
}
