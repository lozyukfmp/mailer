package by.samsolutions.imgcloud.service;

import org.springframework.stereotype.Service;

import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.entity.user.UserProfileEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public interface UserProfileService extends GenericService<UserProfileDto, UserProfileEntity, String>
{
	UserProfileDto uploadUserPhoto(String username, String photoUrl) throws ServiceException;
}
