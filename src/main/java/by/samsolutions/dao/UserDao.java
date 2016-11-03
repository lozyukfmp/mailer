package by.samsolutions.dao;

import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserProfile;

public interface UserDao {

    User findByUsername(String username);

    UserProfile addUserInfo(UserProfile userInfo);

    User saveUser(User user);

    User getUserDetails(String username);
}
