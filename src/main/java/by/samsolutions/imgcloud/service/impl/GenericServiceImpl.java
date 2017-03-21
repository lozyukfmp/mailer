package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dao.BaseDao;
import by.samsolutions.imgcloud.dto.BaseDto;
import by.samsolutions.imgcloud.nodeentity.BaseEntity;
import by.samsolutions.imgcloud.service.GenericService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GenericServiceImpl<Dto extends BaseDto, Entity extends BaseEntity, PK extends Serializable>
				implements GenericService<Dto, Entity, PK> {
	private static final Logger logger = LogManager.getLogger(GenericServiceImpl.class);

	private BaseDao<Entity, PK> crudDao;
	private Converter<Dto, Entity> converter;

	public GenericServiceImpl(BaseDao<Entity, PK> genericDao, Converter<Dto, Entity> converter) {
		this.crudDao = genericDao;
		this.converter = converter;
	}

	public GenericServiceImpl() {

	}

	@Override
	@Transactional
	public Dto create(final Dto dto) throws ServiceException {
		logger.trace("CREATING DTO : " + dto);
		try {
			Entity entity = converter.toEntity(dto);
			Dto resultDto = converter.toDto(crudDao.save(entity));
			return resultDto;
		}
		catch (ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void delete(final PK id) throws ServiceException {
		logger.trace("DELETING DTO : ID = " + id);
		try {
			crudDao.removeByUuid(id);
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Dto find(final PK id) throws ServiceException {
		logger.trace("GETTING DTO : ID = " + id);
		try {
			Entity entity = crudDao.findByUuid(id);
			Dto resultDto = converter.toDto(entity);
			return resultDto;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public Dto update(final Dto dto) throws ServiceException {
		logger.trace("UPDATING DTO : " + dto);
		try {
			Entity entity = converter.toEntity(dto);
			Dto resultDto = converter.toDto(crudDao.save(entity));
			return resultDto;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Dto> all() throws ServiceException {
		logger.trace("GETTING DTO LIST");
		try {
			List<Entity> entityList = new ArrayList<>();
			crudDao.findAll().forEach(entity -> entityList.add(entity));
			Collection<Dto> dtoCollection = converter.toDtoCollection(entityList);
			return dtoCollection;
		}
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}
}
