package by.samsolutions.imgcloud.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.UserProfileConverter;
import by.samsolutions.imgcloud.dao.GenericDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.entity.user.UserProfileEntity;
import by.samsolutions.imgcloud.service.UserProfileService;
import by.samsolutions.imgcloud.service.exception.UserNotFoundException;
import by.samsolutions.imgcloud.service.exception.ServiceException;

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
			userProfileEntity.setEmail(dto.getEmail());
			userProfileEntity.setFirstName(dto.getFirstName());
			userProfileEntity.setSecondName(dto.getSecondName());
			userProfileEntity.setThirdName(dto.getThirdName());

			return userProfileConverter.toDto(userProfileDao.update(userProfileEntity));
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public UserProfileDto uploadUserPhoto(UserProfileDto userProfileDto) throws ServiceException
	{
		//logger.trace("UPLOADING PHOTO (USERNAME = " + username + ", PHOTO_URL = " + photoUrl + ").");
		try
		{
			UserProfileEntity userProfile = userProfileConverter.toEntity(userProfileDto);
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
