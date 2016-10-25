package by.samsolutions.dao.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserInfo;
import org.hibernate.Session;
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
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .list();

        return users.stream().findFirst().orElse(null);

    }

    @Override
    public UserInfo addUserInfo(UserInfo userInfo) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        user.getUserRole().forEach(userRole -> session.save(userRole));

        return user;
    }
}
