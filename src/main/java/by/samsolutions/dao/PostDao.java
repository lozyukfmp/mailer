package by.samsolutions.dao;

import java.util.List;

import by.samsolutions.entity.Post;

public interface PostDao extends GenericDao<Post, Integer>
{
	List<Post> all(Integer messageCount);
	Post findWithComments(Integer postId);
}
