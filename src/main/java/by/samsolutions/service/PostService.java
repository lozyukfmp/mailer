package by.samsolutions.service;

import java.util.List;

import by.samsolutions.entity.Post;

public interface PostService
{
	Post createPost(Post post);

	Post updatePost(Post post);

	void deletePost(Integer postId);

	Post getPost(Integer postId);

	List<Post> getAll(String username, Integer messageCount);

}
