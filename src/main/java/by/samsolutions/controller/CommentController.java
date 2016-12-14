package by.samsolutions.controller;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import by.samsolutions.controller.exception.ControllerException;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.exception.ServiceException;

@RestController
@RequestMapping("comment")
public class CommentController
{
	private static final Logger logger = LogManager.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@GetMapping("/all/{postId}/{commentIndex}")
	public ModelAndView getCommentListByPostId(@PathVariable final Integer postId, @PathVariable final Integer commentIndex)
					throws ControllerException
	{
		logger.trace("GETTING COMMENTS(COUNT = " + commentIndex + ") BY POST_ID = " + postId);
		try
		{
			Collection<CommentDto> commentDtoCollection = commentService.getCommentListByPostId(postId, commentIndex);

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("commentList");
			modelAndView.addObject("commentList", commentDtoCollection);

			return modelAndView;
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}

	}

	@GetMapping("/{id}")
	public
	@ResponseBody
	ResponseEntity<CommentDto> getComment(@PathVariable final Integer id) throws ControllerException
	{
		logger.trace("GETTING COMMENT BY COMMENT_ID = " + id);
		try
		{
			CommentDto commentDto = commentService.find(id);

			return new ResponseEntity<>(commentDto, HttpStatus.OK);
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
	ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto comment) throws ControllerException
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comment.setUsername(auth.getName());

		logger.trace("CREATING COMMENT " + comment);
		try
		{
			CommentDto resultDto = commentService.create(comment);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}

	}

	@PostMapping("/update")
	public
	@ResponseBody
	ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto comment) throws ControllerException
	{
		logger.trace("UPDATING COMMENT " + comment);
		try
		{
			CommentDto resultDto = commentService.update(comment);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}
	}

	@PostMapping("/delete/{id}")
	public
	@ResponseBody
	ResponseEntity<CommentDto> deleteComment(@PathVariable Integer id) throws ControllerException
	{
		logger.trace("DELETING COMMENT WITH ID = " + id);
		try
		{
			commentService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			logger.error(e);
			throw new ControllerException(e);
		}

	}
}
