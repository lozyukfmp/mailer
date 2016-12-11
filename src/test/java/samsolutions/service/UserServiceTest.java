package samsolutions.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import by.samsolutions.converter.impl.UserConverter;
import by.samsolutions.converter.impl.UserProfileConverter;
import by.samsolutions.dao.UserDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.UserService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.exception.UserAlreadyExistsException;
import by.samsolutions.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest
{
	@Configuration
	static class UserServiceConfiguration {

		@Bean
		public UserDao userDao() {
			return Mockito.mock(UserDao.class);
		}

		@Bean
		public UserConverter userConverter() {
			return new UserConverter();
		}

		@Bean
		public UserProfileConverter userProfileConverter() {
			return new UserProfileConverter();
		}

		@Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
	}

	private UserService userService;
	private UserEntity  user;
	private UserDto     userDto;
	private UserEntity  sucessUser;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private UserProfileConverter userProfileConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Before
	public void init()
	{
		user = UserEntity.builder().password("password").username("username").build();

		userDto = UserDto.builder().password("password").username("username").userProfileDto(new UserProfileDto()).build();

		sucessUser = UserEntity.builder().password("password").username("username1").profile(new UserProfileEntity()).build();

		Mockito.when(userDao.find("username")).thenReturn(user);
		Mockito.when(userDao.find("username1")).thenReturn(null);

		ReflectionTestUtils.setField(userConverter, "userProfileConverter", userProfileConverter);
		userService = new UserServiceImpl(userDao, userConverter);
		ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);

	}

	@Test(expected = UserAlreadyExistsException.class)
	public void testCreateUserFailure() throws ServiceException
	{
		userService.create(userDto);
	}

	/*@Test
	public void testCreateUserSuccess()
	{

		UserEntity resultUser = userService.create(sucessUser);

		Assert.assertNotNull(resultUser);
		Assert.assertEquals(resultUser.getUsername(), user.getUsername());
		Assert.assertTrue(passwordEncoder.matches(
						user.getPassword(),
						resultUser.getPassword()));
	}*/
}
