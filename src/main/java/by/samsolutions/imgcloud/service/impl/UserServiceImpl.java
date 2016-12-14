package by.samsolutions.imgcloud.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.UserConverter;
import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.entity.user.UserEntity;
import by.samsolutions.imgcloud.entity.user.UserRoleEntity;
import by.samsolutions.imgcloud.service.exception.UserAlreadyExistsException;
import by.samsolutions.imgcloud.service.UserService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import by.samsolutions.imgcloud.service.exception.UserNotFoundException;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserDto, UserEntity, String> implements UserService
{
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private UserDao         userDao;
	private UserConverter   userConverter;
	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	@Transactional
	public UserDto create(final UserDto dto) throws ServiceException
	{
		logger.trace("CREATING USER : " + dto);
		try
		{
			UserEntity userEntity = userConverter.toEntity(dto);

			if (userDao.find(userEntity.getUsername()) != null)
			{
				throw new UserAlreadyExistsException();
			}

			UserRoleEntity userRole = UserRoleEntity.builder().username(userEntity.getUsername()).role("ROLE_USER").build();
			userEntity.setUserRole(new HashSet<>(Arrays.asList(userRole)));
			userEntity.setEnabled(true);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

			UserEntity resultEntity = userDao.create(userEntity);
			UserDto resultDto = userConverter.toDto(resultEntity);

			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getWithProfileByUsername(final String username) throws ServiceException
	{
		logger.trace("GETTING USER WITH PROFILE : " + username);
		try
		{
			UserEntity userEntity = userDao.getByUsernameWithProfile(username);
			UserDto userDto = userConverter.toDto(userEntity);

			return userDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new UserNotFoundException();
		}
	}

	@Override
	@Transactional
	public void setUserEnabled(final String username, final Boolean enabled) throws ServiceException
	{
		logger.trace("TRYING TO LOCK/UNLOCK USER WITH USERNAME = " + username);
		try
		{
			userDao.setUserEnabled(username, enabled);
		}
		catch (DaoException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> getAll(final Integer userCount) throws ServiceException
	{
		logger.trace("GETTING USERS COUNT = " + userCount);
		try
		{
			return userConverter.toDtoCollection(userDao.getAll(userCount));
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
