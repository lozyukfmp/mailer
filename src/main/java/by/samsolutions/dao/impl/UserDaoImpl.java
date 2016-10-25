package by.samsolutions.dao.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @SuppressWarnings("uncheked")
    @Override
    public User findByUsername(String username) {
        System.out.println("USERNAME : " + username);
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .list();
        System.out.println(users);
        return users.stream().findFirst().orElse(null);

    }

    @Override
    public User findById(int id) {
        return null;
    }
}
