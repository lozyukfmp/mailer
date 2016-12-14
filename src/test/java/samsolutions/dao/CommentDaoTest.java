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

import by.samsolutions.imgcloud.dao.CommentDao;
import by.samsolutions.imgcloud.dao.PostDao;
import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.CommentEntity;
import by.samsolutions.imgcloud.entity.PostEntity;
import by.samsolutions.imgcloud.entity.user.UserEntity;
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

	private UserEntity user;
	private PostEntity post;

	@Before
	public void init() throws DaoException
	{
		user = new UserEntity();
		user.setUsername("username");
		user.setPassword("password");

		userDao.create(user);

		PostEntity testPost = new PostEntity();
		testPost.setText("Some post text");
		testPost.setDate(new Date());
		testPost.setUsername(user.getUsername());

		post = postDao.create(testPost);
	}

	@Test
	@Transactional
	@Rollback
	public void testCreateComment() throws DaoException
	{

		CommentEntity comment = new CommentEntity();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPostId(post.getId());

		commentDao.create(comment);

		List<CommentEntity> comments = commentDao.findAllByPostId(post.getId(), 2);

		Assert.assertEquals(comments.size(), 1);
		Assert.assertEquals(comment.getText(), comments.get(0).getText());
		Assert.assertEquals(comment.getUsername(), comments.get(0).getUsername());

	}

	@Test
	@Transactional
	@Rollback
	public void testUpdateComment() throws DaoException
	{

		CommentEntity comment = new CommentEntity();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPostId(post.getId());

		commentDao.create(comment);

		comment.setText("Some update comment text");

		commentDao.update(comment);

		List<CommentEntity> comments = commentDao.findAllByPostId(post.getId(), 2);

		Assert.assertEquals(comments.size(), 1);
		Assert.assertEquals(comment.getText(), comments.get(0).getText());
	}

	@Test
	@Transactional
	@Rollback
	public void testDeleteComment() throws DaoException
	{
		CommentEntity comment = new CommentEntity();
		comment.setText("Some comment text");
		comment.setDate(new Date());
		comment.setUsername(user.getUsername());
		comment.setPostId(post.getId());

		comment = commentDao.create(comment);

		commentDao.delete(comment.getId());

		List<CommentEntity> posts = commentDao.findAllByPostId(post.getId(), 2);

		Assert.assertEquals(posts.size(), 0);
	}

	@Test
	@Transactional
	@Rollback
	public void testGetAllComments() throws DaoException
	{

		CommentEntity firstComment = new CommentEntity();
		firstComment.setText("Some comment text");
		firstComment.setDate(new Date());
		firstComment.setUsername(user.getUsername());
		firstComment.setPostId(post.getId());

		CommentEntity secondComment = new CommentEntity();
		secondComment.setText("Some comment text");
		secondComment.setDate(new Date());
		secondComment.setUsername(user.getUsername());
		secondComment.setPostId(post.getId());

		commentDao.create(firstComment);
		commentDao.create(secondComment);

		List<CommentEntity> comments = commentDao.findAllByPostId(post.getId(), 2);

		Assert.assertEquals(comments.size(), 2);
	}
}
