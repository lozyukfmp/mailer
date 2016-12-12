package by.samsolutions.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.user.UserEntity;

@Repository
public class UserDaoImpl extends GenericDaoImpl<UserEntity, String> implements UserDao
{

	public UserDaoImpl()
	{
		super(UserEntity.class);
	}

	@Override
	public List<UserEntity> all()
	{
		return entityManager.createNamedQuery("User.findAll", UserEntity.class)
		             .getResultList();
	}

	@Override
	public List<UserEntity> getAll(Integer userCount)
	{
		return entityManager.createNamedQuery("User.findAllWithProfile", UserEntity.class)
		                    .setFirstResult(0)
		                    .setMaxResults(userCount)
		                    .getResultList();
	}

	@Override
	public UserEntity getByUsernameWithProfile(final String username) throws DaoException
	{
		try
		{
			return entityManager.createNamedQuery("User.findWithProfileByUsername", UserEntity.class)
			                    .setParameter("username", username)
			                    .getSingleResult();
		}
		catch (NoResultException e)
		{
			throw new DaoException(e);
		}
	}

	@Override
	public void setUserEnabled(final String username, final Boolean enabled) throws DaoException
	{
		entityManager.createNamedQuery("User.setEnabled")
		             .setParameter("enabled", enabled)
		             .setParameter("username", username)
		             .executeUpdate();
	}
}
