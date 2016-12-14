package by.samsolutions.imgcloud.dao.impl;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import by.samsolutions.imgcloud.dao.PostDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.PostEntity;

@Repository
public class PostDaoImpl extends GenericDaoImpl<PostEntity, Integer> implements PostDao
{
	private static final Logger logger = LogManager.getLogger(PostDaoImpl.class);

	public PostDaoImpl()
	{
		super(PostEntity.class);
	}

	@Override
	public List<PostEntity> all(final String username, final Integer messageCount) throws DaoException
	{
		List<PostEntity> postList = null;
		logger.trace("GETTING POSTS BY USERNAME = " + username);
		try
		{
			postList = entityManager.createNamedQuery("Post.findAllByUsername", PostEntity.class)
			                        .setParameter("username", username)
			                        .setFirstResult(0)
			                        .setMaxResults(messageCount)
			                        .getResultList();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}

		postList.forEach(post -> post.setComments(Collections.EMPTY_SET));

		return postList;
	}

	@Override
	public PostEntity findWithComments(final Integer postId) throws DaoException
	{
		logger.trace("GETTING POST WITH COMMENTS ID = " + postId);

		try
		{
			return entityManager.createNamedQuery("Post.findByIdWithComments", PostEntity.class)
			                    .setParameter("id", postId)
			                    .getSingleResult();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

}
