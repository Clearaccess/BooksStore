package com.university.books.store.repository;

import com.university.books.store.model.entity.BookEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Aleksandr on 5/7/2017.
 */
public interface BookDAO {
    BookEntity findById(int id);

    List<BookEntity> findAllBooks();

    void save(BookEntity book);

    void change(BookEntity book);

    void deleteById(int id);

    int countAllBooks();

    List<BookEntity> getPage(int indexBegin, int number);
}
