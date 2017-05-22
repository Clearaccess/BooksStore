package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "order_detail", schema = "books_store", catalog = "")
public class OrderDetailEntity {
    private long odId;
    private int amount;
    private double price;
    private OrderEntity order;
    private BookEntity book;

    @Id
    @Column(name = "od_id", nullable = false)
    public long getOdId() {
        return odId;
    }

    public void setOdId(long odId) {
        this.odId = odId;
    }
    @Basic
    @Column(name = "amount", nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailEntity that = (OrderDetailEntity) o;

        if (odId != that.odId) return false;
        if (order.getOrderId() != that.order.getOrderId()) return false;
        if (book.getBookId() != that.book.getBookId()) return false;
        if (amount != that.amount) return false;
        if (Double.compare(that.price, price) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (odId ^ (odId >>> 32));
        result = 31 * result + (int) (order.getOrderId() ^ (order.getOrderId() >>> 32));
        result = 31 * result + (int) (book.getBookId() ^ (book.getBookId() >>> 32));
        result = 31 * result + amount;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
