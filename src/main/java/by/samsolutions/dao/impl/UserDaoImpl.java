package by.samsolutions.dao.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.User;
import by.samsolutions.entity.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUsername(final String username) {

        List<User> users = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .list();

        return users.stream().findFirst().orElse(null);

    }

    @Override
    public User saveUser(final User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);

        //saving user roles
        user.getUserRole().forEach(userRole -> session.save(userRole));

        //saving user profile
        session.save(user.getProfile());

        return user;
    }

    @Override
    public UserProfile getUserProfile(final String username) {

        return (UserProfile) sessionFactory.getCurrentSession()
                             .createQuery("from UserProfile u where u.username = :username")
                             .setParameter("username", username)
                             .getSingleResult();
    }

    @Override
    public void saveUserProfile(final UserProfile userProfile) {
        sessionFactory.getCurrentSession().merge(userProfile);
    }
}