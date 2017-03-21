package by.samsolutions.imgcloud.service;

import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.nodeentity.user.UserNodeEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends GenericService<UserDto, UserNodeEntity, Long>
{
	UserDto findByUsername(String username) throws ServiceException;

	Collection<UserDto> getAll(Integer userCount) throws ServiceException;

	Collection<UserDto> getWithProfileByUsername(String username) throws ServiceException;

	void setUserEnabled(String username, Boolean enabled) throws ServiceException;

	boolean isEnabled(String username) throws ServiceException;

	void addAdminRole(String username) throws ServiceException;

	void deleteAdminRole(String username) throws ServiceException;
}
