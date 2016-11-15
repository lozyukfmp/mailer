package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.entity.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment, Integer>
				implements CommentDao
{
	@Override
	public List<Comment> all() {

		return entityManager.createNamedQuery("Comment.findAll", Comment.class)
		                    .getResultList();
	}

	@Override
	public List<Comment> findAllByUsername(final String username)
	{
		return entityManager.createNamedQuery("Comment.findAllByUsername", Comment.class)
		                    .setParameter("username", username)
		                    .getResultList();
	}

	@Override
	public List<Comment> findAllByPostId(final Integer postId)
	{
		return entityManager.createNamedQuery("Comment.findAllByPostId", Comment.class)
		                    .setParameter("id", postId)
		                    .getResultList();
	}
}
