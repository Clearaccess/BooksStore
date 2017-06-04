package com.university.books.store.model.dto;

import com.university.books.store.model.entity.BookEntity;

import java.util.Map;

/**
 * Created by Aleksandr on 6/4/2017.
 */
public class BagDTO {
    private Map<Long,Integer>books;
    private double totalPrice;

    public Map<Long, Integer> getBooks() {
        return books;
    }

    public void setBooks(Map<Long, Integer> books) {
        this.books = books;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
