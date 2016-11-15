package by.samsolutions.service.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.UserProfileDao;
import by.samsolutions.dao.UserRoleDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.entity.user.UserRole;
import by.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User createUserAccount(final UserDto accountDto)
	{
		if (userDao.find(accountDto.getUsername()) != null)
		{
			return null;
		}

		User user = new User();

		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		//userRole.set(user);
		Set<UserRole> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);


		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(accountDto.getUsername());
		userProfile.setEmail(accountDto.getUserProfileDto().getEmail());
		userProfile.setFirstName(accountDto.getUserProfileDto().getFirstName());
		userProfile.setSecondName(accountDto.getUserProfileDto().getSecondName());
		userProfile.setThirdName(accountDto.getUserProfileDto().getThirdName());

		user.setUsername(accountDto.getUsername());
		user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		user.setUserRole(userRoleSet);
		user.setEnabled(true);
		user.setProfile(userProfile);

		userDao.create(user);
		//userRoleDao.create(userRole);

		return user;
	}
}
