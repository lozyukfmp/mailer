package by.samsolutions.dao;

import java.util.List;

import by.samsolutions.entity.Comment;

public interface CommentDao extends GenericDao<Comment, Integer>
{
	List<Comment> findAllByPostId(Integer postId);

	List<Comment> findAllByUsername(String username);
}
