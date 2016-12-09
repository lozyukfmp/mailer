package by.samsolutions.dao;

import java.io.Serializable;
import java.util.List;

import by.samsolutions.entity.BaseEntity;

public interface GenericDao<E extends BaseEntity, PK extends Serializable>
{
	E create(E entity);

	void delete(PK id);

	E find(PK id);

	E update(E entity);

	List<E> all();
}
