package com.university.books.store.repository.impl;

import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.BookDAO;
import com.university.books.store.model.entity.BookEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/7/2017.
 */
@Repository("bookRepositoryDao")
public class BookDAOImpl extends AbstractDao<Integer,BookEntity> implements BookDAO {
    @Override
    public BookEntity findById(int id) {
        BookEntity book = getByKey(id);
        return book;
    }

    @Override
    public List<BookEntity> findAllBooks() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<BookEntity> books = (List<BookEntity>) criteria.list();
        return books;
    }

    @Override
    public void save(BookEntity book) {
        persist(book);
    }

    @Override
    public void change(BookEntity book){
        update(book);
    }

    @Override
    public void deleteById(int id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("book_id", id));
        BookEntity book = (BookEntity)criteria.uniqueResult();
        delete(book);
    }

    @Override
    public int countAllBooks() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<BookEntity> books = (List<BookEntity>) criteria.list();
        if(books!=null) {
            return books.size();
        }

        return 0;
    }

    @Override
    public List<BookEntity> getPage(int indexBegin, int number) {
        Criteria criteria = createEntityCriteria();
        criteria.setFirstResult(indexBegin)
                .setMaxResults(number)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<BookEntity> books = (List<BookEntity>) criteria.list();
        return books;
    }
}
