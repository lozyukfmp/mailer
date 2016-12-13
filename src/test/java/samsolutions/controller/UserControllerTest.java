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
import by.samsolutions.controller.UserController;
import by.samsolutions.dto.PostDto;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.PostService;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.UserService;
import by.samsolutions.service.exception.ServiceException;
import samsolutions.configuration.WebTestConfiguration;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfiguration.class, SpringWebConfig.class})
@WebAppConfiguration
public class UserControllerTest
{

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private PostService postService;

	@Autowired
	private UserController userController;

	private MockMvc mockMvc;

	@Before
	public void init() throws ServiceException
	{
		UserProfileDto userProfile = UserProfileDto.builder()
		                                              .username("ArtemLozyuk")
		                                              .firstName("Artem")
		                                              .secondName("Lozyuk")
		                                              .thirdName("Nicolaevich")
		                                              .email("lozyuk-artem@mail.ru")
		                                              .build();

		PostDto firstPost = PostDto.builder().id("1").text("Some firstPost text").username("ArtemLozyuk").build();
		PostDto secondPost = PostDto.builder().id("2").text("Some secondPost text").username("ArtemLozyuk").build();

		when(userProfileService.find(userProfile.getUsername())).thenReturn(userProfile);
		when(userService.create(any())).thenReturn(new UserDto());
		when(postService.getAll(userProfile.getUsername(), 2)).thenReturn(Arrays.asList(firstPost, secondPost));

		ReflectionTestUtils.setField(userController, "userService", userService);
		ReflectionTestUtils.setField(userController, "userProfileService", userProfileService);
		ReflectionTestUtils.setField(userController, "postService", postService);

		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	}

	@Test
	public void testGetLoginPage() throws Exception
	{
		mockMvc.perform(get("/login-page")).andExpect(view().name("loginPage"));
	}

	@Test
	public void testSuccessLogout() throws Exception
	{
		mockMvc.perform(get("/logout")).andExpect(redirectedUrl("/login-page?logout"));
	}

	@Test
	public void testGetUserPage() throws Exception
	{
		mockMvc.perform(get("/user").param("username", "ArtemLozyuk"))
		       .andExpect(view().name("user_view"))
		       .andExpect(model().attribute("profile", hasProperty("username", is("ArtemLozyuk"))))
		       .andExpect(model().attribute("profile", hasProperty("firstName", is("Artem"))))
		       .andExpect(model().attribute("profile", hasProperty("secondName", is("Lozyuk"))))
		       .andExpect(model().attribute("profile", hasProperty("thirdName", is("Nicolaevich"))))
		       .andExpect(model().attribute("profile", hasProperty("email", is("lozyuk-artem@mail.ru"))))
		       .andExpect(model().attribute("messageList", hasSize(2)))
		       .andExpect(model().attribute("messageList", hasItem(allOf(
						       hasProperty("id", is("1")),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some firstPost text"))
		       ))))
		       .andExpect(model().attribute("messageList", hasItem(allOf(
						       hasProperty("id", is("2")),
						       hasProperty("username", is("ArtemLozyuk")),
						       hasProperty("text", is("Some secondPost text"))
		       ))));
	}

	public void testRegistrationFailed() throws Exception
	{
		mockMvc.perform(post("/register").param("username", "Artem")
		                                 .param("password", "Artem2968235")
		                                 .param("confirmPassword", "Artem2968235")
		                                 .param("firstName", "Artem")
		                                 .param("secondName", "Lozyuk")
		                                 .param("thirdName", "Nicolaevich")
		                                 .param("email", "lozyuk-artem@mail.ru"))
		       .andExpect(model().hasErrors())
		       .andExpect(view().name("registration"));
	}

}
