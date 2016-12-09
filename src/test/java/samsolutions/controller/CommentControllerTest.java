package samsolutions.controller;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.samsolutions.configuration.web.SpringWebConfig;
import by.samsolutions.controller.CommentController;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.exception.ServiceException;
import samsolutions.configuration.WebTestConfiguration;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
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
public class CommentControllerTest
{

	@Autowired
	private CommentController commentController;

	@Autowired
	private CommentService commentService;

	private MockMvc mockMvc;

	@Before
	public void init() throws ServiceException
	{
		CommentDto firstComment = CommentDto.builder()
		                                    .id("1")
		                                    .postId("1")
		                                    .text("Some firstComment text")
		                                    .username("ArtemLozyuk")
		                                    .build();
		CommentDto secondComment = CommentDto.builder()
		                                     .id("2")
		                                     .postId("1")
		                                     .text("Some secondComment text")
		                                     .username("ArtemLozyuk")
		                                     .build();
		CommentDto createComment = CommentDto.builder()
		                                     .id("3")
		                                     .postId("1")
		                                     .text("Some createComment text")
		                                     .username("ArtemLozyuk")
		                                     .build();
		CommentDto updateComment = CommentDto.builder()
		                                     .id("4")
		                                     .postId("1")
		                                     .text("Some updateComment text")
		                                     .username("ArtemLozyuk")
		                                     .build();

		when(commentService.getCommentListByPostId(1, 2)).thenReturn(Arrays.asList(firstComment, secondComment));
		when(commentService.create(any())).thenReturn(createComment);
		when(commentService.update(any())).thenReturn(updateComment);
		when(commentService.find(1)).thenReturn(firstComment);
		ReflectionTestUtils.setField(commentController, "commentService", commentService);

		mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
	}

	@Test
	public void getCommentListViewTest() throws Exception
	{
		mockMvc.perform(get("/comment/all/1/2"))
		       .andExpect(view().name("commentList"))
		       .andExpect(model().attribute("commentList", hasSize(2)))
		       .andExpect(model().attribute("commentList", hasItem(allOf(
						       hasProperty("id", is("1")),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some firstComment text"))
		       ))))
		       .andExpect(model().attribute("commentList", hasItem(allOf(
						       hasProperty("id", is("2")),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some secondComment text"))
		       ))));
	}

	@Test
	public void getCommentTest() throws Exception
	{
		mockMvc.perform(get("/comment/1"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id", is("1")))
		       .andExpect(jsonPath("$.text", is("Some firstComment text")))
		       .andExpect(jsonPath("$.username", is("ArtemLozyuk")));
	}

	@Test
	@WithMockUser(username = "ArtemLozyuk")
	public void createCommentTest() throws Exception
	{
		mockMvc.perform(post("/comment/create").contentType(MediaType.APPLICATION_JSON_UTF8)
		                                       .content("{\"postId\":\"1\", \"text\":\"Some createComment text\"}"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id", is("3")))
		       .andExpect(jsonPath("$.text", is("Some createComment text")))
		       .andExpect(jsonPath("$.username", is("ArtemLozyuk")));

	}

	@Test
	public void updateCommentTest() throws Exception
	{
		mockMvc.perform(post("/comment/update").contentType(MediaType.APPLICATION_JSON_UTF8)
		                                       .content("{\"id\":\"4\", \"postId\":\"1\", \"text\":\"Some updateComment text\"}"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.id", is("4")))
		       .andExpect(jsonPath("$.text", is("Some updateComment text")))
		       .andExpect(jsonPath("$.username", is("ArtemLozyuk")));
	}

	@Test
	public void deleteCommentTest() throws Exception
	{
		mockMvc.perform(post("/comment/delete/1")).andExpect(status().isOk());
	}

}
