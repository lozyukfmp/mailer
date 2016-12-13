package by.samsolutions.dao;

import java.io.Serializable;
import java.util.List;

import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.BaseEntity;

public interface GenericDao<E extends BaseEntity, PK extends Serializable>
{
	E create(E entity) throws DaoException;

	void delete(PK id) throws DaoException;

	E find(PK id) throws DaoException;

	E update(E entity) throws DaoException;

	List<E> all() throws DaoException;
}
