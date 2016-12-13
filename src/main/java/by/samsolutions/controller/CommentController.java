package by.samsolutions.controller;

import java.util.Collection;

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
	@Autowired
	private CommentService commentService;

	@GetMapping("/all/{postId}/{commentIndex}")
	public ModelAndView getCommentListByPostId(@PathVariable final Integer postId, @PathVariable final Integer commentIndex)
					throws ControllerException
	{
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
			throw new ControllerException(e);
		}

	}

	@GetMapping("/{id}")
	public
	@ResponseBody
	ResponseEntity<CommentDto> getComment(@PathVariable final Integer id) throws ControllerException
	{
		try
		{
			CommentDto commentDto = commentService.find(id);

			return new ResponseEntity<>(commentDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
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

		try
		{
			CommentDto resultDto = commentService.create(comment);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}

	}

	@PostMapping("/update")
	public
	@ResponseBody
	ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto comment) throws ControllerException
	{
		try
		{
			CommentDto resultDto = commentService.update(comment);

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}
	}

	@PostMapping("/delete/{id}")
	public
	@ResponseBody
	ResponseEntity<CommentDto> deleteComment(@PathVariable Integer id) throws ControllerException
	{
		try
		{
			commentService.delete(id);
			return new ResponseEntity(HttpStatus.OK);
		}
		catch (ServiceException e)
		{
			throw new ControllerException(e);
		}

	}
}
