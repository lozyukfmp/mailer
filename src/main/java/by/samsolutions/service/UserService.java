package by.samsolutions.service;

import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.User;

public interface UserService
{
	User createUserAccount(UserDto accountDto);
}
