package by.samsolutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import by.samsolutions.service.UserProfileService;

@Controller
public class MainController
{
	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(value = {"/", "/welcome"})
	public String welcome()
	{
		return "welcome";
	}

	@RequestMapping(value = "/admin**")
	public String admin()
	{
		return "admin";
	}

	@RequestMapping(value = "/user**")
	public String user()
	{
		return "user_view";
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied()
	{
		return "accessDenied";
	}

	@RequestMapping(value = "/userHeader")
	public String getHeader()
	{
		return "user_header";
	}
}
