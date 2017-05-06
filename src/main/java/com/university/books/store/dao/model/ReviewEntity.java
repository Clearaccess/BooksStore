package com.university.books.store.dao.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Review", schema = "public", catalog = "postgres")
public class ReviewEntity {
    private int reviewId;
    private String message;
    private int rate;
    private long bookId;
    private long userId;
    private Date createDate;
    private Date updateDate;
    private BookEntity bookByBookId;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Basic
    @Column(name = "message", nullable = true, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "rate", nullable = false)
    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = true)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewEntity that = (ReviewEntity) o;

        if (reviewId != that.reviewId) return false;
        if (rate != that.rate) return false;
        if (bookId != that.bookId) return false;
        if (userId != that.userId) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = reviewId;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
