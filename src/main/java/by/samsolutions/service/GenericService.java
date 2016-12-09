package by.samsolutions.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import by.samsolutions.dto.BaseDto;
import by.samsolutions.entity.BaseEntity;
import by.samsolutions.service.exception.ServiceException;

public interface GenericService<Dto extends BaseDto, Entity extends BaseEntity, PK extends Serializable>
{
	Dto create(Dto entity) throws ServiceException;

	void delete(PK id) throws ServiceException;

	Dto find(PK id) throws ServiceException;

	Dto update(Dto entity) throws ServiceException;

	Collection<Dto> all() throws ServiceException;

}
