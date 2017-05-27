package com.university.books.store.service;

import com.university.books.store.model.entity.ReviewEntity;
import com.university.books.store.model.entity.UserEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface UserService {

    public UserEntity findUserById(long id);
    public List<UserEntity> findAllUsers();
    public void save(UserEntity user);
    public void update(UserEntity user);
    public void deleteById(long id);

}
