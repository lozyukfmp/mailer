package by.samsolutions.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.samsolutions.dao.GenericDao;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>
{

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public T create(final T t)
	{
		entityManager.persist(t);
		return t;
	}

	@Override
	public void delete(final PK id)
	{
		entityManager.remove(entityManager.getReference(type, id));
	}

	@Override
	public T find(final PK id)
	{
		return (T) entityManager.find(type, id);
	}

	@Override
	public T update(final T t)
	{
		return entityManager.merge(t);
	}

	@Override
	public List<T> all()
	{
		return null;
	}
}
