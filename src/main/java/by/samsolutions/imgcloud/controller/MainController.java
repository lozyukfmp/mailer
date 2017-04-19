package by.samsolutions.imgcloud.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import by.samsolutions.imgcloud.controller.exception.ControllerException;
import com.dropbox.core.DbxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.samsolutions.imgcloud.controller.util.FileUtil;

@Controller
public class MainController
{
	@Autowired
	private FileUtil fileUtil;

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

	@GetMapping(value = "img/{username}/{imageName}.{imageType}")
	public void getImage(@PathVariable String username, @PathVariable String imageName, @PathVariable String imageType, HttpServletResponse response) throws IOException, DbxException
	{
		byte[] result = fileUtil.getImageFromDisk(username, imageName + "." + imageType);
		response.setContentLength(result.length);
		response.setStatus(HttpServletResponse.SC_OK);
		OutputStream os = response.getOutputStream();
		os.write(result);
	}
}
