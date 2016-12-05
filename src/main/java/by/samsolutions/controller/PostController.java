package by.samsolutions.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import by.samsolutions.controller.util.FileUtil;
import by.samsolutions.entity.Post;
import by.samsolutions.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController
{

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private PostService postService;

	@GetMapping("/all/{username}/{messageCount}")
	public ModelAndView getPostList(@PathVariable String username, @PathVariable Integer messageCount)
	{

		Collection<Post> messageList = postService.getAll(username, messageCount);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("messageList", messageList);
		modelAndView.setViewName("messageList");

		return modelAndView;
	}

	@GetMapping("/{id}")
	public Post getPost(@PathVariable Integer id)
	{
		return postService.getPost(id);
	}

	@GetMapping("/view/{id}")
	public ModelAndView getPostView(@PathVariable Integer id)
	{
		Post post = postService.getPost(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message", post);
		modelAndView.setViewName("message");

		return modelAndView;
	}

	@PostMapping("/create")
	public
	@ResponseBody
	ResponseEntity<Post> createPost(@RequestParam(value = "postImage", required = false) MultipartFile file,
	                                @RequestParam("postMessage") String postMessage) throws IOException
	{
		Post post = new ObjectMapper().readValue(postMessage, Post.class);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		post.setUsername(auth.getName());
		post.setImageUrl(FileUtil.saveImageToDisk(request, file, post.getImageUrl()));
		post = postService.createPost(post);

		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@PostMapping("/update")
	public
	@ResponseBody
	ResponseEntity<Post> updatePost(@RequestParam(value = "postImage", required = false) MultipartFile file,
	                                @RequestParam("postMessage") String postMessage) throws IOException
	{
		Post post = new ObjectMapper().readValue(postMessage, Post.class);
		post.setImageUrl(FileUtil.saveImageToDisk(request, file, post.getImageUrl()));
		post = postService.updatePost(post);

		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public
	@ResponseBody
	ResponseEntity<Post> deletePost(@PathVariable Integer id)
	{
		postService.deletePost(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
