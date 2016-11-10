package by.samsolutions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.UserProfileDao;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService
{
	@Autowired
	private UserProfileDao userProfileDao;

	@Override
	@Transactional(readOnly = true)
	public UserProfile getUserProfile(final String username)
	{
		return userProfileDao.find(username);
	}

	@Override
	@Transactional
	public UserProfile updateUserProfile(final UserProfileDto userProfileDto)
	{
		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(userProfileDto.getUsername());
		userProfile.setEmail(userProfileDto.getEmail());
		userProfile.setFirstName(userProfileDto.getFirstName());
		userProfile.setSecondName(userProfileDto.getSecondName());
		userProfile.setThirdName(userProfileDto.getThirdName());

		return userProfileDao.update(userProfile);
	}
}
