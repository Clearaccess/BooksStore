package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "discount", schema = "books_store", catalog = "")
public class DiscountEntity {
    private long discountId;
    private String desription;
    private Calendar startDate;
    private Calendar stopDate;
    private double percentage;
    private int status;
    private BookEntity book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false)
    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "desription", nullable = true, length = 50)
    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stop_date", nullable = false)
    public Calendar getStopDate() {
        return stopDate;
    }

    public void setStopDate(Calendar stopDate) {
        this.stopDate = stopDate;
    }

    @Basic
    @Column(name = "percentage", nullable = false, precision = 0)
    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

        DiscountEntity that = (DiscountEntity) o;

        if (discountId != that.discountId) return false;
        if (book.getBookId() != that.book.getBookId()) return false;
        if (Double.compare(that.percentage, percentage) != 0) return false;
        if (status != that.status) return false;
        if (desription != null ? !desription.equals(that.desription) : that.desription != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (stopDate != null ? !stopDate.equals(that.stopDate) : that.stopDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (discountId ^ (discountId >>> 32));
        result = 31 * result + (int) (book.getBookId() ^ (book.getBookId() >>> 32));
        result = 31 * result + (desription != null ? desription.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        temp = Double.doubleToLongBits(percentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status;
        return result;
    }
}
