package by.samsolutions.imgcloud.dao.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import by.samsolutions.imgcloud.dao.CommentDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.CommentEntity;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<CommentEntity, Integer> implements CommentDao
{
	private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);

	public CommentDaoImpl()
	{
		super(CommentEntity.class);
	}

	@Override
	public List<CommentEntity> findAllByPostId(final Integer postId, final Integer commentCount) throws DaoException
	{
		logger.trace("GETTING COMMENTS BY POST ID = " + postId);
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
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}
}
