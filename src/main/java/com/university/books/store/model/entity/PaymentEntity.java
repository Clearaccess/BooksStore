package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "payment", schema = "books_store", catalog = "")
public class PaymentEntity {
    private long paymentId;
    private String name;
    private String description;
    private Collection<OrderEntity> orders;
    private Collection<OrderPaymentEntity> orderPayments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Collection<OrderEntity> orders) {
        this.orders = orders;
    }

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    public Collection<OrderPaymentEntity> getOrderPayments() {
        return orderPayments;
    }

    public void setOrderPayments(Collection<OrderPaymentEntity> orderPayments) {
        this.orderPayments = orderPayments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (paymentId != that.paymentId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (paymentId ^ (paymentId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
