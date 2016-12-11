package by.samsolutions.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import by.samsolutions.controller.exception.ControllerException;
import by.samsolutions.controller.util.FileUtil;
import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.UserProfileConverter;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfileEntity;
import by.samsolutions.service.UserProfileService;
import by.samsolutions.service.exception.ServiceException;

@Controller
@RequestMapping("/user")
public class UserProfileController
{

	@Autowired
	HttpServletRequest request;

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping("/profile")
	public ModelAndView getUserProfileView() throws ControllerException
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			UserProfileDto userProfileDto = userProfileService.find(auth.getName());

			return new ModelAndView("profile_view", "userProfile", userProfileDto);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}

	}

	@GetMapping("/profile/info")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> getUserProfile() throws ControllerException
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			UserProfileDto userProfileDto = userProfileService.find(auth.getName());

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);

		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@PostMapping("/profile")
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile") @Valid final UserProfileDto userProfileDto,
	                                    final BindingResult userProfileBindingResult) throws ControllerException
	{
		try
		{
			if (!userProfileBindingResult.hasErrors())
			{
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				userProfileDto.setUsername(auth.getName());
				userProfileService.update(userProfileDto);

				return new ModelAndView("profile_view", "successProfileChange", "You've been changed profile successfully.");
			}

			return new ModelAndView("profile_view", "userProfile", userProfileDto);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}

	}

	@PostMapping(value = "/profile/photo/upload")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> loadUserPhoto(@RequestParam("userImage") MultipartFile file) throws ControllerException
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String photoUrl = fileUtil.saveImageToDisk(request, file, null);

			UserProfileDto userProfileDto = userProfileService.uploadUserPhoto(auth.getName(), photoUrl);

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
		}
		catch (ServiceException | IOException e)
		{
			throw new ControllerException(e);
		}
	}

	@PostMapping(value = "/profile/photo/delete")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> deleteUserPhoto() throws ControllerException
	{
		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			UserProfileDto userProfileDto = userProfileService.uploadUserPhoto(auth.getName(), null);

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);

		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}
}
