package by.samsolutions.service;

import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.User;

public interface UserService {

    User findByUsername(String username);

    User createUserAccount(UserDto accountDto);

    User getUserProfileInfo(String username);

}
