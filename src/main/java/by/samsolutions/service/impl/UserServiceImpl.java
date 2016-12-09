package by.samsolutions.service.impl;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.UserConverter;
import by.samsolutions.dao.UserDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserRoleEntity;
import by.samsolutions.service.UserService;
import by.samsolutions.service.exception.ServiceException;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserDto, UserEntity, String> implements UserService
{
	private UserDao       userDao;
	private UserConverter userConverter;

	public UserServiceImpl()
	{

	}

	@Autowired
	public UserServiceImpl(@Autowired UserDao userDao, @Autowired UserConverter userConverter)
	{
		super(userDao, userConverter);
		this.userDao = userDao;
		this.userConverter = userConverter;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDto create(final UserDto dto) throws ServiceException
	{
		try
		{
			UserEntity userEntity = userConverter.toEntity(dto);

			if (userDao.find(userEntity.getUsername()) != null)
			{
				return null;
			}

			UserRoleEntity userRole = UserRoleEntity.builder().username(userEntity.getUsername()).role("ROLE_USER").build();
			userEntity.setUserRole(new HashSet<>(Arrays.asList(userRole)));
			userEntity.setEnabled(true);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

			UserEntity resultEntity = userDao.create(userEntity);
			UserDto resultDto = userConverter.toDto(resultEntity);

			return resultDto;
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

	/*@Override
	@Transactional
	public UserEntity createUserAccount(final UserDto accountDto)
	{
		if (userDao.find(accountDto.getUsername()) != null)
		{
			return null;
		}

		UserEntity user = new UserEntity();

		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setRole("ROLE_USER");
		Set<UserRoleEntity> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);

		UserProfileEntity userProfile = new UserProfileEntity();
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

		return user;
	}*/
}
