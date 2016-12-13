package by.samsolutions.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.UserConverter;
import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserRoleEntity;
import by.samsolutions.service.UserService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.exception.UserAlreadyExistsException;
import by.samsolutions.service.exception.UserNotFoundException;

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
		catch (DaoException e)
		{
			throw new ServiceException(e);
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getWithProfileByUsername(final String username) throws ServiceException
	{
		try
		{
			UserEntity userEntity = userDao.getByUsernameWithProfile(username);
			UserDto userDto = userConverter.toDto(userEntity);

			return userDto;
		}
		catch (DaoException e)
		{
			throw new UserNotFoundException();
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void setUserEnabled(final String username, final Boolean enabled) throws ServiceException
	{
		try
		{
			userDao.setUserEnabled(username, enabled);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> getAll(final Integer userCount) throws ServiceException
	{
		try
		{
			return userConverter.toDtoCollection(userDao.getAll(userCount));
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
		catch (DaoException e)
		{
			throw new ServiceException(e);
		}
	}
}
