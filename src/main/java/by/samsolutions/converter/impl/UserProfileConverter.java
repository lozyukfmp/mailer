package by.samsolutions.converter.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import by.samsolutions.converter.Converter;
import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.user.UserProfileEntity;

@Component
public class UserProfileConverter implements Converter<UserProfileDto, UserProfileEntity>
{
	private static final String NO_AVATAR_IMAGE_URL = "/static/core/pictures/no_avatar.jpg";

	@Override
	public UserProfileDto toDto(final UserProfileEntity userProfile) throws ConverterException
	{
		return UserProfileDto.builder()
		                     .username(userProfile.getUsername())
		                     .email(userProfile.getEmail())
		                     .firstName(userProfile.getFirstName())
		                     .secondName(userProfile.getSecondName())
		                     .thirdName(userProfile.getThirdName())
		                     .imageUrl(userProfile.getImageUrl())
		                     .build();

	}

	@Override
	public UserProfileEntity toEntity(final UserProfileDto userProfileDto) throws ConverterException
	{

		UserProfileEntity userProfileEntity = UserProfileEntity.builder()
		                                                       .username(userProfileDto.getUsername())
		                                                       .email(userProfileDto.getEmail())
		                                                       .firstName(userProfileDto.getFirstName())
		                                                       .secondName(userProfileDto.getSecondName())
		                                                       .thirdName(userProfileDto.getThirdName())
		                                                       .build();
		if (userProfileDto.getImageUrl() == null)
		{
			userProfileEntity.setImageUrl(NO_AVATAR_IMAGE_URL);
		}

		return userProfileEntity;
	}

	@Override
	public Collection<UserProfileDto> toDtoCollection(final Collection<UserProfileEntity> userProfileEntities)
					throws ConverterException
	{
		Collection<UserProfileDto> dtoCollection = new ArrayList<>();
		for (UserProfileEntity userProfileEntity : userProfileEntities)
			dtoCollection.add(toDto(userProfileEntity));

		return dtoCollection;
	}

	@Override
	public Collection<UserProfileEntity> toEntityCollection(final Collection<UserProfileDto> userProfileDtos)
					throws ConverterException
	{
		Collection<UserProfileEntity> dtoCollection = new ArrayList<>();
		for (UserProfileDto userProfileDto : userProfileDtos)
			dtoCollection.add(toEntity(userProfileDto));

		return dtoCollection;
	}
}
