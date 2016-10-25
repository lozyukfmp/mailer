package by.samsolutions.dao.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
