package by.samsolutions.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	HttpServletRequest request;

	@Autowired
	private UserProfileService userProfileService;

	@GetMapping("/profile")
	public ModelAndView getUserProfileView() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserProfile userProfile = userProfileService.getUserProfile(auth.getName());

		return new ModelAndView("profile", "userProfile", userProfile);
	}

	@GetMapping("/profile/info/{username}")
	public @ResponseBody ResponseEntity<UserProfile> getUserProfile(@PathVariable String username)
	{
		return new ResponseEntity<>(userProfileService.getUserProfile(username),
		                                       HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ModelAndView saveUserProfile(@ModelAttribute("userProfile")
	                                    @Valid final UserProfileDto userProfileDto,
	                                    final BindingResult userProfileBindingResult)
	{

		if (!userProfileBindingResult.hasErrors()) {
			userProfileService.updateUserProfile(userProfileDto);

			return new ModelAndView("profile", "successProfileChange",
			                        "You've been changed profile successfully.");
		}

		return new ModelAndView("profile", "userProfile", userProfileDto);

	}

	@PostMapping(value = "/profile/photo/upload")
	public @ResponseBody ResponseEntity<UserProfile>
	loadUserPhoto(@RequestParam("userImage") MultipartFile file) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String photoUrl = saveImageToDisk(file);

		UserProfile userProfile = userProfileService.uploadUserPhoto(auth.getName(), photoUrl);

		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	@PostMapping(value = "/profile/photo/delete")
	public @ResponseBody ResponseEntity<UserProfile>
	deleteUserPhoto() throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UserProfile userProfile = userProfileService.uploadUserPhoto(auth.getName(), null);

		return new ResponseEntity<>(userProfile, HttpStatus.OK);
	}

	private String saveImageToDisk(MultipartFile file) throws IOException
	{
		String imageUrl = null;

		if (file != null && !file.isEmpty())
		{

			String uploadsDir = "/static/core/pictures/";
			String pathToUploads = request.getServletContext().getRealPath(uploadsDir);

			if (!new File(pathToUploads).exists())
			{
				new File(pathToUploads).mkdir();
			}

			String orgName = file.getOriginalFilename();
			String filePath = pathToUploads + orgName;
			imageUrl = request.getContextPath() + uploadsDir + orgName;
			File dest = new File(filePath);
			file.transferTo(dest);

		}

		return imageUrl;
	}

}
