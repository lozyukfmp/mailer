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

	/*@Autowired
	private GenericDao<UserProfileEntity, String> userProfileDao;*/

/*
	@Override
	@Transactional(readOnly = true)
	public UserProfileEntity getUserProfile(final String username)
	{
		UserProfileEntity userProfile = userProfileDao.find(username);

		if (userProfile != null && userProfile.getImageUrl() == null)
		{
			userProfile.setImageUrl(NO_AVATAR_IMAGE_URL);
		}

		return userProfile;
	}

	@Override
	@Transactional
	public UserProfileEntity updateUserProfile(final UserProfileDto userProfileDto)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userProfileDto.setUsername(auth.getName());

		UserProfileEntity retrievedProfile = userProfileDao.find(userProfileDto.getUsername());

		retrievedProfile.setUsername(userProfileDto.getUsername());
		retrievedProfile.setEmail(userProfileDto.getEmail());
		retrievedProfile.setFirstName(userProfileDto.getFirstName());
		retrievedProfile.setSecondName(userProfileDto.getSecondName());
		retrievedProfile.setThirdName(userProfileDto.getThirdName());
		retrievedProfile.setImageUrl(retrievedProfile.getImageUrl());

		return userProfileDao.update(retrievedProfile);
	}
*/

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
