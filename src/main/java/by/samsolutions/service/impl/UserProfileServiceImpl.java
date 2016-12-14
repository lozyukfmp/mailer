package by.samsolutions.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.UserProfileConverter;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.exception.UserNotFoundException;

@Service
public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileDto, UserProfileEntity, String>
				implements UserProfileService
{
	private static final Logger logger = LogManager.getLogger(UserProfileServiceImpl.class);

	private GenericDao<UserProfileEntity, String> userProfileDao;
	private UserProfileConverter                  userProfileConverter;

	public UserProfileServiceImpl()
	{

	}

	@Autowired
	public UserProfileServiceImpl(@Autowired GenericDao<UserProfileEntity, String> userProfileDao,
	                              @Autowired UserProfileConverter userProfileConverter)
	{
		super(userProfileDao, userProfileConverter);
		this.userProfileDao = userProfileDao;
		this.userProfileConverter = userProfileConverter;
	}

	@Override
	@Transactional
	public UserProfileDto find(final String id) throws ServiceException
	{
		logger.trace("GETTING USER PROFILE BY USERNAME = " + id);
		try
		{
			UserProfileEntity userProfileEntity = userProfileDao.find(id);

			if (userProfileEntity == null)
			{
				throw new UserNotFoundException();
			}

			UserProfileDto userProfileDto = userProfileConverter.toDto(userProfileEntity);

			return userProfileDto;
		}
		catch (DaoException | ConverterException e)
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
			UserProfileEntity userProfileEntity = userProfileDao.find(dto.getUsername());
			UserProfileEntity saveProfile = userProfileConverter.toEntity(dto);
			saveProfile.setImageUrl(userProfileEntity.getImageUrl());

			return userProfileConverter.toDto(userProfileDao.update(saveProfile));
		}
		catch (DaoException | ConverterException e)
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
			UserProfileEntity userProfile = userProfileDao.find(username);
			userProfile.setImageUrl(photoUrl);
			UserProfileEntity updatedProfile = userProfileDao.update(userProfile);
			UserProfileDto resultDto = userProfileConverter.toDto(updatedProfile);

			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
