package by.samsolutions.dao;

import by.samsolutions.entity.user.User;

public interface UserDao {

    User findByUsername(String username);

    User findById(int id);
}
