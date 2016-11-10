package by.samsolutions.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.service.UserProfileService;

@Controller
@RequestMapping("/user")
public class UserProfileController
{
	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView getUserProfile() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserProfile userProfile = userProfileService.getUserProfile(auth.getName());

		return new ModelAndView("profile", "userProfile", userProfile);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile")
	                                    @Valid final UserProfileDto userProfileDto,
	                                    final BindingResult userProfileBindingResult)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		userProfileDto.setUsername(auth.getName());

		if (!userProfileBindingResult.hasErrors()) {
			userProfileService.updateUserProfile(userProfileDto);

			return new ModelAndView("profile", "successProfileChange",
			                        "You've been changed profile successfully.");
		}

		return new ModelAndView("profile", "userProfile", userProfileDto);

	}

	@RequestMapping(value = "/profile/photo", method = RequestMethod.GET)
	public void loadUserPhoto(@RequestParam("input41") MultipartFile file) {

	}

}
