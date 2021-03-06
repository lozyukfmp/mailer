package samsolutions.controller;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.samsolutions.configuration.web.SpringWebConfig;
import by.samsolutions.controller.PostController;
import by.samsolutions.entity.Post;
import by.samsolutions.service.PostService;
import samsolutions.configuration.WebTestConfiguration;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfiguration.class, SpringWebConfig.class})
@WebAppConfiguration
public class PostControllerTest
{
	@Autowired
	private PostController postController;

	@Autowired
	private PostService postService;

	private MockMvc mockMvc;

	@Before
	public void init()
	{

		Post firstPost = Post.builder().id(1).date(new Date()).text("Some firstPost text").username("ArtemLozyuk").build();
		Post secondPost = Post.builder().id(2).date(new Date()).text("Some secondPost text").username("ArtemLozyuk").build();

		when(postService.getAll("ArtemLozyuk", 2)).thenReturn(Arrays.asList(firstPost, secondPost));
		when(postService.getPost(1)).thenReturn(firstPost);

		ReflectionTestUtils.setField(postController, "postService", postService);

		mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
	}

	@Test
	public void getPostListViewTest() throws Exception
	{
		mockMvc.perform(get("/post/all/ArtemLozyuk/2"))
		       .andExpect(view().name("messageList"))
		       .andExpect(model().attribute("messageList", hasSize(2)))
		       .andExpect(model().attribute("messageList", hasItem(allOf(
						       hasProperty("id", is(1)),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some firstPost text"))
		       ))))
		       .andExpect(model().attribute("messageList", hasItem(allOf(
						       hasProperty("id", is(2)),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some secondPost text"))
		       ))));
	}

	@Test
	public void getPostViewTest() throws Exception
	{
		mockMvc.perform(get("/post/view/1"))
		       .andExpect(view().name("message"))
		       .andExpect(model().attribute("message", hasProperty("id", is(1))))
		       .andExpect(model().attribute("message", hasProperty("username", is("ArtemLozyuk"))))
		       .andExpect(model().attribute("message", hasProperty("text", is("Some firstPost text"))));
	}

	@Test
	public void getPostTest() throws Exception
	{
		mockMvc.perform(get("/post/1"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id", is(1)))
		       .andExpect(jsonPath("$.text", is("Some firstPost text")))
		       .andExpect(jsonPath("$.username", is("ArtemLozyuk")));
	}

	@Test
	public void deletePostTest() throws Exception
	{
		mockMvc.perform(post("/post/delete/1")).andExpect(status().isOk());
	}
}
