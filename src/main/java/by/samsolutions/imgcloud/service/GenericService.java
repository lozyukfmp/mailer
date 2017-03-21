package by.samsolutions.imgcloud.service;

import by.samsolutions.imgcloud.dto.BaseDto;
import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

import java.io.Serializable;
import java.util.Collection;

public interface GenericService<Dto extends BaseDto, Entity extends BaseEntity, PK extends Serializable>
{
	Dto create(Dto entity) throws ServiceException;

	void delete(PK id) throws ServiceException;

	Dto find(PK id) throws ServiceException;

	Dto update(Dto entity) throws ServiceException;

	Collection<Dto> all() throws ServiceException;

}
