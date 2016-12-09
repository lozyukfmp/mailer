package by.samsolutions.converter.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.samsolutions.converter.Converter;
import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.CommentEntity;
import by.samsolutions.entity.user.UserEntity;

@Component
public class UserConverter implements Converter<UserDto, UserEntity>
{
	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	public UserDto toDto(final UserEntity userEntity) throws ConverterException
	{
		return UserDto.builder().username(userEntity.getUsername()).build();
	}

	@Override
	public UserEntity toEntity(final UserDto userDto) throws ConverterException
	{
		return UserEntity.builder()
		                 .username(userDto.getUsername())
		                 .password(userDto.getPassword())
		                 .profile(userProfileConverter.toEntity(userDto.getUserProfileDto()))
		                 .build();
	}

	@Override
	public Collection<UserDto> toDtoCollection(final Collection<UserEntity> userEntities) throws ConverterException
	{
		Collection<UserDto> dtoCollection = new ArrayList<>();
		for (UserEntity userEntity : userEntities)
			dtoCollection.add(toDto(userEntity));

		return dtoCollection;
	}

	@Override
	public Collection<UserEntity> toEntityCollection(final Collection<UserDto> userDtos) throws ConverterException
	{
		Collection<UserEntity> dtoCollection = new ArrayList<>();
		for (UserDto userDto : userDtos)
			dtoCollection.add(toEntity(userDto));

		return dtoCollection;
	}
}
