package by.samsolutions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.UserProfileConverter;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.exception.UserNotFoundException;

@Service
public class UserProfileServiceImpl extends GenericServiceImpl<UserProfileDto, UserProfileEntity, String>
				implements UserProfileService
{

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
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public UserProfileDto uploadUserPhoto(final String username, final String photoUrl) throws ServiceException
	{
		try
		{
			UserProfileEntity userProfile = userProfileDao.find(username);
			userProfile.setImageUrl(photoUrl);
			UserProfileEntity updatedProfile = userProfileDao.update(userProfile);
			UserProfileDto resultDto = userProfileConverter.toDto(updatedProfile);

			return resultDto;
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}
}
