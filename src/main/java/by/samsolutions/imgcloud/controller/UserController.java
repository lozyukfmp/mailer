package by.samsolutions.imgcloud.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import by.samsolutions.imgcloud.controller.exception.ControllerException;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.service.PostService;
import by.samsolutions.imgcloud.service.UserProfileService;
import by.samsolutions.imgcloud.service.UserService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import by.samsolutions.imgcloud.service.exception.UserAlreadyExistsException;
import by.samsolutions.imgcloud.service.exception.UserNotFoundException;

@Controller
@SessionAttributes({"user", "userProfile"})
public class UserController
{
	private static final Logger logger = LogManager.getLogger(UserController.class);

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

	@GetMapping("login-page")
	public ModelAndView login(@RequestParam(value = "error", required = false) final String error,
	                          @RequestParam(value = "logout", required = false) final String logout,
	                          @RequestParam(value = "success", required = false) final String success)
	{

		logger.trace("GETTING LOGIN PAGE");
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
		logger.trace("GETTING USER WITH USERNAME = " + username);

		ModelAndView modelAndView = new ModelAndView();

		try
		{
			Collection<UserDto> userDtoCollection = userService.getWithProfileByUsername(username);
			modelAndView.setViewName("userList");
			modelAndView.addObject("userList", userDtoCollection);

			return modelAndView;
		}
		catch (UserNotFoundException e)
		{
			logger.info("USER WITH USERNAME " + username + " NOT FOUND", e);
			modelAndView.setViewName("user_not_found");
			modelAndView.addObject("username", username);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("/user/all/{userCount}")
	public ModelAndView getUserList(@PathVariable Integer userCount) throws ControllerException
	{
		logger.trace("GETTING USER LIST (COUNT = " + userCount + ")");
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
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("search")
	public ModelAndView getSearchPage() throws ControllerException
	{
		logger.trace("GETTING SEARCH PAGE");
		try
		{
			Collection<UserDto> userDtoCollection = userService.getAll(2);

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("search");
			modelAndView.addObject("userList", userDtoCollection);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("admin")
	public ModelAndView getAdminPage() throws ControllerException
	{
		logger.trace("GETTING ADMIN PAGE");
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
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@PostMapping("/enable/{username}/{enabled}")
	public
	@ResponseBody
	ResponseEntity setUserEnabled(@PathVariable String username, @PathVariable Boolean enabled) throws ControllerException
	{
		logger.trace("TRYING TO LOCK/UNLOCK USER : " + username);
		try
		{
			userService.setUserEnabled(username, enabled);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@PostMapping("/admin-role/{username}/{enabled}")
	public
	@ResponseBody
	ResponseEntity toggleAdminRole(@PathVariable String username, @PathVariable Boolean enabled) throws ControllerException
	{
		logger.trace("TRYING TO ADD/DELETE ADMIN ROLE : " + username);
		try
		{
			if (enabled)
			{
				userService.addAdminRole(username);
			}
			else
			{
				userService.deleteAdminRole(username);
			}

			return new ResponseEntity(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("user")
	public ModelAndView getUserPage(@RequestParam(required = false) String username) throws ControllerException
	{
		logger.trace("GETTING USER PAGE");

		if (username == null)
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			username = auth.getName();
		}

		ModelAndView modelAndView = new ModelAndView();

		try
		{

			UserProfileDto userProfileDto = userProfileService.findByUsername(username);
			Collection<PostDto> postDtoCollection = postService.getAll(username, 2);

			modelAndView.setViewName("user_view");
			modelAndView.addObject("profile", userProfileDto);
			modelAndView.addObject("messageList", postDtoCollection);

			return modelAndView;

		}
		catch (UserNotFoundException e)
		{
			logger.info("USER WITH USERNAME : " + username + " NOT FOUND", e);
			modelAndView.setViewName("user_not_found");
			modelAndView.addObject("username", username);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("validation")
	public
	@ResponseBody
	ResponseEntity getRegexpMap(Locale locale)
	{

		logger.trace("GETTING VALIDATION MAP");

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
		logger.trace("LOGGIN OUT");

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

		logger.trace("GETTING REGISTRATION PAGE");

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

		logger.trace("REGISTERING USER");

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
			logger.info("USER ALREADY EXISTS (USERNAME = " + userDto.getUsername() + "). ", e);
			model.addAttribute("usernameExist", "User with the same username already exists");
			session.setAttribute("model", model);

			return "redirect:/registration-page";
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

}