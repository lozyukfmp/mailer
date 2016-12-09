package by.samsolutions.converter;

import java.util.Collection;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.dto.BaseDto;
import by.samsolutions.entity.BaseEntity;

public interface Converter<Dto extends BaseDto, Entity extends BaseEntity>
{
	Dto toDto(Entity entity) throws ConverterException;

	Entity toEntity(Dto dto) throws ConverterException;

	Collection<Dto> toDtoCollection(Collection<Entity> entityCollection) throws ConverterException;

	Collection<Entity> toEntityCollection(Collection<Dto> dtoCollection) throws ConverterException;
}
