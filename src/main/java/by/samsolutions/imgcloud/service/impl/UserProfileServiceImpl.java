package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.UserProfileConverter;
import by.samsolutions.imgcloud.dao.UserProfileDao;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.nodeentity.user.UserProfileNodeEntity;
import by.samsolutions.imgcloud.service.UserProfileService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import by.samsolutions.imgcloud.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileDto, UserProfileNodeEntity, Long>
				implements UserProfileService
{
	private static final Logger logger = LogManager.getLogger(UserProfileServiceImpl.class);

	private UserProfileDao userProfileDao;
	private UserProfileConverter                  userProfileConverter;

	public UserProfileServiceImpl()
	{

	}

	@Autowired
	public UserProfileServiceImpl(@Autowired UserProfileDao userProfileDao,
	                              @Autowired UserProfileConverter userProfileConverter)
	{
		super(userProfileDao, userProfileConverter);
		this.userProfileDao = userProfileDao;
		this.userProfileConverter = userProfileConverter;
	}

	@Override
	@Transactional
	public UserProfileDto findByUsername(final String id) throws ServiceException
	{
		logger.trace("GETTING USER PROFILE BY USERNAME = " + id);
		try
		{
			UserProfileNodeEntity userProfileEntity = userProfileDao.findByUsername(id);

			if (userProfileEntity == null)
			{
				throw new UserNotFoundException();
			}

			UserProfileDto userProfileDto = userProfileConverter.toDto(userProfileEntity);

			return userProfileDto;
		}
		catch (ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public UserProfileDto update(final UserProfileDto dto) throws ServiceException
	{
		logger.trace("UPDATING USER PROFILE : " + dto);
		try
		{
			UserProfileNodeEntity userProfileEntity = userProfileDao.findByUsername(dto.getUsername());
			UserProfileNodeEntity saveProfile = userProfileConverter.toEntity(dto);
			saveProfile.setImageUrl(userProfileEntity.getImageUrl());

			return userProfileConverter.toDto(userProfileDao.save(saveProfile));
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public UserProfileDto uploadUserPhoto(final String username, final String photoUrl) throws ServiceException
	{
		logger.trace("UPLOADING PHOTO (USERNAME = " + username + ", PHOTO_URL = " + photoUrl + ").");
		try
		{
			UserProfileNodeEntity userProfile = userProfileDao.findByUsername(username);
			userProfile.setImageUrl(photoUrl);
			UserProfileNodeEntity updatedProfile = userProfileDao.save(userProfile);
			UserProfileDto resultDto = userProfileConverter.toDto(updatedProfile);

			return resultDto;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
