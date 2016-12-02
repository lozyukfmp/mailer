package samsolutions.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.Comment;
import by.samsolutions.entity.Post;
import by.samsolutions.entity.user.User;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.impl.CommentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CommentServiceTest
{
	@Configuration
	static class CommentServiceConfiguration {
		@Bean
		public CommentService commentService()
		{
			return new CommentServiceImpl();
		}

		@Bean
		public CommentDao commentDao()
		{
			return Mockito.mock(CommentDao.class);
		}

		@Bean
		public UserDao userDao()
		{
			return Mockito.mock(UserDao.class);
		}
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentService commentService;

	private Post post;
	private final List<Comment> commentList = new ArrayList<>();

	@Before
	public void initPostList()
	{
		final User user = new User();
		user.setUsername("Artem");

		post = new Post();
		post.setId(1);
		post.setText("Some firstPost text");
		post.setDate(new Date());

		Comment firstComment = new Comment();
		firstComment.setId(1);
		firstComment.setPostId(post.getId());
		firstComment.setUsername(user.getUsername());
		firstComment.setDate(new Date());
		firstComment.setText("Some firstComment text");

		Comment secondComment = new Comment();
		secondComment.setId(2);
		secondComment.setPostId(post.getId());
		secondComment.setUsername(user.getUsername());
		secondComment.setDate(new Date());
		secondComment.setText("Some secondComment text");

		commentList.add(firstComment);
		commentList.add(secondComment);

		Mockito.when(userDao.find("Artem")).thenReturn(user);
		Mockito.when(commentDao.all()).thenReturn(commentList);
		Mockito.when(commentDao.find(1)).thenReturn(firstComment);
		Mockito.when(commentDao.find(2)).thenReturn(secondComment);
		Mockito.when(commentDao.findAllByPostId(1, 2)).thenReturn(commentList);
		Mockito.when(commentDao.findAllByUsername("Artem")).thenReturn(commentList);

		Mockito.doAnswer(invocationOnMock -> {
			commentList.remove(firstComment);
			return null;
		}).when(commentDao).delete(1);

		Mockito.doAnswer(invocationOnMock -> {
			commentList.remove(secondComment);
			return null;
		}).when(commentDao).delete(2);

		ReflectionTestUtils.setField(commentService, "commentDao", commentDao);
	}

	@Test
	public void testCreateComment()
	{
		final Comment comment = new Comment();
		comment.setText("Some comment text");
		comment.setDate(new Date());

		Mockito.when(commentDao.create(comment)).then(invocationOnMock -> {
			commentList.add(comment);
			return comment;
		});

		Comment resultComment = commentService.createComment(comment);

		List<Comment> posts = commentService.getCommentListByPostId(post.getId(), 2);

		Assert.assertNotNull(resultComment);
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(comment.getText(), resultComment.getText());
		Assert.assertEquals(comment.getDate(), resultComment.getDate());
	}

	@Test
	public void testUpdateComment()
	{
		final Comment comment = new Comment();
		comment.setId(1);
		comment.setText("Some updated comment text");
		comment.setDate(new Date());

		Mockito.when(commentDao.update(comment)).then(invocationOnMock -> {

			commentList.stream()
			        .filter(commentValue -> commentValue.getId() == comment.getId())
			        .findFirst()
			        .ifPresent(commentValue -> commentValue.setText(comment.getText()));

			return comment;
		});

		final Comment resultComment = commentService.updateComment(comment);

		final List<Comment> comments = commentService.getCommentListByPostId(1, 2);

		Assert.assertNotNull(resultComment);
		Assert.assertEquals(commentList.size(), 2);
		Assert.assertEquals(comment.getText(), resultComment.getText());
		Assert.assertEquals(comment.getDate(), resultComment.getDate());
	}

	@Test
	public void testGetComment()
	{
		final Comment firstComment = commentService.getComment(1);
		final Comment secondComment = commentService.getComment(2);
		final Comment thirdComment = commentService.getComment(3);

		Assert.assertNotNull(firstComment);
		Assert.assertNotNull(secondComment);
		Assert.assertNull(thirdComment);
		Assert.assertEquals(firstComment.getText(), "Some firstComment text");
		Assert.assertEquals(secondComment.getText(), "Some secondComment text");
	}

	@Test
	public void testDeleteComment()
	{
		commentService.deleteComment(1);

		final List<Comment> comments = commentService.getCommentListByPostId(1, 2);

		Assert.assertEquals(comments.size(), 1);
		Assert.assertEquals(comments.get(0).getId(), 2);
		Assert.assertEquals(comments.get(0).getText(), "Some secondComment text");
	}

	@Test
	public void testGetAllCommentsByUsername()
	{
		final List<Comment> comments = commentService.getCommentListByUsername("Artem");

		Assert.assertEquals(comments.size(), 2);
	}


	@Test
	public void testGetAllCommentsByPostId()
	{
		final List<Comment> comments = commentService.getCommentListByPostId(1, 2);

		Assert.assertEquals(comments.size(), 2);
	}
}
