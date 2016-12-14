package by.samsolutions.service.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.Converter;
import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.dao.GenericDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.dto.BaseDto;
import by.samsolutions.entity.BaseEntity;
import by.samsolutions.service.GenericService;
import by.samsolutions.service.exception.ServiceException;

@Service
public class GenericServiceImpl<Dto extends BaseDto, Entity extends BaseEntity, PK extends Serializable>
				implements GenericService<Dto, Entity, PK>
{
	private static final Logger logger = LogManager.getLogger(GenericServiceImpl.class);

	private GenericDao<Entity, PK> genericDao;
	private Converter<Dto, Entity> converter;

	public GenericServiceImpl(GenericDao<Entity, PK> genericDao, Converter<Dto, Entity> converter)
	{
		this.genericDao = genericDao;
		this.converter = converter;
	}

	public GenericServiceImpl()
	{

	}

	@Override
	@Transactional
	public Dto create(final Dto dto) throws ServiceException
	{
		logger.trace("CREATING DTO : " + dto);
		try
		{
			Entity entity = converter.toEntity(dto);
			System.out.println(entity);
			Dto resultDto = converter.toDto(genericDao.create(entity));
			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void delete(final PK id) throws ServiceException
	{
		logger.trace("DELETING DTO : ID = " + id);
		try
		{
			genericDao.delete(id);
		}
		catch (DaoException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Dto find(final PK id) throws ServiceException
	{
		logger.trace("GETTING DTO : ID = " + id);
		try
		{
			Entity entity = genericDao.find(id);
			Dto resultDto = converter.toDto(entity);
			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public Dto update(final Dto dto) throws ServiceException
	{
		logger.trace("UPDATING DTO : " + dto);
		try
		{
			Entity entity = converter.toEntity(dto);
			Dto resultDto = converter.toDto(genericDao.update(entity));
			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Dto> all() throws ServiceException
	{
		logger.trace("GETTING DTO LIST");
		try
		{
			Collection<Entity> entityCollection = genericDao.all();
			Collection<Dto> dtoCollection = converter.toDtoCollection(entityCollection);
			return dtoCollection;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}

	}
}
