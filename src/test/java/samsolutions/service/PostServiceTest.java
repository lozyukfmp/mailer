package samsolutions.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import by.samsolutions.converter.impl.CommentConverter;
import by.samsolutions.converter.impl.PostConverter;
import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.PostDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.dto.PostDto;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.service.PostService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.impl.PostServiceImpl;

import static org.mockito.Matchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PostServiceTest
{
	private final List<PostEntity> postList = new ArrayList<>();
	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private PostConverter postConverter;

	@Autowired
	private CommentConverter commentConverter;

	@Autowired
	private GenericDao<UserEntity, String> userDao;

	private PostService postService;

	@Before
	public void initPostList() throws DaoException
	{
		final UserEntity user = UserEntity.builder().username("Artem").build();

		final PostEntity firstPost = PostEntity.builder()
		                                       .id(1)
		                                       .text("Some firstPost text")
		                                       .date(new Date())
		                                       .comments(Collections.EMPTY_LIST)
		                                       .build();

		final PostEntity secondPost = PostEntity.builder()
		                                        .id(2)
		                                        .text("Some secondPost text")
		                                        .date(new Date())
		                                        .comments(Collections.EMPTY_LIST)
		                                        .build();

		final PostEntity createdPost = PostEntity.builder()
		                                         .id(3)
		                                         .text("Some createdPost text")
		                                         .date(new Date())
		                                         .comments(Collections.EMPTY_LIST)
		                                         .build();

		final PostEntity updatedPost = PostEntity.builder()
		                                         .id(4)
		                                         .text("Some updatedPost text")
		                                         .date(new Date())
		                                         .comments(Collections.EMPTY_LIST)
		                                         .build();

		postList.add(firstPost);
		postList.add(secondPost);

		Mockito.when(userDao.find("Artem")).thenReturn(user);
		Mockito.when(postDao.all(user.getUsername(), 2)).thenReturn(postList);
		Mockito.when(postDao.find(1)).thenReturn(firstPost);
		Mockito.when(commentDao.findAllByPostId(1, 2)).thenReturn(Collections.EMPTY_LIST);
		Mockito.when(commentDao.findAllByPostId(2, 2)).thenReturn(Collections.EMPTY_LIST);
		Mockito.when(postDao.find(2)).thenReturn(secondPost);

		Mockito.doAnswer(invocationOnMock -> {
			postList.remove(firstPost);
			return null;
		}).when(postDao).delete(1);

		Mockito.doAnswer(invocationOnMock -> {
			postList.remove(secondPost);
			return null;
		}).when(postDao).delete(2);

		Mockito.when(postDao.create(any())).then(invocationOnMock -> {
			postList.add(createdPost);
			return createdPost;
		});

		Mockito.when(postDao.update(any())).then(invocationOnMock -> updatedPost);

		ReflectionTestUtils.setField(postConverter, "commentConverter", commentConverter);
		postService = new PostServiceImpl(postDao, postConverter);
		ReflectionTestUtils.setField(postService, "commentDao", commentDao);
	}

	@Test
	public void testCreatePost() throws ServiceException
	{
		final PostDto postDto = PostDto.builder().text("Some createdPost text").comments(Collections.EMPTY_LIST).build();

		PostDto resultPost = postService.create(postDto);

		Collection<PostDto> posts = postService.getAll("Artem", 2);

		Assert.assertNotNull(resultPost);
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(postDto.getText(), resultPost.getText());
	}

	@Test
	public void testUpdatePost() throws ServiceException
	{
		final PostDto postDto = PostDto.builder().id("4").text("Some updatedPost text").comments(Collections.EMPTY_LIST).build();

		final PostDto resultPost = postService.update(postDto);

		final Collection<PostDto> posts = postService.getAll("Artem", 2);

		Assert.assertNotNull(resultPost);
		Assert.assertEquals(posts.size(), 2);
		Assert.assertEquals(postDto.getText(), resultPost.getText());
	}

	@Test
	public void testGetPost() throws ServiceException
	{
		final PostDto firstPost = postService.find(1);
		final PostDto secondPost = postService.find(2);

		Assert.assertNotNull(firstPost);
		Assert.assertNotNull(secondPost);
		Assert.assertEquals(firstPost.getText(), "Some firstPost text");
		Assert.assertEquals(secondPost.getText(), "Some secondPost text");
	}

	@Test
	public void testDeletePost() throws ServiceException
	{
		postService.delete(1);

		final Collection<PostDto> posts = postService.getAll("Artem", 2);

		Assert.assertEquals(posts.size(), 1);
	}

	@Test
	public void testGetAllPost() throws ServiceException
	{
		final Collection<PostDto> posts = postService.getAll("Artem", 2);

		Assert.assertEquals(posts.size(), 2);
	}

	@Configuration
	static class PostServiceConfiguration
	{

		@Bean
		public PostDao postDao()
		{
			return Mockito.mock(PostDao.class);
		}

		@Bean
		public CommentDao commentDao()
		{
			return Mockito.mock(CommentDao.class);
		}

		@Bean
		public PostConverter postConverter()
		{
			return new PostConverter();
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