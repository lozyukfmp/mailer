package by.samsolutions.imgcloud.dao.impl;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.user.UserEntity;

@Repository
public class UserDaoImpl extends GenericDaoImpl<UserEntity, String> implements UserDao
{
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

	public UserDaoImpl()
	{
		super(UserEntity.class);
	}

	@Override
	public List<UserEntity> all() throws DaoException
	{
		logger.trace("GETTING ALL USERS");
		try
		{
			return entityManager.createNamedQuery("User.findAll", UserEntity.class).getResultList();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public List<UserEntity> getAll(Integer userCount) throws DaoException
	{
		logger.trace("GETTING ALL USERS (COUNT = " + userCount + " )");
		try
		{
			return entityManager.createNamedQuery("User.findAllWithProfile", UserEntity.class)
			                    .setFirstResult(0)
			                    .setMaxResults(userCount)
			                    .getResultList();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public List<UserEntity> getByUsernameWithProfile(final String username) throws DaoException
	{
		logger.trace("GETTING USERS WITH PROFILE , USERNAME LIKE = " + username);
		try
		{
			return entityManager.createNamedQuery("User.findWithProfileByQuery", UserEntity.class)
			                    .setParameter("username", "%" + username + "%")
			                    .getResultList();
		}
		catch (NoResultException e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public void setUserEnabled(final String username, final Boolean enabled) throws DaoException
	{
		logger.trace("TRYING TO LOCK/UNLOCK USER (USERNAME = " + username + ").");
		try
		{

			entityManager.createNamedQuery("User.setEnabled")
			             .setParameter("enabled", enabled)
			             .setParameter("username", username)
			             .executeUpdate();
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

    @Override
    public Collection<UserEntity> getWithProfileByFullName(String firstName, String secondName, String thirdName) throws DaoException {
        try
        {

            return entityManager.createNamedQuery("User.findWithProfileByFullName")
                    .setParameter("firstName", firstName)
                    .setParameter("secondName", secondName)
                    .setParameter("thirdName", thirdName)
                    .getResultList();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            throw new DaoException(e);
        }
    }
}
