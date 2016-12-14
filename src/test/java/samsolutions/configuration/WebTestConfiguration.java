package samsolutions.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import by.samsolutions.imgcloud.controller.CommentController;
import by.samsolutions.imgcloud.controller.PostController;
import by.samsolutions.imgcloud.controller.UserController;
import by.samsolutions.imgcloud.controller.UserProfileController;
import by.samsolutions.imgcloud.service.CommentService;
import by.samsolutions.imgcloud.service.PostService;
import by.samsolutions.imgcloud.service.UserProfileService;
import by.samsolutions.imgcloud.service.UserService;

@Configuration
@PropertySource(value = "classpath:/validation/regexp.properties")
public class WebTestConfiguration
{

	@Bean
	public UserController userController()
	{
		return new UserController();
	}

	@Bean
	public UserProfileController userProfileController()
	{
		return new UserProfileController();
	}

	@Bean
	public PostController postController()
	{
		return new PostController();
	}

	@Bean
	public CommentController commentController()
	{
		return new CommentController();
	}

	@Bean
	public UserService userService()
	{
		return Mockito.mock(UserService.class);
	}

	@Bean
	public UserProfileService userProfileService()
	{
		return Mockito.mock(UserProfileService.class);
	}

	@Bean
	public PostService postService()
	{
		return Mockito.mock(PostService.class);
	}

	@Bean
	public CommentService commentService()
	{
		return Mockito.mock(CommentService.class);
	}

}
