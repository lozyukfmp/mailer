package by.samsolutions.controller;

import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/loginPage")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) final String error,
            @RequestParam(value = "logout", required = false) final String logout) {

        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "You've been logged out successfully.");
        }

        modelAndView.setViewName("loginPage");

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(final HttpServletRequest request, final HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/loginPage?logout";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("user", new UserDto());
        modelAndView.addObject("userProfile", new UserProfileDto());
        modelAndView.setViewName("registration");

        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user")
                                         @Valid final UserDto userDto,
                                     final BindingResult userBindingResult,
                                     @ModelAttribute("userProfile")
                                         @Valid final UserProfileDto userProfileDto,
				                             final BindingResult userProfileBindingResult, final Model model) {
        User registered = new User();
        if (!userBindingResult.hasErrors() &&
				        !userProfileBindingResult.hasErrors()) {
            userDto.setUserProfileDto(userProfileDto);
            registered = userService.createUserAccount(userDto);
        }

        if (registered == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("registration");
            modelAndView.addObject("usernameExist","User with the same username already exists");

            return modelAndView;
        }

        if (userBindingResult.hasErrors() || userProfileBindingResult.hasErrors()) {
	          ModelAndView modelAndView = new ModelAndView();

	          modelAndView.addObject("user", userDto);
	          modelAndView.addObject("userProfile", userProfileDto);
	          modelAndView.setViewName("registration");
            return modelAndView;
        } else {
            return new ModelAndView("loginPage", "successRegistration",
                    "You've been created account successfully.");
        }

    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserProfile userProfile = userService.getUserProfileInfo(auth.getName());

        return new ModelAndView("profile", "userProfile", userProfile);
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public ModelAndView saveUserProfile(@ModelAttribute("userProfile")
                                            @Valid final UserProfileDto userProfileDto,
                                        final BindingResult userProfileBindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userProfileDto.setUsername(auth.getName());
        if (!userProfileBindingResult.hasErrors()) {
            userService.saveUserProfileInfo(userProfileDto);

            return new ModelAndView("profile", "successProfileChange",
                                    "You've been changed profile successfully.");
        }

        return new ModelAndView("profile", "userProfile", userProfileDto);

    }

    @RequestMapping(value = "/user/profile/photo", method = RequestMethod.GET,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void loadUserPhoto() {
        System.out.println("HERE!");
    }

    @RequestMapping(value = "/user/profile/photo", method = RequestMethod.POST)
    public @ResponseBody String saveUserPhoto(MultipartHttpServletRequest request) {
        return "sdf";
    }
}