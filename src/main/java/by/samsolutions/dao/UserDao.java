package by.samsolutions.dao;

import by.samsolutions.entity.User;
import by.samsolutions.entity.UserProfile;

public interface UserDao {

    User findByUsername(String username);

    User saveUser(User user);

    UserProfile getUserProfile(String username);

    void saveUserProfile(UserProfile userProfile);
}
