package samsolutions.service;

import org.junit.Assert;
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

import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.UserRoleDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.service.UserService;
import by.samsolutions.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class UserServiceTest
{
	@Configuration
	static class UserServiceConfiguration {
		@Bean
		public UserService userService() {
			return new UserServiceImpl();
		}

		@Bean
		public UserDao userDao() {
			return Mockito.mock(UserDao.class);
		}

		@Bean
		public UserRoleDao userRoleDao() {
			return Mockito.mock(UserRoleDao.class);
		}

		@Bean
		public PasswordEncoder passwordEncoder(){
			return new BCryptPasswordEncoder();
		}
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void testCreateUserFailure()
	{

		User user = new User();
		user.setPassword("password");
		user.setUsername("username");

		UserDto userDto = new UserDto();
		userDto.setUsername("username");
		userDto.setPassword("password");

		Mockito.when(userDao.find("username")).thenReturn(user);
		ReflectionTestUtils.setField(userService, "userDao", userDao);

		User resultUser = userService.createUserAccount(userDto);

		Assert.assertEquals(resultUser, null);
	}

	@Test
	public void testCreateUserSuccess()
	{
		User user = new User();
		user.setPassword("password");
		user.setUsername("username");

		UserDto userDto = new UserDto();
		userDto.setUsername("username");
		userDto.setPassword("password");
		userDto.setUserProfileDto(new UserProfileDto());

		Mockito.when(userDao.find("username")).thenReturn(null);
		ReflectionTestUtils.setField(userService, "userDao", userDao);

		User resultUser = userService.createUserAccount(userDto);

		Assert.assertNotNull(resultUser);
		Assert.assertEquals(resultUser.getUsername(), userDto.getUsername());
		Assert.assertTrue(passwordEncoder.matches(
						userDto.getPassword(),
						resultUser.getPassword()));
	}
}
