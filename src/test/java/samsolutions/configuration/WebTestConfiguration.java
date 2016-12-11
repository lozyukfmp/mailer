package samsolutions.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import by.samsolutions.configuration.root.ValidatorConfiguration;
import by.samsolutions.controller.CommentController;
import by.samsolutions.controller.PostController;
import by.samsolutions.controller.UserController;
import by.samsolutions.controller.UserProfileController;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.PostService;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.UserService;

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
