package by.samsolutions.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.PostDao;
import by.samsolutions.entity.Comment;
import by.samsolutions.entity.Post;
import by.samsolutions.service.PostService;

@Service
public class PostServiceImpl implements PostService
{
	@Autowired
	private PostDao postDao;

	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional
	public Post createPost(final Post post)
	{
		post.setDate(new Date());

		return postDao.create(post);
	}

	@Override
	@Transactional
	public Post updatePost(final Post post)
	{
		return postDao.update(post);
	}

	@Override
	@Transactional
	public void deletePost(final Integer postId)
	{
		postDao.delete(postId);
	}

	@Override
	@Transactional(readOnly = true)
	public Post getPost(final Integer postId)
	{
		Post post = postDao.find(postId);

		if (post != null)
		{
			Collection<Comment> comments = commentDao.findAllByPostId(postId, 2);
			post.setComments(comments);
		}

		return post;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Post> getAll(Integer messageCount)
	{
		return postDao.all(messageCount);
	}
}
