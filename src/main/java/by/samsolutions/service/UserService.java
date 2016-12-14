package by.samsolutions.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.service.exception.ServiceException;

@Service
public interface UserService extends GenericService<UserDto, UserEntity, String>
{
	Collection<UserDto> getAll(Integer userCount) throws ServiceException;

	UserDto getWithProfileByUsername(String username) throws ServiceException;

	void setUserEnabled(String username, Boolean enabled) throws ServiceException;
}
