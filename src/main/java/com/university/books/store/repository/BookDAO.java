package com.university.books.store.repository;

import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.service.filter.BookFilter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Aleksandr on 5/7/2017.
 */
public interface BookDAO {

    BookEntity findById(long id);

    List<BookEntity> findAllBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllNewestDiscountBooks(int begPos, int limit);

    List<BookEntity> findAllMostPopularBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllNoPopularBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllNewestBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllOldestBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllExpensiveBooks(int begPos, int limit, BookFilter filter);

    List<BookEntity> findAllCheapBooks(int begPos, int limit, BookFilter filter);

    void save(BookEntity book);

    void update(BookEntity book);

    void deleteById(long id);

    int countAllBooks();
}
