package com.university.books.store.repository;

import com.university.books.store.model.entity.UserEntity;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
public interface UserDAO {
    public UserEntity findById(long id);

    public UserEntity findByLogin(String login);

    public UserEntity findByEmail(String email);

    public List<UserEntity> findAllUsers();

    public void save(UserEntity user);

    public void update(UserEntity user);

    public void deleteById(long id);
}
