package com.university.books.store.service;

import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.service.filter.BookFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */

public interface BookService {

    public BookEntity findById(long id);

    public List<BookEntity> findAllBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllNewestDiscountBooks(int begPos, int limit);

    public List<BookEntity> findAllMostPopularBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllNoPopularBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllNewestBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllOldestBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllExpensiveBooks(int begPos, int limit, BookFilter filter);

    public List<BookEntity> findAllCheapBooks(int begPos, int limit, BookFilter filter);
    public int countAllBooks();

    public int countAllBooksByFilter(BookFilter filter);

    public double maxPriceBooksByCategoryId(long categoryId);
}
