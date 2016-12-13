package by.samsolutions.dao.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.PostDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.PostEntity;

@Repository
public class PostDaoImpl extends GenericDaoImpl<PostEntity, Integer>
				implements PostDao
{

	public PostDaoImpl()
	{
		super(PostEntity.class);
	}

	@Override
	public List<PostEntity> all(final String username, final Integer messageCount) throws DaoException
	{
		List<PostEntity> postList = null;
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
			throw new DaoException(e);
		}

		postList.forEach(post -> post.setComments(Collections.EMPTY_SET));

		return postList;
	}

	@Override
	public PostEntity findWithComments(final Integer postId) throws DaoException
	{
		try
		{
			return entityManager.createNamedQuery("Post.findByIdWithComments", PostEntity.class)
			                    .setParameter("id", postId)
			                    .getSingleResult();
		}
		catch (Exception e)
		{
			throw new DaoException(e);
		}
	}

}
