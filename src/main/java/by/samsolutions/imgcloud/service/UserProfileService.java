package by.samsolutions.imgcloud.service;

import org.springframework.stereotype.Service;

import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.nodeentity.user.UserProfileNodeEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public interface UserProfileService extends GenericService<UserProfileDto, UserProfileNodeEntity, Long>
{
	UserProfileDto uploadUserPhoto(String username, String photoUrl) throws ServiceException;
	UserProfileDto findByUsername(String username) throws ServiceException;
}
