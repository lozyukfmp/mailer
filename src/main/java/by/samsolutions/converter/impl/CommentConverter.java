package by.samsolutions.converter.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Component;

import by.samsolutions.converter.Converter;
import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.entity.CommentEntity;

@Component
public class CommentConverter implements Converter<CommentDto, CommentEntity>
{
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");

	@Override
	public CommentDto toDto(final CommentEntity commentEntity) throws ConverterException
	{
		try
		{
			return CommentDto.builder()
			                 .id(Integer.toString(commentEntity.getId()))
			                 .postId(Integer.toString(commentEntity.getPostId()))
			                 .text(commentEntity.getText())
			                 .username(commentEntity.getUsername())
			                 .date(formatter.format(commentEntity.getDate()))
			                 .build();
		}
		catch (NumberFormatException e)
		{
			throw new ConverterException(e);
		}
	}

	@Override
	public CommentEntity toEntity(final CommentDto commentDto) throws ConverterException
	{
		try
		{
			CommentEntity commentEntity = CommentEntity.builder()
			                                           .postId(Integer.parseInt(commentDto.getPostId()))
			                                           .text(commentDto.getText())
			                                           .username(commentDto.getUsername())
			                                           .build();
			if (commentDto.getId() != null)
			{
				commentEntity.setId(Integer.parseInt(commentDto.getId()));
			}

			if (commentDto.getDate() == null)
			{
				commentEntity.setDate(new Date());
			}
			else
			{
				commentEntity.setDate(formatter.parse(commentDto.getDate()));
			}

			return commentEntity;
		}

		catch (NumberFormatException e)
		{
			throw new ConverterException(e);
		}
		catch (ParseException e1)
		{
			throw new ConverterException(e1);
		}
	}

	@Override
	public Collection<CommentDto> toDtoCollection(final Collection<CommentEntity> commentEntities) throws ConverterException
	{
		Collection<CommentDto> dtoCollection = new ArrayList<>();
		for (CommentEntity commentEntity : commentEntities)
			dtoCollection.add(toDto(commentEntity));

		return dtoCollection;
	}

	@Override
	public Collection<CommentEntity> toEntityCollection(final Collection<CommentDto> commentDtos) throws ConverterException
	{
		Collection<CommentEntity> dtoCollection = new ArrayList<>();
		for (CommentDto commentDto : commentDtos)
			dtoCollection.add(toEntity(commentDto));

		return dtoCollection;
	}
}
