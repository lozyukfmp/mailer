package by.samsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/welcome" })
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/admin**")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/user**")
    public String user() {
        return "user";
    }

    @RequestMapping(value = "/loginPage")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView modelAndView = new ModelAndView();

        if (error != null) {
            modelAndView.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            modelAndView.addObject("logout", "You've been logged out successfully.");
        }

        modelAndView.setViewName("loginPage");

        return modelAndView;
    }
}
