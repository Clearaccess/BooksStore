package com.university.books.store.service.impl;

import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.repository.UserDAO;
import com.university.books.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepositoryDao")
    private UserDAO userDao;

    @Override
    public List<UserEntity> findAllUsers() {
        return userDao.findAllUsers();
    }
}
