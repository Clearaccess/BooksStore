package com.university.books.store.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Discount", schema = "public", catalog = "postgres")
public class DiscountEntity {
    private int discountId;
    private long bookId;
    private String description;
    private Date startDate;
    private Date stopDate;
    private double discountPercentage;
    private int status;
    private BookEntity bookByBookId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id", nullable = false)
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "stop_date", nullable = false)
    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    @Basic
    @Column(name = "discount_percentage", nullable = false, precision = 0)
    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountEntity that = (DiscountEntity) o;

        if (discountId != that.discountId) return false;
        if (bookId != that.bookId) return false;
        if (Double.compare(that.discountPercentage, discountPercentage) != 0) return false;
        if (status != that.status) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (stopDate != null ? !stopDate.equals(that.stopDate) : that.stopDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = discountId;
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (stopDate != null ? stopDate.hashCode() : 0);
        temp = Double.doubleToLongBits(discountPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}
