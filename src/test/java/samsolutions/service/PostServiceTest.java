package samsolutions.service;

import java.util.ArrayList;
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

import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.PostDao;
import by.samsolutions.entity.Post;
import by.samsolutions.entity.user.User;
import by.samsolutions.service.PostService;
import by.samsolutions.service.impl.PostServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PostServiceTest
{
	@Configuration
	static class PostServiceConfiguration {
		@Bean
		public PostService postService()
		{
			return new PostServiceImpl();
		}

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
		public GenericDao<User, String> userDao()
		{
			return Mockito.mock(GenericDao.class);
		}
	}

	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private GenericDao<User, String> userDao;

	@Autowired
	private PostService postService;

	private final List<Post> postList = new ArrayList<>();

	@Before
	public void initPostList()
	{
		final User user = new User();
		user.setUsername("Artem");

		final Post firstPost = new Post();
		firstPost.setId(1);
		firstPost.setText("Some firstPost text");
		firstPost.setDate(new Date());

		final Post secondPost = new Post();
		secondPost.setId(2);
		secondPost.setText("Some secondPost text");
		secondPost.setDate(new Date());

		postList.add(firstPost);
		postList.add(secondPost);

		Mockito.when(userDao.find("Artem")).thenReturn(user);
		Mockito.when(postDao.all(user.getUsername(), 2)).thenReturn(postList);
		Mockito.when(postDao.find(1)).thenReturn(firstPost);
		Mockito.when(postDao.find(2)).thenReturn(secondPost);
		Mockito.when(commentDao.findAllByPostId(1, 0)).thenReturn(Collections.EMPTY_LIST);
		Mockito.when(commentDao.findAllByPostId(2, 0)).thenReturn(Collections.EMPTY_LIST);

		Mockito.doAnswer(invocationOnMock -> {
			postList.remove(firstPost);
			return null;
		}).when(postDao).delete(1);

		Mockito.doAnswer(invocationOnMock -> {
			postList.remove(secondPost);
			return null;
		}).when(postDao).delete(2);

		ReflectionTestUtils.setField(postService, "postDao", postDao);
		ReflectionTestUtils.setField(postService, "commentDao", commentDao);
	}

	@Test
	public void testCreatePost()
	{
		final Post post = new Post();
		post.setText("Some post text");
		post.setDate(new Date());

		Mockito.when(postDao.create(post)).then(invocationOnMock -> {
			postList.add(post);
			return post;
		});

		Post resultPost = postService.createPost(post);

		List<Post> posts = postService.getAll("Artem", 2);

		Assert.assertNotNull(resultPost);
		Assert.assertEquals(posts.size(), 3);
		Assert.assertEquals(post.getText(), resultPost.getText());
		Assert.assertEquals(post.getDate(), resultPost.getDate());
	}

	@Test
	public void testUpdatePost()
	{
		final Post post = new Post();
		post.setText("Some updated post text");
		post.setDate(new Date());
		post.setId(1);

		Mockito.when(postDao.update(post)).then(invocationOnMock -> {

			postList.stream()
			        .filter(postValue -> postValue.getId() == post.getId())
			        .findFirst()
			        .ifPresent(postValue -> postValue.setText(post.getText()));

			return post;
		});

		final Post resultPost = postService.updatePost(post);

		final List<Post> posts = postService.getAll("Artem", 2);

		Assert.assertNotNull(resultPost);
		Assert.assertEquals(posts.size(), 2);
		Assert.assertEquals(post.getText(), resultPost.getText());
		Assert.assertEquals(post.getDate(), resultPost.getDate());
	}

	@Test
	public void testGetPost()
	{
		final Post firstPost = postService.getPost(1);
		final Post secondPost = postService.getPost(2);
		final Post thirdPost = postService.getPost(3);

		Assert.assertNotNull(firstPost);
		Assert.assertNotNull(secondPost);
		Assert.assertNull(thirdPost);
		Assert.assertEquals(firstPost.getText(), "Some firstPost text");
		Assert.assertEquals(secondPost.getText(), "Some secondPost text");
	}

	@Test
	public void testDeletePost()
	{
		postService.deletePost(1);

		final List<Post> posts = postService.getAll("Artem", 2);

		Assert.assertEquals(posts.size(), 1);
		Assert.assertEquals(posts.get(0).getId(), 2);
		Assert.assertEquals(posts.get(0).getText(), "Some secondPost text");
	}

	@Test
	public void testGetAllPost()
	{
		final List<Post> posts = postService.getAll("Artem", 2);

		Assert.assertEquals(posts.size(), 2);
	}
}