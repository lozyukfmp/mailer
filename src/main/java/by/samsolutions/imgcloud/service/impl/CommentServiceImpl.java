package by.samsolutions.imgcloud.service.impl;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.CommentConverter;
import by.samsolutions.imgcloud.dao.CommentDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.dto.CommentDto;
import by.samsolutions.imgcloud.entity.CommentEntity;
import by.samsolutions.imgcloud.service.CommentService;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public class CommentServiceImpl extends GenericServiceImpl<CommentDto, CommentEntity, Integer> implements CommentService
{
	private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

	private CommentDao       commentDao;
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
		logger.trace("GETTING COMMENT LIST BY POST ID = " + postId);
		try
		{
			Collection<CommentEntity> commentEntityCollection = commentDao.findAllByPostId(postId, commentIndex);
			Collection<CommentDto> commentDtoCollection = commentConverter.toDtoCollection(commentEntityCollection);
			return commentDtoCollection;
		}
		catch (DaoException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

}
