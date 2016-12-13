package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.CommentEntity;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<CommentEntity, Integer>
				implements CommentDao
{

	public CommentDaoImpl()
	{
		super(CommentEntity.class);
	}

	@Override
	public List<CommentEntity> findAllByPostId(final Integer postId, final Integer commentCount) throws DaoException
	{
		try
		{
			return entityManager.createNamedQuery("Comment.findAllByPostId", CommentEntity.class)
			                    .setParameter("id", postId)
			                    .setFirstResult(0)
			                    .setMaxResults(commentCount)
			                    .getResultList();
		}
		catch (Exception e)
		{
			throw new DaoException(e);
		}
	}
}
