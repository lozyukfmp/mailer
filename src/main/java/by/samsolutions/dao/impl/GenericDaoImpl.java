package by.samsolutions.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.BaseEntity;

public class GenericDaoImpl<T extends BaseEntity, PK extends Serializable> implements GenericDao<T, PK>
{

	private static final Logger logger = LogManager.getLogger(GenericDaoImpl.class);

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl()
	{

	}

	public GenericDaoImpl(Class<T> type)
	{
		this.type = type;
	}

	@Override
	public T create(final T t) throws DaoException
	{
		logger.trace("Creating entity: " + t);
		try
		{
			entityManager.persist(t);
			return t;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(final PK id) throws DaoException
	{
		logger.trace("DELETING ENTITY ID = " + id);
		try
		{
			entityManager.remove(entityManager.getReference(type, id));
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public T find(final PK id) throws DaoException
	{
		logger.trace("TRYING TO FIND ENTITY WITH ID = " + id);
		try
		{
			return (T) entityManager.find(type, id);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public T update(final T t) throws DaoException
	{
		logger.trace("UPDATING ENTITY : " + t);
		try
		{
			return entityManager.merge(t);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new DaoException(e);
		}
	}

	@Override
	public List<T> all() throws DaoException
	{
		return null;
	}
}
