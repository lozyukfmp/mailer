package by.samsolutions.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
	private static final Logger logger = LogManager.getLogger(MainController.class);

	@RequestMapping(value = {"/", "/welcome"})
	public String welcome()
	{
		logger.trace("GETTING WELCOME PAGE");
		return "welcome";
	}

	@RequestMapping(value = "/admin**")
	public String admin()
	{
		logger.trace("GETTING ADMIN PAGE");
		return "admin";
	}

	@RequestMapping(value = "/user")
	public String user()
	{
		logger.trace("GETTING USER PAGE");
		return "user_view";
	}

	@RequestMapping(value = "/accessDenied")
	public String accessDenied()
	{
		logger.trace("GETTING ACCESS DENIED PAGE");
		return "accessDenied";
	}
}
