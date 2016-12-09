package by.samsolutions.service;

import org.springframework.stereotype.Service;

import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.exception.ServiceException;

@Service
public interface UserProfileService extends GenericService<UserProfileDto, UserProfileEntity, String>
{
	UserProfileDto uploadUserPhoto(String username, String photoUrl) throws ServiceException;
}
