package samsolutions.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
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
import by.samsolutions.entity.Post;
import by.samsolutions.entity.user.User;
import samsolutions.configuration.hibernate.HibernateTestConfiguration;


@DirtiesContext
@ContextConfiguration(classes = HibernateTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class PostDaoTest
{
	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	@Rollback
	public void testCreatePost()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUser(user);

		postDao.create(post);

		List<Post> posts = postDao.all();

		Assert.assertEquals(posts.size(), 1);
		Assert.assertEquals(post.getText(), posts.get(0).getText());
		Assert.assertEquals(post.getUser().getUsername(), posts.get(0).getUser().getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testUpdatePost()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUser(user);

		postDao.create(post);

		post.setText("Changed post text");

		postDao.update(post);

		List<Post> posts = postDao.all();

		Assert.assertEquals(posts.size(), 1);
		Assert.assertEquals(post.getText(), posts.get(0).getText());
		Assert.assertEquals(post.getUser().getUsername(), posts.get(0).getUser().getUsername());
	}

	@Test
	@Transactional
	@Rollback
	public void testDeletePost()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUser(user);

		post = postDao.create(post);

		postDao.delete(post.getId());

		List<Post> posts = postDao.all();

		Assert.assertEquals(posts.size(), 0);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetAllPosts()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post firstPost = new Post();
		firstPost.setText("First post text");
		firstPost.setDate(new Date());
		firstPost.setUser(user);

		Post secondPost = new Post();
		secondPost.setText("Second post text");
		secondPost.setDate(new Date());
		secondPost.setUser(user);

		postDao.create(firstPost);
		postDao.create(secondPost);

		List<Post> posts = postDao.all();

		Assert.assertEquals(posts.size(), 2);
	}
}
