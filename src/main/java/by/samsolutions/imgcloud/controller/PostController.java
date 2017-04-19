package by.samsolutions.imgcloud.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.dropbox.core.DbxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.samsolutions.imgcloud.controller.exception.ControllerException;
import by.samsolutions.imgcloud.controller.exception.TooLargeFileException;
import by.samsolutions.imgcloud.controller.util.FileUtil;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.service.PostService;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@RestController
@RequestMapping("post")
public class PostController
{

	private static final Logger logger = LogManager.getLogger(PostController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private PostService postService;

	@GetMapping("/all/{username}/{messageCount}")
	public ModelAndView getPostList(@PathVariable String username, @PathVariable Integer messageCount) throws ControllerException
	{
		logger.trace("GETTING POSTS(COUNT = " + messageCount + ") WITH USERNAME = " + username);
		try
		{
			Collection<PostDto> postDtoCollection = postService.getAll(username, messageCount);

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("messageList", postDtoCollection);
			modelAndView.setViewName("messageList");

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer id) throws ControllerException
	{
		logger.trace("GETTING POST BY ID = " + id);
		try
		{
			PostDto postDto = postService.find(id);

			return new ResponseEntity<>(postDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

	@GetMapping("/view/{id}")
	public ModelAndView getPostView(@PathVariable Integer id) throws ControllerException
	{
		logger.trace("GETTING POST VIEW BY ID = " + id);
		try
		{
			PostDto postDto = postService.find(id);

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message", postDto);
			modelAndView.addObject("commentList", postDto.getComments());

			modelAndView.setViewName("message");

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

	@PostMapping("/create")
	public
	@ResponseBody
	ResponseEntity createPost(@RequestParam(value = "postImage", required = false) MultipartFile file,
	                          @RequestParam("postMessage") String postMessage, Locale locale) throws ControllerException
	{
		logger.trace("CREATING POST " + postMessage);
		try
		{
			PostDto post = new ObjectMapper().readValue(postMessage, PostDto.class);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			post.setUsername(auth.getName());
			post.setImageUrl(fileUtil.saveImageToDisk(file, post.getImageUrl()));

			PostDto resultDto = postService.create(post);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (TooLargeFileException e)
		{
			logger.error(e.getMessage(), e);
			Map<String, String> response = new HashMap<>();
			response.put("error", messageSource.getMessage("message.file.long", null, locale));
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
		catch (ServiceException | IOException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		} catch (DbxException e) {
            throw new ControllerException(e);
        }

    }

	@PostMapping("/update")
	public
	@ResponseBody
	ResponseEntity updatePost(@RequestParam(value = "postImage", required = false) MultipartFile file,
	                          @RequestParam("postMessage") String postMessage, Locale locale) throws ControllerException
	{
		logger.trace("UPDATING POST " + postMessage);
		try
		{
			PostDto post = new ObjectMapper().readValue(postMessage, PostDto.class);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			post.setUsername(auth.getName());
			post.setImageUrl(fileUtil.saveImageToDisk(file, post.getImageUrl()));
			PostDto resultDto = postService.update(post);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (TooLargeFileException e)
		{
			logger.error(e.getMessage(), e);
			Map<String, String> response = new HashMap<>();
			response.put("error", messageSource.getMessage("message.file.long", null, locale));
			return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
		}
		catch (ServiceException | IOException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		} catch (DbxException e) {
            throw new ControllerException(e);
        }

    }

	@PostMapping("/delete/{id}")
	public
	@ResponseBody
	ResponseEntity<PostDto> deletePost(@PathVariable Integer id) throws ControllerException
	{
		logger.trace("DELETING POST WITH ID = " + id);
		try
		{
			postService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e.getMessage(), e);
			throw new ControllerException(e);
		}
	}
}
