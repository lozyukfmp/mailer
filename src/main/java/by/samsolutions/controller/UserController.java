package by.samsolutions.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
@SessionAttributes({"user", "userProfile"})
public class UserController
{

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private PostService postService;


	@GetMapping(value = "/login-page")
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

	@GetMapping("/user")
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

			if (userProfileDto == null)
			{
				modelAndView.setViewName("user_not_found");
				modelAndView.addObject("username", username);

				return modelAndView;
			}

			Collection<PostDto> postDtoCollection =postService.getAll(username, 2);

			modelAndView.setViewName("user_view");
			modelAndView.addObject("profile", userProfileDto);
			modelAndView.addObject("messageList", postDtoCollection);

			return modelAndView;

		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(final HttpServletRequest request, final HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login-page?logout";
	}

	@RequestMapping(value = "/registration-page", method = RequestMethod.GET)
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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Valid final UserDto userDto, final BindingResult userBindingResult,
	                           @ModelAttribute("userProfile") @Valid final UserProfileDto userProfileDto,
	                           final BindingResult userProfileBindingResult, final Model model, final HttpSession session)
					throws ControllerException
	{
		try
		{
			UserDto registered = new UserDto();

			if (!userBindingResult.hasErrors() && !userProfileBindingResult.hasErrors())
			{
				userDto.setUserProfileDto(userProfileDto);

				registered = userService.create(userDto);
			}

			if (registered == null)
			{
				model.addAttribute("usernameExist", "UserEntity with the same username already exists");
				session.setAttribute("model", model);

				return "redirect:/registration-page";
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
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

}