package by.samsolutions.imgcloud.controller;

import by.samsolutions.imgcloud.controller.exception.ControllerException;
import by.samsolutions.imgcloud.controller.util.FileUtil;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.service.UserProfileService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;

@Controller
@RequestMapping("profile")
public class UserProfileController
{

	private static final Logger logger = LogManager.getLogger(UserProfileController.class);

	@Autowired
	HttpServletRequest request;

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping
	public ModelAndView getUserProfileView() throws ControllerException
	{
		logger.trace("GETTING USER PROFILE VIEW");

		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			UserProfileDto userProfileDto = userProfileService.find(auth.getName());

			return new ModelAndView("profile_view", "userProfile", userProfileDto);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}

	}

	@GetMapping("/info")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> getUserProfile() throws ControllerException
	{
		logger.trace("GETTING USER PROFILE JSON");

		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			UserProfileDto userProfileDto = userProfileService.find(auth.getName());

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);

		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

	@PostMapping
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile") @Valid final UserProfileDto userProfileDto,
	                                    final BindingResult userProfileBindingResult) throws ControllerException
	{
		logger.trace("SAVING USER PROFILE :" + userProfileDto);

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
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}

	}

	@PostMapping("/photo/upload")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> loadUserPhoto(@RequestParam("userImage") MultipartFile file) throws ControllerException
	{
		logger.trace("UPLOADING PHOTO " + file.getOriginalFilename());

		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(auth.getName());
            userProfileDto.setImageUrl(fileUtil.saveImageToDisk(file, userProfileDto.getImageUrl()));

			userProfileDto = userProfileService.uploadUserPhoto(userProfileDto);

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
		}
		catch (ServiceException | IOException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
		/*catch (DbxException e) {
            throw new ControllerException(e);
        }*/
    }

	@PostMapping("/photo/delete")
	public
	@ResponseBody
	ResponseEntity<UserProfileDto> deleteUserPhoto() throws ControllerException
	{
		logger.trace("DELETING PHOTO");

		try
		{
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserProfileDto userProfileDto = new UserProfileDto();
            userProfileDto.setUsername(auth.getName());
            userProfileDto.setImageUrl(null);
            userProfileDto = userProfileService.uploadUserPhoto(userProfileDto);

			return new ResponseEntity<>(userProfileDto, HttpStatus.OK);

		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}
}
