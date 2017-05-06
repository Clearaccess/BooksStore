package com.university.books.store.dao.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Book", schema = "public", catalog = "postgres")
public class BookEntity {
    private int bookId;
    private String name;
    private String originalName;
    private int pages;
    private int releaseDate;
    private String publisher;
    private int weight;
    private int height;
    private int width;
    private String description;
    private int isbn;
    private String material;
    private String language;
    private String author;
    private Integer circulation;
    private double price;
    private int status;
    private Collection<CategoryEntity> categoriesByBookId;
    private Collection<DiscountEntity> discountsByBookId;
    private Collection<OrderDetailEntity> orderDetailsByBookId;
    private Collection<ReviewEntity> reviewsByBookId;
    private Collection<UserEntity> wishUsers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "original_name", nullable = true, length = -1)
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Basic
    @Column(name = "pages", nullable = false)
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "release_date", nullable = false)
    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "publisher", nullable = false, length = -1)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "height", nullable = false)
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Basic
    @Column(name = "width", nullable = false)
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ISBN", nullable = false)
    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "material", nullable = true, length = -1)
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Basic
    @Column(name = "language", nullable = false, length = -1)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "author", nullable = false, length = -1)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (pages != that.pages) return false;
        if (releaseDate != that.releaseDate) return false;
        if (weight != that.weight) return false;
        if (height != that.height) return false;
        if (width != that.width) return false;
        if (isbn != that.isbn) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (status != that.status) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (originalName != null ? !originalName.equals(that.originalName) : that.originalName != null) return false;
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (circulation != null ? !circulation.equals(that.circulation) : that.circulation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = bookId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (originalName != null ? originalName.hashCode() : 0);
        result = 31 * result + pages;
        result = 31 * result + releaseDate;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + isbn;
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (circulation != null ? circulation.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + status;
        return result;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<DiscountEntity> getDiscountsByBookId() {
        return discountsByBookId;
    }

    public void setDiscountsByBookId(Collection<DiscountEntity> discountsByBookId) {
        this.discountsByBookId = discountsByBookId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<OrderDetailEntity> getOrderDetailsByBookId() {
        return orderDetailsByBookId;
    }

    public void setOrderDetailsByBookId(Collection<OrderDetailEntity> orderDetailsByBookId) {
        this.orderDetailsByBookId = orderDetailsByBookId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<ReviewEntity> getReviewsByBookId() {
        return reviewsByBookId;
    }

    public void setReviewsByBookId(Collection<ReviewEntity> reviewsByBookId) {
        this.reviewsByBookId = reviewsByBookId;
    }

    @ManyToMany
    @JoinTable(name = "Wish",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    public Collection<UserEntity> getWishUsers() {
        return wishUsers;
    }

    public void setWishUsers(Collection<UserEntity> wishUsers) {
        this.wishUsers = wishUsers;
    }

    @ManyToMany
    @JoinTable(name = "Book_category",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    public Collection<CategoryEntity> getCategoriesByBookId() {
        return categoriesByBookId;
    }

    public void setCategoriesByBookId(Collection<CategoryEntity> bookCategoriesByBookId) {
        this.categoriesByBookId = bookCategoriesByBookId;
    }
}
