package by.samsolutions.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.samsolutions.controller.exception.ControllerException;
import by.samsolutions.dto.PostDto;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.service.PostService;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.UserService;
import by.samsolutions.service.exception.ServiceException;
import by.samsolutions.service.exception.UserAlreadyExistsException;
import by.samsolutions.service.exception.UserNotFoundException;

@Controller
@SessionAttributes({"user", "userProfile"})
public class UserController
{

	@Autowired
	private MessageSource messageSource;

	@Value("${validation.text}")
	private String textRegexp;

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private PostService postService;

	@GetMapping(value = "login-page")
	public ModelAndView login(@RequestParam(value = "error", required = false) final String error,
	                          @RequestParam(value = "logout", required = false) final String logout,
	                          @RequestParam(value = "success", required = false) final String success)
	{

		ModelAndView modelAndView = new ModelAndView();

		if (error != null)
		{
			modelAndView.addObject("error", "Invalid username or password!");
		}

		if (logout != null)
		{
			modelAndView.addObject("logout", "You've been logged out successfully.");
		}

		if (success != null)
		{
			modelAndView.addObject("success", "You've been created account successfully.");
		}

		modelAndView.setViewName("loginPage");

		return modelAndView;
	}

	@GetMapping("user/{username}")
	public ModelAndView getUser(@PathVariable String username) throws ControllerException
	{
		ModelAndView modelAndView = new ModelAndView();

		try
		{
			List<UserDto> userDtoCollection = Arrays.asList(userService.getWithProfileByUsername(username));
			modelAndView.setViewName("userList");
			modelAndView.addObject("userList", userDtoCollection);

			return modelAndView;
		}
		catch (UserNotFoundException e)
		{
			modelAndView.setViewName("user_not_found");
			modelAndView.addObject("username", username);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@GetMapping("/user/all/{userCount}")
	public ModelAndView getUserList(@PathVariable Integer userCount) throws ControllerException
	{
		try
		{
			Collection<UserDto> userDtoCollection = userService.getAll(userCount);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("userList");
			modelAndView.addObject("userList", userDtoCollection);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}
	@GetMapping("admin")
	public ModelAndView getAdminPage() throws ControllerException
	{
		try
		{
			Collection<UserDto> userDtoCollection = userService.getAll(2);

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("admin");
			modelAndView.addObject("userList", userDtoCollection);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@PostMapping("/enable/{username}/{enabled}")
	public @ResponseBody ResponseEntity setUserEnabled(@PathVariable String username, @PathVariable Boolean enabled) throws ControllerException{
		try
		{
			userService.setUserEnabled(username, enabled);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@GetMapping("user")
	public ModelAndView getUserPage(@RequestParam(required = false) String username) throws ControllerException
	{

		if (username == null)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			username = auth.getName();
		}

		ModelAndView modelAndView = new ModelAndView();

		try
		{
			UserProfileDto userProfileDto = userProfileService.find(username);
			Collection<PostDto> postDtoCollection = postService.getAll(username, 2);

			modelAndView.setViewName("user_view");
			modelAndView.addObject("profile", userProfileDto);
			modelAndView.addObject("messageList", postDtoCollection);

			return modelAndView;

		}
		catch (UserNotFoundException e)
		{
			modelAndView.setViewName("user_not_found");
			modelAndView.addObject("username", username);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@GetMapping("validation")
	public
	@ResponseBody
	ResponseEntity getRegexpMap(Locale locale)
	{

		String imageMessage = messageSource.getMessage("message.image.NotEmpty", null, locale);
		String postMessage = messageSource.getMessage("message.post.NotEmpty", null, locale);
		String longMessage = messageSource.getMessage("message.file.long", null, locale);
		String emptyUsername = messageSource.getMessage("message.search.empty", null, locale);

		Map<String, Map<String, String>> validationInformation = new HashMap<>();
		Map<String, String> textMap = new HashMap<>();
		textMap.put("message", postMessage);
		textMap.put("regexp", textRegexp);
		Map<String, String> imageMap = new HashMap<>();
		imageMap.put("message", imageMessage);
		imageMap.put("long", longMessage);
		Map<String, String> searchMap = new HashMap<>();
		searchMap.put("message", emptyUsername);

		validationInformation.put("text", textMap);
		validationInformation.put("image", imageMap);
		validationInformation.put("search", searchMap);

		return new ResponseEntity(validationInformation, HttpStatus.OK);
	}

	@GetMapping("logout")
	public String logout(final HttpServletRequest request, final HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login-page?logout";
	}

	@GetMapping("registration-page")
	public String showRegistrationForm(Model model, HttpSession session)
	{

		Model sessionModel = (Model) session.getAttribute("model");
		if (sessionModel != null)
		{
			model.addAllAttributes(sessionModel.asMap());
			session.removeAttribute("model");
		}
		else
		{
			model.addAttribute("user", new UserDto());
			model.addAttribute("userProfile", new UserProfileDto());
		}

		return "registration";
	}

	@PostMapping("register")
	public String registerUser(@ModelAttribute("user") @Valid final UserDto userDto, final BindingResult userBindingResult,
	                           @ModelAttribute("userProfile") @Valid final UserProfileDto userProfileDto,
	                           final BindingResult userProfileBindingResult, final Model model, final HttpSession session)
					throws ControllerException
	{
		try
		{
			if (!userBindingResult.hasErrors() && !userProfileBindingResult.hasErrors())
			{
				userDto.setProfile(userProfileDto);
				userService.create(userDto);
			}

			if (userBindingResult.hasErrors() || userProfileBindingResult.hasErrors())
			{
				session.setAttribute("model", model);

				return "redirect:/registration-page";
			}
			else
			{
				return "redirect:/login-page?success";
			}

		}
		catch (UserAlreadyExistsException e)
		{
			model.addAttribute("usernameExist", "User with the same username already exists");
			session.setAttribute("model", model);

			return "redirect:/registration-page";
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

}