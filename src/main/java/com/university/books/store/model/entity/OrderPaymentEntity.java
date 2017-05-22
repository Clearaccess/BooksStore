package com.university.books.store.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

/**
 * Created by Aleksandr on 5/21/2017.
 */
@Entity
@Table(name = "order_payment", schema = "books_store", catalog = "")
public class OrderPaymentEntity {
    private long opId;
    private long transationId;
    private int status;
    private PaymentEntity payment;
    private OrderEntity order;

    @Id
    @Column(name = "op_id", nullable = false)
    public long getOpId() {
        return opId;
    }

    public void setOpId(long opId) {
        this.opId = opId;
    }

    @Basic
    @Column(name = "transation_id", nullable = false)
    public long getTransationId() {
        return transationId;
    }

    public void setTransationId(long transationId) {
        this.transationId = transationId;
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
    @JoinColumn(name = "payment_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPaymentEntity that = (OrderPaymentEntity) o;

        if (opId != that.opId) return false;
        if (payment.getPaymentId() != that.payment.getPaymentId()) return false;
        if (transationId != that.transationId) return false;
        if (status != that.status) return false;
        if (order.getOrderId() != that.order.getOrderId()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (opId ^ (opId >>> 32));
        result = 31 * result + (int) (payment.getPaymentId() ^ (payment.getPaymentId() >>> 32));
        result = 31 * result + (int) (transationId ^ (transationId >>> 32));
        result = 31 * result + status;
        result = 31 * result + (int) (order.getOrderId() ^ (order.getOrderId() >>> 32));
        return result;
    }
}
