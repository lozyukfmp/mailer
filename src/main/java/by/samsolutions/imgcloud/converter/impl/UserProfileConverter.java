package by.samsolutions.imgcloud.converter.impl;

import by.samsolutions.imgcloud.controller.util.FileUtil;
import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.UserProfileDto;
import by.samsolutions.imgcloud.entity.user.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserProfileConverter implements Converter<UserProfileDto, UserProfileEntity>
{

	@Value("${noAvatarUrl}")
	private String NO_AVATAR_IMAGE_URL;

	@Autowired
	private FileUtil fileUtil;

	@Override
	public UserProfileDto toDto(final UserProfileEntity userProfile) throws ConverterException
	{
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .firstName(userProfile.getFirstName())
                .secondName(userProfile.getSecondName())
                .thirdName(userProfile.getThirdName())
                .imageUrl(fileUtil.encodeToBase64String(userProfile.getImage()))
                .build();
        if (userProfile.getImage() == null)
        {
            userProfileDto.setImageUrl(NO_AVATAR_IMAGE_URL);
        }
        return userProfileDto;

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
                .image(fileUtil.decodeToByteArray(userProfileDto.getImageUrl()))
                .build();

        if (userProfileDto.getImageUrl() == NO_AVATAR_IMAGE_URL)
        {
            userProfileEntity.setImage(null);
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

	private byte[] getNoAvatarImage(String url) throws IOException {
		//return Files.readAllBytes(new File(url).toPath());
        return null;
	}
}
