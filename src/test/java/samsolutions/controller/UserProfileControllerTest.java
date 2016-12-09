package samsolutions.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import by.samsolutions.configuration.web.SpringWebConfig;
import by.samsolutions.controller.UserProfileController;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.exception.ServiceException;
import samsolutions.configuration.WebTestConfiguration;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebTestConfiguration.class, SpringWebConfig.class})
@WebAppConfiguration
public class UserProfileControllerTest
{

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileController userProfileController;

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

		when(userProfileService.find(userProfile.getUsername())).thenReturn(userProfile);
		when(userProfileService.update(Matchers.any())).thenReturn(userProfile);

		ReflectionTestUtils.setField(userProfileController, "userProfileService", userProfileService);

		mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
	}

	@Test
	@WithMockUser(username = "ArtemLozyuk")
	public void testGetUserProfile() throws Exception
	{
		mockMvc.perform(get("/user/profile"))
		       .andExpect(view().name("profile_view"))
		       .andExpect(model().attribute("userProfile", hasProperty("username", is("ArtemLozyuk"))))
		       .andExpect(model().attribute("userProfile", hasProperty("firstName", is("Artem"))))
		       .andExpect(model().attribute("userProfile", hasProperty("secondName", is("Lozyuk"))))
		       .andExpect(model().attribute("userProfile", hasProperty("thirdName", is("Nicolaevich"))))
		       .andExpect(model().attribute("userProfile", hasProperty("email", is("lozyuk-artem@mail.ru"))));
	}

	@Test
	public void testSaveUserProfile() throws Exception
	{
		mockMvc.perform(post("/user/profile").param("firstName", "Artem")
		                                     .param("secondName", "Lozyuk")
		                                     .param("thirdName", "Nicolaevich")
		                                     .param("username", "ArtemLozyuk")
		                                     .param("email", "lozyuk-artem@mail.ru"))
		       .andExpect(view().name("profile_view"))
		       .andExpect(model().hasNoErrors())
		       .andExpect(model().attribute("successProfileChange", "You've been changed profile successfully."))
		       .andExpect(model().attribute("userProfile", hasProperty("firstName", is("Artem"))))
		       .andExpect(model().attribute("userProfile", hasProperty("secondName", is("Lozyuk"))))
		       .andExpect(model().attribute("userProfile", hasProperty("thirdName", is("Nicolaevich"))))
		       .andExpect(model().attribute("userProfile", hasProperty("email", is("lozyuk-artem@mail.ru"))));
	}

	@Test
	public void testFailSaveUserProfile() throws Exception
	{
		mockMvc.perform(post("/user/profile").param("firstName", "Artem")
		                                     .param("secondName", "Lozyuk")
		                                     .param("thirdName", "Nicolaevich")
		                                     .param("username", "ArtemLozyuk")
		                                     .param("email", "lozyuk-mail.ru"))
		       .andExpect(view().name("profile_view"))
		       .andExpect(model().hasErrors())
		       .andExpect(model().attribute("userProfile", hasProperty("firstName", is("Artem"))))
		       .andExpect(model().attribute("userProfile", hasProperty("secondName", is("Lozyuk"))))
		       .andExpect(model().attribute("userProfile", hasProperty("thirdName", is("Nicolaevich"))))
		       .andExpect(model().attribute("userProfile", hasProperty("email", is("lozyuk-mail.ru"))));
	}

	@Test
	@WithMockUser(username="ArtemLozyuk")
	public void testGetUserProfileJson() throws Exception
	{
		mockMvc.perform(get("/user/profile/info"))
		       .andExpect(status().isOk())
		       .andExpect(jsonPath("$.firstName", is("Artem")))
		       .andExpect(jsonPath("$.secondName", is("Lozyuk")))
		       .andExpect(jsonPath("$.thirdName", is("Nicolaevich")))
		       .andExpect(jsonPath("$.email", is("lozyuk-artem@mail.ru")));

	}

}
