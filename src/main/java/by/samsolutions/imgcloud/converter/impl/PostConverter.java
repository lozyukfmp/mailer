package by.samsolutions.imgcloud.converter.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.entity.PostEntity;

@Component
public class PostConverter implements Converter<PostDto, PostEntity>
{
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");

	@Autowired
	private CommentConverter commentConverter;

	@Override
	public PostDto toDto(final PostEntity postEntity) throws ConverterException
	{
		try
		{
			PostDto postDto = PostDto.builder()
			                         .id(Integer.toString(postEntity.getId()))
			                         .text(postEntity.getText())
			                         .username(postEntity.getUsername())
			                         .imageUrl(postEntity.getImageUrl())
					.image(postEntity.getImage())
			                         .date(formatter.format(postEntity.getDate()))
			                         .build();

			if (postEntity.getComments() != null)
			{
				postDto.setComments(commentConverter.toDtoCollection(postEntity.getComments()));
			}

			return postDto;

		}
		catch (NumberFormatException e1)
		{
			throw new ConverterException(e1);
		}
	}

	@Override
	public PostEntity toEntity(final PostDto postDto) throws ConverterException
	{
		try
		{
			PostEntity postEntity = PostEntity.builder()
			                                  .text(postDto.getText())
			                                  .username(postDto.getUsername())
			                                  .imageUrl(postDto.getImageUrl())
												.image(postDto.getImage())
			                                  .build();

			if (postDto.getId() != null)
			{
				postEntity.setId(Integer.parseInt(postDto.getId()));
			}

			if (postDto.getDate() == null)
			{
				postEntity.setDate(new Date());
			}
			else
			{
				postEntity.setDate(formatter.parse(postDto.getDate()));
			}

			return postEntity;
		}
		catch (NumberFormatException e1)
		{
			throw new ConverterException(e1);
		}
		catch (ParseException e2)
		{
			throw new ConverterException(e2);
		}
	}

	@Override
	public Collection<PostDto> toDtoCollection(final Collection<PostEntity> postEntities) throws ConverterException
	{
		Collection<PostDto> dtoCollection = new ArrayList<>();
		for (PostEntity postEntity : postEntities)
			dtoCollection.add(toDto(postEntity));

		return dtoCollection;
	}

	@Override
	public Collection<PostEntity> toEntityCollection(final Collection<PostDto> postDtos) throws ConverterException
	{
		Collection<PostEntity> dtoCollection = new ArrayList<>();
		for (PostDto commentDto : postDtos)
			dtoCollection.add(toEntity(commentDto));

		return dtoCollection;
	}
}
