package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.DiscountEntity;
import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.BookDAO;
import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.repository.DiscountDAO;
import com.university.books.store.repository.ReviewDAO;
import com.university.books.store.service.filter.BookFilter;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 5/7/2017.
 */
@Repository("bookRepository")
public class BookDAOImpl extends AbstractDao<Long, BookEntity> implements BookDAO {

    @Autowired
    @Qualifier("discountRepository")
    DiscountDAO discountDAO;

    @Override
    public BookEntity findById(long id) {
        BookEntity book = getByKey(id);
        return book;
    }

    @Override
    public List<BookEntity> findAllBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();
        criteria = applyFilter(criteria, filter);
        criteria.setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<BookEntity> books = criteria.list();
        return books;
    }

    @Override
    public List<BookEntity> findAllMostPopularBooks(int begPos, int limit, BookFilter filter) {
        Criteria reviewCriteria = createEntityCriteria(ReviewEntity.class);

        reviewCriteria.setProjection(Projections.projectionList()
                .add(Projections.sum("rate"), "rt")
                .add(Projections.groupProperty("book.bookId")))
                .addOrder(Order.desc("rt"))
                .setFirstResult(begPos)
                .setMaxResults(limit);
        List<Object[]> rates = reviewCriteria.list();

        List<BookEntity> books = new ArrayList<>();

        int iteration = 0;
        for (Object[] rate : rates) {
            iteration++;
            System.out.println(rate[0] + " " + rate[1]);
            BookEntity book = findById((long) rate[1]);
            if ((filter.getMinPrice() <= book.getPrice() && book.getPrice() <= filter.getMaxPrice())
                    &&
                    (filter.getCategory() == 0 || book.getCategory().getCategoryId() == filter.getCategory())
                    &&
                    iteration >= begPos) {
                books.add(book);
            }

            if (books.size() >= limit + begPos) {
                break;
            }
        }

        System.out.println("GOOD! bookDAOImpl.findAllMostPopularBooks");
        System.out.println("size result: " + books.size());

        return books;
    }

    @Override
    public List<BookEntity> findAllNoPopularBooks(int begPos, int limit, BookFilter filter) {
        Criteria reviewCriteria = createEntityCriteria(ReviewEntity.class);

        reviewCriteria.setProjection(Projections.projectionList()
                .add(Projections.sum("rate"), "rt")
                .add(Projections.groupProperty("book.bookId")))
                .addOrder(Order.asc("rt"))
                .setFirstResult(begPos)
                .setMaxResults(limit);
        List<Object[]> rates = reviewCriteria.list();

        List<BookEntity> books = new ArrayList<>();

        int iteration = 0;
        for (Object[] rate : rates) {
            iteration++;
            System.out.println(rate[0] + " " + rate[1]);
            BookEntity book = findById((long) rate[1]);
            if ((filter.getMinPrice() <= book.getPrice() && book.getPrice() <= filter.getMaxPrice())
                    &&
                    (filter.getCategory() == 0 || book.getCategory().getCategoryId() == filter.getCategory())
                    &&
                    iteration >= begPos) {
                books.add(book);
            }

            if (books.size() >= limit + begPos) {
                break;
            }
        }

        System.out.println("GOOD! bookDAOImpl.findAllNoPopularBooks");
        System.out.println("size result: " + books.size());

        return books;
    }

    @Override
    public List<BookEntity> findAllNewestDiscountBooks(int begPos, int limit) {
        List<DiscountEntity> discounts = discountDAO.findNewestDiscounts(begPos, limit);
        List<BookEntity> books = new ArrayList<>();
        for (DiscountEntity discount : discounts) {
            books.add(discount.getBook());
        }

        return books;
    }

    @Override
    public List<BookEntity> findAllNewestBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();

        criteria = applyFilter(criteria, filter);

        criteria.addOrder(Order.desc("releaseDate"))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<BookEntity> books = criteria.list();
        return books;
    }

    @Override
    public List<BookEntity> findAllOldestBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();

        criteria = applyFilter(criteria, filter);

        criteria.addOrder(Order.asc("release_date"))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<BookEntity> books = criteria.list();
        return books;
    }

    @Override
    public List<BookEntity> findAllExpensiveBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();

        criteria = applyFilter(criteria, filter);

        criteria.addOrder(Order.desc("price"))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<BookEntity> books = criteria.list();
        return books;
    }

    @Override
    public List<BookEntity> findAllCheapBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();

        criteria = applyFilter(criteria, filter);

        criteria.addOrder(Order.asc("price"))
                .setFirstResult(begPos)
                .setMaxResults(limit)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<BookEntity> books = criteria.list();
        return books;
    }

    @Override
    public void save(BookEntity book) {
        persist(book);
    }

    @Override
    public void update(BookEntity book) {
        super.update(book);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("book_id", id));
        BookEntity book = (BookEntity) criteria.uniqueResult();
        delete(book);
    }

    @Override
    public int countAllBooks() {
        Criteria criteria = createEntityCriteria();
        criteria.setProjection(Projections.rowCount());
        System.out.println(criteria.list().get(0).getClass());
        int count = ((Long) criteria.list().get(0)).intValue();
        return count;
    }

    @Override
    public int countAllBooksByFilter(BookFilter filter) {
        Criteria criteria = createEntityCriteria();
        criteria = applyFilter(criteria, filter);
        criteria.setProjection(Projections.rowCount());
        System.out.println(criteria.list().get(0).getClass());
        int count = ((Long) criteria.list().get(0)).intValue();
        return count;
    }

    protected Criteria applyFilter(Criteria criteria, BookFilter filter) {
        criteria.add(Restrictions.between("price", filter.getMinPrice(), filter.getMaxPrice()));

        if (filter.getCategory() != 0) {
            criteria.add(Restrictions.eq("category.categoryId", filter.getCategory()));
        }

        return criteria;
    }
}
