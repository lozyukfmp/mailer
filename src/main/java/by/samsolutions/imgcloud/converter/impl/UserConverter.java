package by.samsolutions.imgcloud.converter.impl;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.nodeentity.user.UserNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserConverter implements Converter<UserDto, UserNodeEntity>
{
	@Autowired
	private UserProfileConverter userProfileConverter;

	@Override
	public UserDto toDto(final UserNodeEntity userEntity) throws ConverterException
	{
		UserDto userDto = UserDto.builder().username(userEntity.getUsername()).enabled(userEntity.isEnabled()).build();

		if (userEntity.getProfile() != null)
		{
			userDto.setProfile(userProfileConverter.toDto(userEntity.getProfile()));
		}

		userDto.setAdmin(userEntity.getUserRole().stream().anyMatch(userRoleEntity -> userRoleEntity.getRole().equals
						("ROLE_ADMIN")));

		return userDto;
	}

	@Override
	public UserNodeEntity toEntity(final UserDto userDto) throws ConverterException
	{
		return UserNodeEntity.builder()
		                 .username(userDto.getUsername())
		                 .password(userDto.getPassword())
		                 .profile(userProfileConverter.toEntity(userDto.getProfile()))
		                 .build();
	}

	@Override
	public Collection<UserDto> toDtoCollection(final Collection<UserNodeEntity> userEntities) throws ConverterException
	{
		Collection<UserDto> dtoCollection = new ArrayList<>();
		for (UserNodeEntity userEntity : userEntities)
			dtoCollection.add(toDto(userEntity));

		return dtoCollection;
	}

	@Override
	public Collection<UserNodeEntity> toEntityCollection(final Collection<UserDto> userDtos) throws ConverterException
	{
		Collection<UserNodeEntity> dtoCollection = new ArrayList<>();
		for (UserDto userDto : userDtos)
			dtoCollection.add(toEntity(userDto));

		return dtoCollection;
	}
}
