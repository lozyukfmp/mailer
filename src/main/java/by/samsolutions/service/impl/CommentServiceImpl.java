package by.samsolutions.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public List<Comment> getCommentListByPostId(final Integer postId, final Integer commentIndex)
	{
		return commentDao.findAllByPostId(postId, commentIndex);
	}

	@Override
	@Transactional
	public Comment createComment(final Comment comment)
	{
		comment.setDate(new Date());

		return commentDao.create(comment);
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
