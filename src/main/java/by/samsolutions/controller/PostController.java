package by.samsolutions.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.samsolutions.entity.Post;
import by.samsolutions.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController
{

	@Autowired
	HttpServletRequest request;

	@Autowired
	private PostService postService;

	@GetMapping("/all")
	public ModelAndView getPostList()
	{
		List<Post> messageList = postService.getAll();

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

	@PostMapping("/create")
	public @ResponseBody ResponseEntity<Post> createPost(@RequestParam(value = "postImage", required = false) MultipartFile file,
	                                                     @RequestParam("postMessage") String postMessage) throws IOException
	{
		Post post = new ObjectMapper().readValue(postMessage, Post.class);

		if (file != null && !file.isEmpty())
		{
				String uploadsDir = "/static/core/pictures/";
				String pathToUploads = request.getServletContext().getRealPath(uploadsDir);

				if (!new File(pathToUploads).exists())
				{
					new File(pathToUploads).mkdir();
				}

				String orgName = file.getOriginalFilename();
				String filePath = pathToUploads + orgName;
				post.setImageUrl(uploadsDir + orgName);
				File dest = new File(filePath);
				file.transferTo(dest);
		}

		post = postService.createPost(post);

		return new ResponseEntity<>(post, HttpStatus.OK);
	}

	@PostMapping("/update")
	public @ResponseBody ResponseEntity<Post> updatePost(@RequestBody Post post)
	{
		Post updatedPost = postService.updatePost(post);
		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}

	@PostMapping("/delete/{id}")
	public @ResponseBody ResponseEntity<Post> deletePost(@PathVariable Integer id)
	{
		postService.deletePost(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
