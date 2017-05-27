package com.university.books.store.service.impl;

import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.repository.ReviewDAO;
import com.university.books.store.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/27/2017.
 */
@Service("reviewService")
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    @Qualifier("reviewRepository")
    ReviewDAO reviewDAO;

    @Override
    public List<ReviewEntity> findAllReviews(int begPos, int limit) {
        List<ReviewEntity> reviews=reviewDAO.findAllReviews(begPos,limit);
        return reviews;
    }

    @Override
    public List<ReviewEntity> findAllReviewsByBookId(int begPos, int limit, long bookId) {
        List<ReviewEntity> reviews=reviewDAO.findAllReviewsByBookId(begPos,limit,bookId);
        return reviews;
    }

    @Override
    public void save(ReviewEntity review) {
        reviewDAO.save(review);
    }

    @Override
    public void update(ReviewEntity review) {
        reviewDAO.update(review);
    }

    @Override
    public void deleteById(long id) {
        reviewDAO.deleteById(id);
    }

    @Override
    public int countReviewByBookId(long id) {
        int count=reviewDAO.countReviewByBookId(id);
        return count;
    }

    @Override
    public double findAvgRateByBookId(long id) {
        double avgRate=reviewDAO.findAvgRateByBookId(id);
        return avgRate;
    }
}
