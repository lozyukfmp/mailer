package by.samsolutions.imgcloud.converter.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.nodeentity.user.UserProfileNodeEntity;

@Component
public class UserProfileConverter implements Converter<UserProfileDto, UserProfileNodeEntity>
{

	@Value("${noAvatarUrl}")
	private String NO_AVATAR_IMAGE_URL;

	@Override
	public UserProfileDto toDto(final UserProfileNodeEntity userProfile) throws ConverterException
	{
		UserProfileDto userProfileDto = UserProfileDto.builder()
				.username(userProfile.getUsername())
				.email(userProfile.getEmail())
				.firstName(userProfile.getFirstName())
				.secondName(userProfile.getSecondName())
				.thirdName(userProfile.getThirdName())
				.imageUrl(userProfile.getImageUrl())
				.build();

		if (userProfile.getImageUrl() == null)
		{
			userProfileDto.setImageUrl(NO_AVATAR_IMAGE_URL);
		}

		return userProfileDto;
	}

	@Override
	public UserProfileNodeEntity toEntity(final UserProfileDto userProfileDto) throws ConverterException
	{

		UserProfileNodeEntity userProfileEntity = UserProfileNodeEntity.builder()
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
	public Collection<UserProfileDto> toDtoCollection(final Collection<UserProfileNodeEntity> userProfileEntities)
					throws ConverterException
	{
		Collection<UserProfileDto> dtoCollection = new ArrayList<>();
		for (UserProfileNodeEntity userProfileEntity : userProfileEntities)
			dtoCollection.add(toDto(userProfileEntity));

		return dtoCollection;
	}

	@Override
	public Collection<UserProfileNodeEntity> toEntityCollection(final Collection<UserProfileDto> userProfileDtos)
					throws ConverterException
	{
		Collection<UserProfileNodeEntity> dtoCollection = new ArrayList<>();
		for (UserProfileDto userProfileDto : userProfileDtos)
			dtoCollection.add(toEntity(userProfileDto));

		return dtoCollection;
	}
}
