package by.samsolutions.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable>
{
	T create(T t);

	void delete(PK id);

	T find(PK id);

	T update(T t);

	List<T> all();
}
