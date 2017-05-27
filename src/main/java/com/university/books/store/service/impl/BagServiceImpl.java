package com.university.books.store.service.impl;

import com.university.books.store.model.entity.BagEntity;
import com.university.books.store.repository.BagDAO;
import com.university.books.store.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/25/2017.
 */
@Service("bagService")
@Transactional
public class BagServiceImpl implements BagService {

    @Autowired
    @Qualifier("bagRepository")
    BagDAO bagDAO;

    @Override
    public BagEntity findById(long id) {
        BagEntity bag=bagDAO.findById(id);
        return bag;
    }

    @Override
    public List<BagEntity> findAllBags() {
        List<BagEntity> bags=bagDAO.findAllBags();
        return bags;
    }

    @Override
    public void save(BagEntity bag) {
        bagDAO.save(bag);
    }

    @Override
    public void update(BagEntity bag) {
        bagDAO.update(bag);
    }

    @Override
    public void deleteById(long id) {
        bagDAO.deleteById(id);
    }
}
