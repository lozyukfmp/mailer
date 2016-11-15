package by.samsolutions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.entity.Comment;
import by.samsolutions.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService
{
	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional(readOnly = true)
	public Comment getComment(final Integer commentId)
	{
		return commentDao.find(commentId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentListByUsername(final String username)
	{
		return commentDao.findAllByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentListByPostId(final Integer postId)
	{
		return commentDao.findAllByPostId(postId);
	}

	@Override
	@Transactional
	public Comment updateComment(final Comment comment)
	{
		return commentDao.update(comment);
	}

	@Override
	@Transactional
	public void deleteComment(final Integer commentId)
	{
		commentDao.delete(commentId);
	}
}
