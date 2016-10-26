package by.samsolutions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/welcome"})
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

    @RequestMapping(value = "/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

}
