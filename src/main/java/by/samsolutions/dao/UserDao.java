package by.samsolutions.dao;

import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserInfo;

public interface UserDao {

    User findByUsername(String username);

    UserInfo addUserInfo(UserInfo userInfo);

    User saveUser(User user);
}
