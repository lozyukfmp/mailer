package by.samsolutions.controller;

import java.util.List;

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

import by.samsolutions.entity.Comment;
import by.samsolutions.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController
{
	@Autowired
	private CommentService commentService;

	@GetMapping("/all/{postId}")
	public ModelAndView getCommentListByPostId(@PathVariable Integer postId)
	{
		List<Comment> commentList = commentService.getCommentListByPostId(postId);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("commentList", commentList);
		modelAndView.setViewName("commentList");

		return modelAndView;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable Integer id)
	{
		return new ResponseEntity<>(commentService.getComment(id),
		                            HttpStatus.OK);
	}

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<Comment>
	createComment(@RequestBody Comment comment)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comment.setUsername(auth.getName());

		return new ResponseEntity<>(commentService.createComment(comment),
		                            HttpStatus.OK);
	}

	@PostMapping("/update")
	public @ResponseBody ResponseEntity<Comment>
	updateComment(@RequestBody Comment comment)
	{
		return new ResponseEntity<>(commentService.updateComment(comment),
		                            HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<Comment> deleteComment(@PathVariable Integer id)
	{
		commentService.deleteComment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
