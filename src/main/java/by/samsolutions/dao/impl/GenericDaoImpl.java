package by.samsolutions.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import by.samsolutions.dao.GenericDao;

public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK>
{

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> type;

	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T create(final T t)
	{
		return (T) getSession().save(t);
	}

	@Override
	public void delete(final PK id)
	{
		getSession().delete(id);
	}

	@Override
	public T find(final PK id)
	{
		return (T) getSession().get(type, id);
	}

	@Override
	public T update(final T t)
	{
		getSession().saveOrUpdate(t);
		return t;
	}

	@Override
	public List<T> all()
	{
		return null;
	}
}
