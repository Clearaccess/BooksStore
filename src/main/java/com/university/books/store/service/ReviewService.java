package com.university.books.store.service;

import com.university.books.store.model.entity.ReviewEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/27/2017.
 */
public interface ReviewService {
    List<ReviewEntity> findAllReviews(int begPos, int limit);

    List<ReviewEntity> findAllReviewsByBookId(int begPos, int limit, long bookId);

    void save(ReviewEntity review);

    void update(ReviewEntity review);

    void deleteById(long id);

    int countReviewByBookId(long id);

    double findAvgRateByBookId(long id);
}
