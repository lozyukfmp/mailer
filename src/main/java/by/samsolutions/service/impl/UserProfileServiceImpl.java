package by.samsolutions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.UserProfileDao;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService
{
	private static final String NO_AVATAR_IMAGE_URL = "/static/core/pictures/no_avatar.jpg";

	@Autowired
	private UserProfileDao userProfileDao;

	@Override
	@Transactional(readOnly = true)
	public UserProfile getUserProfile(final String username)
	{
		UserProfile userProfile = userProfileDao.find(username);

		if (userProfile.getImageUrl() == null)
		{
			userProfile.setImageUrl(NO_AVATAR_IMAGE_URL);
		}

		return userProfile;
	}

	@Override
	@Transactional
	public UserProfile updateUserProfile(final UserProfileDto userProfileDto)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userProfileDto.setUsername(auth.getName());

		UserProfile retrievedProfile = userProfileDao.find(userProfileDto.getUsername());

		UserProfile updateProfile = new UserProfile();
		updateProfile.setUsername(userProfileDto.getUsername());
		updateProfile.setEmail(userProfileDto.getEmail());
		updateProfile.setFirstName(userProfileDto.getFirstName());
		updateProfile.setSecondName(userProfileDto.getSecondName());
		updateProfile.setThirdName(userProfileDto.getThirdName());
		updateProfile.setImageUrl(retrievedProfile.getImageUrl());

		return userProfileDao.update(updateProfile);
	}

	@Override
	@Transactional
	public UserProfile uploadUserPhoto(final String username, final String photoUrl)
	{
		UserProfile userProfile = userProfileDao.find(username);
		userProfile.setImageUrl(photoUrl);
		userProfileDao.update(userProfile);

		return userProfile;
	}
}
