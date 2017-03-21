package by.samsolutions.imgcloud.converter.impl;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class PostConverter implements Converter<PostDto, PostNodeEntity>
{
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");

	@Autowired
	private CommentConverter commentConverter;

	@Override
	public PostDto toDto(final PostNodeEntity postEntity) throws ConverterException
	{
		try
		{
			PostDto postDto = PostDto.builder()
			                         .id(Long.toString(postEntity.getUuid()))
			                         .text(postEntity.getText())
			                         .username(postEntity.getUsername())
			                         .imageUrl(postEntity.getImageUrl())
			                         .date(formatter.format(postEntity.getDate()))
			                         .build();

			if (postEntity.getComments() != null)
			{
				System.out.println("COMMENT SIZE: " + postEntity.getComments().size());
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
	public PostNodeEntity toEntity(final PostDto postDto) throws ConverterException {
		try {
			PostNodeEntity postEntity = PostNodeEntity.builder()
			                                  .text(postDto.getText())
			                                  .username(postDto.getUsername())
			                                  .imageUrl(postDto.getImageUrl())
			                                  .build();

			if (postDto.getId() != null) {
				postEntity.setId(Long.parseLong(postDto.getId()));
			}

			if (postDto.getDate() == null) {
				postEntity.setDate(new Date());
			}
			else {
				postEntity.setDate(formatter.parse(postDto.getDate()));
			}

			return postEntity;
		}
		catch (NumberFormatException e1) {
			throw new ConverterException(e1);
		}
		catch (ParseException e2) {
			throw new ConverterException(e2);
		}
	}

	@Override
	public Collection<PostDto> toDtoCollection(final Collection<PostNodeEntity> postEntities) throws ConverterException {
		Collection<PostDto> dtoCollection = new ArrayList<>();
		for (PostNodeEntity postEntity : postEntities)
			dtoCollection.add(toDto(postEntity));

		return dtoCollection;
	}

	@Override
	public Collection<PostNodeEntity> toEntityCollection(final Collection<PostDto> postDtos) throws ConverterException
	{
		Collection<PostNodeEntity> dtoCollection = new ArrayList<>();
		for (PostDto commentDto : postDtos)
			dtoCollection.add(toEntity(commentDto));

		return dtoCollection;
	}
}
