package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.PaymentEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.PaymentDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
@Repository("paymentRepository")
public class PaymentDAOImpl extends AbstractDao<Long, PaymentEntity> implements PaymentDAO {
    @Override
    public PaymentEntity findById(long id) {
        PaymentEntity payment=getByKey(id);
        return payment;
    }

    @Override
    public List<PaymentEntity> findAllPayments() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<PaymentEntity> payments = (List<PaymentEntity>) criteria.list();
        return payments;
    }

    @Override
    public void save(PaymentEntity payment) {
        persist(payment);
    }

    @Override
    public void update(PaymentEntity payment) {
        super.update(payment);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("payment_id",id));
        PaymentEntity payment=(PaymentEntity) criteria.uniqueResult();
        delete(payment);
    }
}
