package by.samsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class MainController {

    @Autowired
    CommonsMultipartResolver multipartResolver;

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

    @RequestMapping(value = "/userHeader")
    public String getHeader() {
        return "user_header";
    }
}
