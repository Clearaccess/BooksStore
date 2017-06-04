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
        Criteria criteria = createEntityCriteria();
        criteria=applyFilter(criteria,filter);

        criteria.addOrder(Order.desc("rate"))
                .setFirstResult(begPos)
                .setMaxResults(limit);

        List<BookEntity> books = (List<BookEntity>)criteria.list();

        System.out.println("GOOD! bookDAOImpl.findAllMostPopularBooks");
        System.out.println("size result: " + books.size());

        return books;
    }

    @Override
    public List<BookEntity> findAllNoPopularBooks(int begPos, int limit, BookFilter filter) {
        Criteria criteria = createEntityCriteria();
        criteria=applyFilter(criteria,filter);

        criteria.addOrder(Order.asc("rate"))
                .setFirstResult(begPos)
                .setMaxResults(limit);

        List<BookEntity> books = (List<BookEntity>)criteria.list();

        System.out.println("GOOD! bookDAOImpl.findAllMostPopularBooks");
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

    @Override
    public double maxPriceBooksByCategoryId(long categoryId) {
        Criteria criteria = createEntityCriteria();
        criteria=applyFilter(criteria,new BookFilter().setCategory(categoryId));
        criteria.setProjection(Projections.max("price"));
        System.out.println(criteria.list().get(0).getClass());
        double maxPrice = ((Double) criteria.list().get(0)).doubleValue();
        return maxPrice;
    }


    protected Criteria applyFilter(Criteria criteria, BookFilter filter) {
        criteria.add(Restrictions.between("price", filter.getMinPrice(), filter.getMaxPrice()));

        if (filter.getCategory() != 0) {
            criteria.add(Restrictions.eq("category.categoryId", filter.getCategory()));
        }

        return criteria;
    }
}
