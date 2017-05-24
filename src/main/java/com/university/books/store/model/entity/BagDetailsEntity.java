package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by Aleksandr on 5/23/2017.
 */
@Entity
@Table(name = "bag_detail", schema = "books_store", catalog = "")
public class BagDetailsEntity {
    private long bdId;
    private long bookId;
    private int amount;
    private double price;
    private BagEntity bag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bd_id")
    public long getBdId() {
        return bdId;
    }

    public void setBdId(long bdId) {
        this.bdId = bdId;
    }

    @Basic
    @Column(name = "book_id")
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name="price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "bag_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public BagEntity getBag() {
        return bag;
    }

    public void setBag(BagEntity bag) {
        this.bag = bag;
    }
}
