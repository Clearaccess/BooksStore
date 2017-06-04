package com.university.books.store.repository.impl;

import com.university.books.store.model.entity.UserEntity;
import com.university.books.store.repository.AbstractDao;
import com.university.books.store.repository.UserDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Aleksandr on 5/15/2017.
 */
@Repository("userRepository")
public class UserDAOImpl extends AbstractDao<Long, UserEntity> implements UserDAO {

    @Override
    public UserEntity findById(long id) {
        UserEntity user=getByKey(id);
        return user;
    }

    @Override
    public UserEntity findByLogin(String login) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login",login).ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<UserEntity> user = (List<UserEntity>) criteria.list();
        if(user.size()>0){
            return user.get(0);
        }

        return null;
    }

    @Override
    public UserEntity findByEmail(String email) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email",email).ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<UserEntity> user = (List<UserEntity>) criteria.list();
        if(user.size()>0){
            return user.get(0);
        }

        return null;
    }

    @Override
    public List<UserEntity> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<UserEntity> users = (List<UserEntity>) criteria.list();
        return users;
    }

    @Override
    public void save(UserEntity user) {
        persist(user);
    }

    @Override
    public void update(UserEntity user) {
        super.update(user);
    }

    @Override
    public void deleteById(long id) {
        Criteria criteria=createEntityCriteria();
        criteria.add(Restrictions.eq("user_id",id));
        UserEntity user=(UserEntity) criteria.uniqueResult();
        delete(user);
    }
}
