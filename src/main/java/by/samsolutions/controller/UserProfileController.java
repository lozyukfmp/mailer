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

import by.samsolutions.controller.util.FileUtil;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.service.UserProfileService;

@Controller
@RequestMapping("/user")
public class UserProfileController
{

	@Autowired
	HttpServletRequest request;

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping("/profile")
	public ModelAndView getUserProfileView()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserProfile userProfile = userProfileService.getUserProfile(auth.getName());

		return new ModelAndView("profile_view", "userProfile", userProfile);
	}

	@GetMapping("/profile/info")
	public
	@ResponseBody
	ResponseEntity<UserProfile> getUserProfile()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return new ResponseEntity<>(userProfileService.getUserProfile(auth.getName()), HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile") @Valid final UserProfileDto userProfileDto,
	                                    final BindingResult userProfileBindingResult)
	{

		if (!userProfileBindingResult.hasErrors())
		{
			userProfileService.updateUserProfile(userProfileDto);

			return new ModelAndView("profile_view", "successProfileChange", "You've been changed profile successfully.");
		}

		return new ModelAndView("profile_view", "userProfile", userProfileDto);

	}

	@PostMapping(value = "/profile/photo/upload")
	public
	@ResponseBody
	ResponseEntity<UserProfile> loadUserPhoto(@RequestParam("userImage") MultipartFile file) throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String photoUrl = FileUtil.saveImageToDisk(request, file, null);

		UserProfile userProfile = userProfileService.uploadUserPhoto(auth.getName(), photoUrl);

		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	@PostMapping(value = "/profile/photo/delete")
	public
	@ResponseBody
	ResponseEntity<UserProfile> deleteUserPhoto() throws IOException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UserProfile userProfile = userProfileService.uploadUserPhoto(auth.getName(), null);

		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}
}
