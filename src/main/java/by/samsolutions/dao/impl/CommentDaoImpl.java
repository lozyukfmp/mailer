package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.entity.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment, Integer>
				implements CommentDao
{

	public CommentDaoImpl()
	{
		super(Comment.class);
	}

	@Override
	public List<Comment> findAllByPostId(final Integer postId, final Integer commentCount)
	{
		return entityManager.createNamedQuery("Comment.findAllByPostId", Comment.class)
		                    .setParameter("id", postId)
		                    .setFirstResult(0)
		                    .setMaxResults(commentCount)
		                    .getResultList();
	}
}
