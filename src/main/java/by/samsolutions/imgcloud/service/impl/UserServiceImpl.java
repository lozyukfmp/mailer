package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.UserConverter;
import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.nodeentity.user.UserNodeEntity;
import by.samsolutions.imgcloud.nodeentity.user.UserRoleNodeEntity;
import by.samsolutions.imgcloud.service.UserService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import by.samsolutions.imgcloud.service.exception.UserAlreadyExistsException;
import by.samsolutions.imgcloud.service.exception.UserNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl extends GenericServiceImpl<UserDto, UserNodeEntity, Long> implements UserService
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
    public UserDto findByUsername(String username) throws ServiceException {
        logger.trace("GETTING USER WITH USERNAME : " + username);
        try
        {
            UserNodeEntity userEntity = userDao.findByUsername(username);
            return userConverter.toDto(userEntity);
        }
        catch (ConverterException e)
        {
            logger.error(e.getMessage(), e);
            throw new UserNotFoundException();
        }
    }

    @Override
	@Transactional
	public UserDto create(final UserDto dto) throws ServiceException
	{
		logger.trace("CREATING USER : " + dto);
		try
		{
			UserNodeEntity userEntity = userConverter.toEntity(dto);

			if (userDao.findByUsername(userEntity.getUsername()) != null)
			{
				throw new UserAlreadyExistsException();
			}

			UserRoleNodeEntity userRole = UserRoleNodeEntity.builder()
                    .username(userEntity.getUsername())
					.role("ROLE_USER").build();
			userEntity.setUserRole(new HashSet<>(Arrays.asList(userRole)));
			userEntity.setEnabled(true);
			userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            UserNodeEntity resultEntity = userDao.save(userEntity);
			UserDto resultDto = userConverter.toDto(resultEntity);

			return resultDto;
		}
		catch (ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> getWithProfileByUsername(final String username) throws ServiceException
	{
		logger.trace("GETTING USER WITH PROFILE : " + username);
		try
		{
			Collection<UserNodeEntity> userEntity = userDao.findByUsernameContaining(username);
			return userConverter.toDtoCollection(userEntity);
		}
		catch (ConverterException e)
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
			UserNodeEntity userNodeEntity = userDao.findByUsername(username);
			if (userNodeEntity != null) {
				userNodeEntity.setEnabled(enabled);
				userDao.save(userNodeEntity);
			}
		}
		catch (Exception e)
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
			Pageable pageRequest = new PageRequest(0, userCount, Sort.Direction.DESC, "username");
			List<UserNodeEntity> userEntityCollection = new ArrayList<>();
            userDao.findByUsername(pageRequest).forEach(entity -> userEntityCollection.add(entity));
			return userConverter.toDtoCollection(userEntityCollection);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void addAdminRole(final String username) throws ServiceException
	{
		try
		{
			UserNodeEntity userEntity = userDao.findByUsername(username);
			UserRoleNodeEntity userRoleEntity = UserRoleNodeEntity.builder().username(userEntity.getUsername()).role("ROLE_ADMIN").build();
			userEntity.getUserRole().add(userRoleEntity);

			userDao.save(userEntity);
		}
		catch (Exception e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void deleteAdminRole(final String username) throws ServiceException {
		try {
			UserNodeEntity userEntity = userDao.findByUsername(username);
			Iterator<UserRoleNodeEntity> iterator = userEntity.getUserRole().iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getRole().equals("ROLE_ADMIN")) {
					iterator.remove();
				}
			}

			userDao.save(userEntity);
		}
		catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public boolean isEnabled(final String username) throws ServiceException
	{
		logger.trace("CHECKING IF ENABLED , USERNAME = " + username);
		try
		{
			UserNodeEntity userEntity = userDao.findByUsername(username);

			return userEntity.isEnabled();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
