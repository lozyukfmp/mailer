package samsolutions.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.entity.user.UserRoleEntity;
import samsolutions.configuration.HibernateTestConfiguration;

@ContextConfiguration(classes = HibernateTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest
{
	@Autowired
	private UserDao userDao;

	private UserEntity user;

	@Before
	public void init()
	{
		user = new UserEntity();
		user.setUsername("username");
		user.setPassword("password");

		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setEmail("lozyuk-artem@mail.ru");
		userProfile.setFirstName("Ivan");
		userProfile.setSecondName("Ivanov");
		userProfile.setThirdName("Ivanovich");
		userProfile.setUsername(user.getUsername());

		UserRoleEntity userRole = new UserRoleEntity();
		userRole.setRole("ROLE_USER");
		Set<UserRoleEntity> userRoleSet = new HashSet<>();
		userRoleSet.add(userRole);

		user.setProfile(userProfile);
		userProfile.setUsername(user.getUsername());
		user.setUserRole(userRoleSet);
	}

	@Test
	@Transactional
	@Rollback
	public void testCreateUser()
	{
		userDao.create(user);

		List<UserEntity> users = userDao.all();

		Assert.assertEquals(user.getUsername(), users.get(0).getUsername());
		Assert.assertEquals(user.getProfile().getEmail(), users.get(0).getProfile().getEmail());
		Assert.assertEquals(user.getUserRole().size(), users.get(0).getUserRole().size());
		Assert.assertEquals(users.size(), 1);
	}

	@Test
	@Transactional
	@Rollback
	public void testUpdateUserProfile()
	{
		userDao.create(user);

		user.getProfile().setEmail("artemlozyuk@gmail.com");

		userDao.update(user);

		List<UserEntity> users = userDao.all();

		Assert.assertEquals(user.getUsername(), users.get(0).getUsername());
		Assert.assertEquals(user.getProfile().getEmail(), users.get(0).getProfile().getEmail());
		Assert.assertEquals(users.size(), 1);
	}

	@Test
	@Transactional
	@Rollback
	public void testDeleteUser()
	{
		user = userDao.create(user);

		userDao.delete(user.getUsername());

		List<UserEntity> users = userDao.all();

		Assert.assertEquals(users.size(), 0);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetAllUsers()
	{
		userDao.create(user);

		List<UserEntity> users = userDao.all();

		Assert.assertEquals(users.size(), 1);
	}
}
