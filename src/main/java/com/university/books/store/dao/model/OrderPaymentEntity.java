package com.university.books.store.dao.model;

import javax.persistence.*;

/**
 * Created by Aleksandr on 5/6/2017.
 */
@Entity
@Table(name = "Order_payment", schema = "public", catalog = "postgres")
public class OrderPaymentEntity {
    private int opId;
    private long paymentId;
    private long transactionId;
    private int status;
    private long orderId;
    private PaymentEntity paymentByPaymentId;
    private OrderEntity orderByOrderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_id", nullable = false)
    public int getOpId() {
        return opId;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    @Basic
    @Column(name = "payment_id", nullable = false)
    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "transaction_id", nullable = false)
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "order_id", nullable = false)
    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPaymentEntity that = (OrderPaymentEntity) o;

        if (opId != that.opId) return false;
        if (paymentId != that.paymentId) return false;
        if (transactionId != that.transactionId) return false;
        if (status != that.status) return false;
        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = opId;
        result = 31 * result + (int) (paymentId ^ (paymentId >>> 32));
        result = 31 * result + (int) (transactionId ^ (transactionId >>> 32));
        result = 31 * result + status;
        result = 31 * result + (int) (orderId ^ (orderId >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "payment_id", nullable = false)
    public PaymentEntity getPaymentByPaymentId() {
        return paymentByPaymentId;
    }

    public void setPaymentByPaymentId(PaymentEntity paymentByPaymentId) {
        this.paymentByPaymentId = paymentByPaymentId;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public OrderEntity getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(OrderEntity orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }
}
