package by.samsolutions.service;

import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfile;

public interface UserProfileService
{
	UserProfile getUserProfile(String username);

	UserProfile updateUserProfile(UserProfileDto userProfile);

	UserProfile uploadUserPhoto(String username, String photoUrl);
}
