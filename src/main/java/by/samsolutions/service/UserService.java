package by.samsolutions.service;

import by.samsolutions.entity.user.User;

public interface UserService {

    User findByUsername(String username);

    User findById(int id);
}
