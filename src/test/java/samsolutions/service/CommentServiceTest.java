package samsolutions.service;

import java.util.ArrayList;
import java.util.Collection;
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

import by.samsolutions.converter.impl.CommentConverter;
import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.entity.CommentEntity;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.impl.CommentServiceImpl;

import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CommentServiceTest
{
	private final List<CommentEntity> commentList = new ArrayList<>();
	@Autowired
	private GenericDao<UserEntity, String> userDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentConverter commentConverter;

	private CommentService commentService;

	private PostEntity post;

	@Before
	public void initPostList() throws DaoException
	{
		final UserEntity user = UserEntity.builder().username("Artem").build();

		post = PostEntity.builder().id(1).text("Some firstPost text").date(new Date()).build();

		CommentEntity firstComment = CommentEntity.builder()
		                                          .id(1)
		                                          .postId(post.getId())
		                                          .username(user.getUsername())
		                                          .date(new Date())
		                                          .text("Some firstComment text")
		                                          .build();

		CommentEntity secondComment = CommentEntity.builder()
		                                           .id(2)
		                                           .postId(post.getId())
		                                           .username(user.getUsername())
		                                           .date(new Date())
		                                           .text("Some secondComment text")
		                                           .build();

		CommentEntity createdComment = CommentEntity.builder()
		                                            .id(3)
		                                            .postId(post.getId())
		                                            .username(user.getUsername())
		                                            .date(new Date())
		                                            .text("Some createdComment text")
		                                            .build();

		CommentEntity updatedComment = CommentEntity.builder()
		                                            .id(4)
		                                            .postId(post.getId())
		                                            .username(user.getUsername())
		                                            .date(new Date())
		                                            .text("Some updateComment text")
		                                            .build();

		commentList.add(firstComment);
		commentList.add(secondComment);

		Mockito.when(userDao.find(user.getUsername())).thenReturn(user);
		Mockito.when(commentDao.all()).thenReturn(commentList);
		Mockito.when(commentDao.find(1)).thenReturn(firstComment);
		Mockito.when(commentDao.find(2)).thenReturn(secondComment);
		Mockito.when(commentDao.findAllByPostId(post.getId(), 2)).thenReturn(commentList);
		Mockito.when(commentDao.findAllByPostId(post.getId(), 4)).thenReturn(commentList);

		Mockito.doAnswer(invocationOnMock -> {
			commentList.remove(firstComment);
			return null;
		}).when(commentDao).delete(1);

		Mockito.doAnswer(invocationOnMock -> {
			commentList.remove(secondComment);
			return null;
		}).when(commentDao).delete(2);

		Mockito.when(commentDao.create(any())).then(invocationOnMock -> {
			commentList.add(createdComment);
			return createdComment;
		});

		Mockito.when(commentDao.update(any())).then(invocationOnMock -> updatedComment);

		commentService = new CommentServiceImpl(commentDao, commentConverter);
	}

	@Test
	public void testCreateComment() throws ServiceException
	{

		final CommentDto commentDto = CommentDto.builder().id("3").postId("1").text("Some createdComment text").build();

		CommentDto resultComment = commentService.create(commentDto);

		Collection<CommentDto> posts = commentService.getCommentListByPostId(post.getId(), 4);

		Assert.assertNotNull(resultComment);
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(commentDto.getText(), resultComment.getText());
		Assert.assertEquals(commentDto.getId(), resultComment.getId());
		Assert.assertEquals(commentDto.getPostId(), resultComment.getPostId());
	}

	@Test
	public void testUpdateComment() throws ServiceException
	{
		final CommentDto commentDto = new CommentDto();
		commentDto.setId("4");
		commentDto.setPostId("1");
		commentDto.setText("Some updateComment text");

		final CommentDto resultComment = commentService.update(commentDto);

		Assert.assertNotNull(resultComment);
		Assert.assertEquals(commentList.size(), 2);
		Assert.assertEquals(commentDto.getText(), resultComment.getText());
	}

	@Test
	public void testGetComment() throws ServiceException
	{
		final CommentDto firstComment = commentService.find(1);
		final CommentDto secondComment = commentService.find(2);

		Assert.assertNotNull(firstComment);
		Assert.assertNotNull(secondComment);
		Assert.assertEquals(firstComment.getText(), "Some firstComment text");
		Assert.assertEquals(secondComment.getText(), "Some secondComment text");
	}

	@Test
	public void testDeleteComment() throws ServiceException
	{
		commentService.delete(1);

		final Collection<CommentDto> comments = commentService.getCommentListByPostId(1, 2);

		Assert.assertEquals(comments.size(), 1);
	}

	@Test
	public void testGetAllCommentsByPostId() throws ServiceException
	{
		final Collection<CommentDto> comments = commentService.getCommentListByPostId(1, 2);

		Assert.assertEquals(comments.size(), 2);
	}

	@Configuration
	static class CommentServiceConfiguration
	{
		@Bean
		public CommentDao commentDao()
		{
			return Mockito.mock(CommentDao.class);
		}

		@Bean
		public CommentConverter commentConverter()
		{
			return new CommentConverter();
		}

		@Bean
		public GenericDao<UserEntity, String> userDao()
		{
			return Mockito.mock(GenericDao.class);
		}
	}
}
