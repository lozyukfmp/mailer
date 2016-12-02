package by.samsolutions.service;

import java.util.List;

import by.samsolutions.entity.Comment;

public interface CommentService
{
	Comment getComment(Integer commentId);

	Comment createComment(Comment comment);

	Comment updateComment(Comment comment);

	void deleteComment(Integer commentId);

	List<Comment> getCommentListByPostId(Integer postId, Integer commentIndex);
}
