package by.samsolutions.controller;

import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class UserController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/loginPage")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

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
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/loginPage?logout";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("registration", "user", new UserDto());
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("user") @Valid UserDto accountDto,
                                     BindingResult result, Model model) {
        User registered = new User();
        if (!result.hasErrors()) {
            registered = userService.createUserAccount(accountDto);
        }

        if (registered == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("registration");
            modelAndView.addObject("usernameExist","User with the same username already exists");

            return modelAndView;
        }

        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("loginPage", "successRegistration",
                    "You've been created account successfully.");
        }

    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView getUserProfile() {

        return new ModelAndView("profile", "userProfile", new UserProfileDto());
    }
}
