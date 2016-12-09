package by.samsolutions.service;

import org.springframework.stereotype.Service;

import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.UserEntity;

@Service
public interface UserService extends GenericService<UserDto, UserEntity, String>
{

	/*UserEntity create(UserDto accountDto);*/
}
