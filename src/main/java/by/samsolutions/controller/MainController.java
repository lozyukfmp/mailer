package by.samsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String getWelcomePage() {
        return "welcome";
    }

    @RequestMapping(value = "/admin")
    public String getAdminPage() {
        return "admin";
    }

    @RequestMapping(value = "/user")
    public String getUserPage() {
        return "user";
    }
}
