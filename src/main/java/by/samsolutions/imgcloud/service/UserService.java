package by.samsolutions.imgcloud.service;

import java.util.Collection;

import by.samsolutions.imgcloud.dto.UserProfileDto;
import org.springframework.stereotype.Service;

import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.entity.user.UserEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public interface UserService extends GenericService<UserDto, UserEntity, String>
{
	Collection<UserDto> getAll(Integer userCount) throws ServiceException;

	Collection<UserDto> getWithProfileByUsername(String username) throws ServiceException;

	Collection<UserDto> getWithProfileByFullName(UserProfileDto userProfileDto) throws ServiceException;

	void setUserEnabled(String username, Boolean enabled) throws ServiceException;

	boolean isEnabled(String username) throws ServiceException;

	void addAdminRole(String username) throws ServiceException;

	void deleteAdminRole(String username) throws ServiceException;
}
