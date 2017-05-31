package com.university.books.store.service.impl;

import com.university.books.store.model.entity.BookEntity;
import com.university.books.store.repository.BookDAO;
import com.university.books.store.service.BookService;
import com.university.books.store.service.filter.BookFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookRepository")
    BookDAO bookDAO;

    @Override
    public BookEntity findById(long id) {
        BookEntity book=bookDAO.findById(id);
        return book;
    }

    @Override
    public List<BookEntity> findAllBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllMostPopularBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllMostPopularBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllNoPopularBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllNoPopularBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllNewestBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllNewestBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllOldestBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllOldestBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllExpensiveBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllExpensiveBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllCheapBooks(int begPos, int limit, BookFilter filter) {
        List<BookEntity>books=bookDAO.findAllCheapBooks(begPos,limit,filter);
        return books;
    }

    @Override
    public List<BookEntity> findAllNewestDiscountBooks(int begPos, int limit) {
        List<BookEntity>books=bookDAO.findAllNewestDiscountBooks(begPos,limit);
        return books;
    }

    @Override
    public int countAllBooks() {
        int count=bookDAO.countAllBooks();
        return count;
    }

    @Override
    public int countAllBooksByFilter(BookFilter filter) {
        int count=bookDAO.countAllBooksByFilter(filter);
        return count;
    }
}
