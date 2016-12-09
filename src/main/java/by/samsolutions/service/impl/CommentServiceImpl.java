package by.samsolutions.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.CommentConverter;
import by.samsolutions.dao.CommentDao;
import by.samsolutions.dto.CommentDto;
import by.samsolutions.entity.CommentEntity;
import by.samsolutions.service.CommentService;
import by.samsolutions.service.exception.ServiceException;

@Service
public class CommentServiceImpl extends GenericServiceImpl<CommentDto, CommentEntity, Integer> implements CommentService
{
	private CommentDao commentDao;
	private CommentConverter commentConverter;

	public CommentServiceImpl()
	{

	}

	@Autowired
	public CommentServiceImpl(@Autowired CommentDao commentDao, @Autowired CommentConverter commentConverter)
	{
		super(commentDao, commentConverter);
		this.commentDao = commentDao;
		this.commentConverter = commentConverter;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<CommentDto> getCommentListByPostId(final Integer postId, final Integer commentIndex) throws ServiceException
	{
		try
		{
			Collection<CommentEntity> commentEntityCollection = commentDao.findAllByPostId(postId, commentIndex);
			Collection<CommentDto> commentDtoCollection = commentConverter.toDtoCollection(commentEntityCollection);
			return commentDtoCollection;
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

}
