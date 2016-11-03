package by.samsolutions.service;

import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserProfile;

public interface UserService {

    User findByUsername(String username);

    User createUserAccount(UserDto accountDto);

    UserProfile getUserProfileInfo(String username);

		void saveUserProfileInfo(UserProfileDto userProfileDto);
}
