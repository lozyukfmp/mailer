package by.samsolutions.imgcloud.converter.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.entity.user.UserEntity;

@Component
public class UserConverter implements Converter<UserDto, UserEntity>
{
	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	public UserDto toDto(final UserEntity userEntity) throws ConverterException
	{
		UserDto userDto = UserDto.builder().username(userEntity.getUsername()).enabled(userEntity.isEnabled()).build();

		if (userEntity.getProfile() != null)
		{
			userDto.setProfile(userProfileConverter.toDto(userEntity.getProfile()));
		}

		return userDto;
	}

	@Override
	public UserEntity toEntity(final UserDto userDto) throws ConverterException
	{
		return UserEntity.builder()
		                 .username(userDto.getUsername())
		                 .password(userDto.getPassword())
		                 .profile(userProfileConverter.toEntity(userDto.getProfile()))
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
