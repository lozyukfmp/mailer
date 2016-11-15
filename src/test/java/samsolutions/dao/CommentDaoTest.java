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

import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.PostDao;
import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.Comment;
import by.samsolutions.entity.Post;
import by.samsolutions.entity.user.User;
import samsolutions.configuration.HibernateTestConfiguration;

@DirtiesContext
@ContextConfiguration(classes = HibernateTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CommentDaoTest
{

	@Autowired
	private UserDao userDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Test
	@Transactional
	@Rollback
	public void testCreateComment()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		post = postDao.create(post);

		Comment comment = new Comment();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPost_id(post.getId());

		commentDao.create(comment);

		List<Comment> comments = commentDao.all();

		Assert.assertEquals(comments.size(), 1);
		Assert.assertEquals(comment.getText(), comments.get(0).getText());
		Assert.assertEquals(comment.getUsername(), comments.get(0).getUsername());

	}

	@Test
	@Transactional
	@Rollback
	public void testUpdateComment()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		postDao.create(post);

		Comment comment = new Comment();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPost_id(post.getId());

		commentDao.create(comment);

		comment.setText("Some update comment text");

		commentDao.update(comment);

		List<Comment> comments = commentDao.all();

		Assert.assertEquals(comments.size(), 1);
		Assert.assertEquals(comment.getText(), comments.get(0).getText());
	}

	@Test
	@Transactional
	@Rollback
	public void testDeleteComment()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		post = postDao.create(post);

		Comment comment = new Comment();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPost_id(post.getId());

		comment = commentDao.create(comment);

		commentDao.delete(comment.getId());

		List<Comment> posts = commentDao.all();

		Assert.assertEquals(posts.size(), 0);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetAllComments()
	{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());
		post.setUsername(user.getUsername());

		postDao.create(post);

		Comment firstComment = new Comment();
		firstComment.setText("Some comment text");
		firstComment.setDate(new Date());
		firstComment.setUsername(user.getUsername());
		firstComment.setPost_id(post.getId());

		Comment secondComment = new Comment();
		secondComment.setText("Some comment text");
		secondComment.setDate(new Date());
		secondComment.setUsername(user.getUsername());
		secondComment.setPost_id(post.getId());

		commentDao.create(firstComment);
		commentDao.create(secondComment);

		List<Comment> comments = commentDao.all();

		Assert.assertEquals(comments.size(), 2);
	}
}
