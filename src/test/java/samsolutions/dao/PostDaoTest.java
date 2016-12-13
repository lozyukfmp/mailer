package samsolutions.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.PostDao;
import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.entity.user.UserEntity;
import samsolutions.configuration.HibernateTestConfiguration;

@DirtiesContext
@ContextConfiguration(classes = HibernateTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostDaoTest
{
	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	private UserEntity user;

	@Before
	public void init() throws DaoException
	{
		user = new UserEntity();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);
	}

	@Test
	@Transactional
	@Rollback
	public void testCreatePost() throws DaoException
	{
		PostEntity post = new PostEntity();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		postDao.create(post);

		List<PostEntity> posts = postDao.all(user.getUsername(), 1);

		Assert.assertEquals(posts.size(), 1);
		Assert.assertEquals(post.getText(), posts.get(0).getText());
		Assert.assertEquals(post.getUsername(), posts.get(0).getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testUpdatePost() throws DaoException
	{
		PostEntity post = new PostEntity();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		postDao.create(post);

		post.setText("Changed post text");

		postDao.update(post);

		List<PostEntity> posts = postDao.all(user.getUsername(), 1);

		Assert.assertEquals(posts.size(), 1);
		Assert.assertEquals(post.getText(), posts.get(0).getText());
		Assert.assertEquals(post.getUsername(), posts.get(0).getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testDeletePost() throws DaoException
	{
		PostEntity post = new PostEntity();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		post = postDao.create(post);

		postDao.delete(post.getId());

		List<PostEntity> posts = postDao.all(user.getUsername(), 1);

		Assert.assertEquals(posts.size(), 0);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetAllPosts() throws DaoException
	{
		PostEntity firstPost = new PostEntity();
		firstPost.setText("First post text");
		firstPost.setDate(new Date());
		firstPost.setUsername(user.getUsername());

		PostEntity secondPost = new PostEntity();
		secondPost.setText("Second post text");
		secondPost.setDate(new Date());
		secondPost.setUsername(user.getUsername());

		postDao.create(firstPost);
		postDao.create(secondPost);

		List<PostEntity> posts = postDao.all(user.getUsername(), 2);

		Assert.assertEquals(posts.size(), 2);
	}
}
