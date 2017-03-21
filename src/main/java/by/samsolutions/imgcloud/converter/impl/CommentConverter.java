package by.samsolutions.imgcloud.converter.impl;

import by.samsolutions.imgcloud.converter.Converter;
import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.dto.CommentDto;
import by.samsolutions.imgcloud.nodeentity.CommentNodeEntity;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class CommentConverter implements Converter<CommentDto, CommentNodeEntity> {
	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");

	@Override
	public CommentDto toDto(final CommentNodeEntity commentEntity) throws ConverterException {
		try
		{
			return CommentDto.builder()
			                 .id(Long.toString(commentEntity.getUuid()))
			                 .postId(Long.toString(commentEntity.getPostId()))
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
	public CommentNodeEntity toEntity(final CommentDto commentDto) throws ConverterException
	{
		try
		{
			CommentNodeEntity commentEntity = CommentNodeEntity.builder()
			                                           .postId(Long.parseLong(commentDto.getPostId()))
			                                           .text(commentDto.getText())
			                                           .username(commentDto.getUsername())
			                                           .build();
			if (commentDto.getId() != null) {
				commentEntity.setUuid(Long.parseLong(commentDto.getId()));
			}

			if (commentDto.getDate() == null) {
				commentEntity.setDate((new Date()));
			}
			else {
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
	public Collection<CommentDto> toDtoCollection(final Collection<CommentNodeEntity> commentEntities) throws ConverterException
	{
		Collection<CommentDto> dtoCollection = new ArrayList<>();
		for (CommentNodeEntity commentEntity : commentEntities)
			dtoCollection.add(toDto(commentEntity));

		return dtoCollection;
	}

	@Override
	public Collection<CommentNodeEntity> toEntityCollection(final Collection<CommentDto> commentDtos) throws ConverterException
	{
		Collection<CommentNodeEntity> dtoCollection = new ArrayList<>();
		for (CommentDto commentDto : commentDtos)
			dtoCollection.add(toEntity(commentDto));

		return dtoCollection;
	}
}
