package com.university.books.store.dao.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Payment", schema = "public", catalog = "postgres")
public class PaymentEntity {
    private int paymentId;
    private String name;
    private String description;
    private Collection<OrderEntity> ordersByPaymentId;
    private Collection<OrderPaymentEntity> orderPaymentsByPaymentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        int result = paymentId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "paymentByPaymentId")
    public Collection<OrderEntity> getOrdersByPaymentId() {
        return ordersByPaymentId;
    }

    public void setOrdersByPaymentId(Collection<OrderEntity> ordersByPaymentId) {
        this.ordersByPaymentId = ordersByPaymentId;
    }

    @OneToMany(mappedBy = "paymentByPaymentId")
    public Collection<OrderPaymentEntity> getOrderPaymentsByPaymentId() {
        return orderPaymentsByPaymentId;
    }

    public void setOrderPaymentsByPaymentId(Collection<OrderPaymentEntity> orderPaymentsByPaymentId) {
        this.orderPaymentsByPaymentId = orderPaymentsByPaymentId;
    }
}
