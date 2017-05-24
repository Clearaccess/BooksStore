package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "book", schema = "books_store", catalog = "")
public class BookEntity {
    private long bookId;
    private String title;
    private Integer circulation;
    private String paper;
    private String form;
    private String weight;
    private String publisher;
    private String isbn;
    private String author;
    private String pages;
    private String ageLimit;
    private String series;
    private String cover;
    private String release_date;
    private double price;
    private String description;
    private CategoryEntity category;
    private Collection<DiscountEntity> discounts;
    private Collection<OrderDetailEntity> orderDetails;
    private Collection<ReviewEntity> reviews;
    private Collection<UserEntity> wishUsers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "circulation", nullable = true)
    public Integer getCirculation() {
        return circulation;
    }

    public void setCirculation(Integer circulation) {
        this.circulation = circulation;
    }

    @Basic
    @Column(name = "paper", nullable = true, length = 50)
    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    @Basic
    @Column(name = "form", nullable = true, length = 50)
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "weight", nullable = true, length = 50)
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "publisher", nullable = true, length = 50)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "ISBN", nullable = false, length = 50)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "pages", nullable = true, length = 50)
    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "age_limit", nullable = true, length = 50)
    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    @Basic
    @Column(name = "series", nullable = true, length = 100)
    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Basic
    @Column(name = "cover", nullable = true, length = 50)
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "release_date", nullable = true, length = 50)
    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release) {
        this.release_date = release_date;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 5000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<DiscountEntity> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Collection<DiscountEntity> discounts) {
        this.discounts = discounts;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<OrderDetailEntity> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Collection<OrderDetailEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Wish",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    @Fetch(FetchMode.SELECT)
    public Collection<UserEntity> getWishUsers() {
        return wishUsers;
    }

    public void setWishUsers(Collection<UserEntity> wishUsers) {
        this.wishUsers = wishUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (category.getCategoryId() != that.category.getCategoryId()) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (circulation != null ? !circulation.equals(that.circulation) : that.circulation != null) return false;
        if (paper != null ? !paper.equals(that.paper) : that.paper != null) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (ageLimit != null ? !ageLimit.equals(that.ageLimit) : that.ageLimit != null) return false;
        if (series != null ? !series.equals(that.series) : that.series != null) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (release_date != null ? !release_date.equals(that.release_date) : that.release_date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (bookId ^ (bookId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (circulation != null ? circulation.hashCode() : 0);
        result = 31 * result + (paper != null ? paper.hashCode() : 0);
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (ageLimit != null ? ageLimit.hashCode() : 0);
        result = 31 * result + (series != null ? series.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (release_date != null ? release_date.hashCode() : 0);
        result = 31 * result + (int) (category.getCategoryId() ^ (category.getCategoryId() >>> 32));
        return result;
    }
}
