package by.samsolutions.imgcloud.service.impl;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.PostConverter;
import by.samsolutions.imgcloud.dao.CommentDao;
import by.samsolutions.imgcloud.dao.PostDao;
import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.entity.CommentEntity;
import by.samsolutions.imgcloud.entity.PostEntity;
import by.samsolutions.imgcloud.service.PostService;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public class PostServiceImpl extends GenericServiceImpl<PostDto, PostEntity, Integer> implements PostService
{
	private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

	private PostDao       postDao;
	private PostConverter postConverter;

	@Autowired
	private CommentDao commentDao;

	public PostServiceImpl()
	{

	}

	@Autowired
	public PostServiceImpl(@Autowired PostDao postDao, @Autowired PostConverter postConverter)
	{
		super(postDao, postConverter);
		this.postDao = postDao;
		this.postConverter = postConverter;
	}

	@Override
	public PostDto find(final Integer id) throws ServiceException
	{
		logger.trace("GETTING POST BY ID = " + id);
		try
		{
			PostEntity post = postDao.find(id);

			if (post != null)
			{
				Collection<CommentEntity> comments = commentDao.findAllByPostId(id, 2);
				post.setComments(comments);
			}

			PostDto resultDto = postConverter.toDto(post);

			return resultDto;
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PostDto> getAll(final String username, final Integer messageCount) throws ServiceException
	{
		logger.trace("GETTING POSTS BY USERNAME = " + username);
		try
		{
			Collection<PostEntity> postEntityCollection = postDao.all(username, messageCount);
			return postConverter.toDtoCollection(postEntityCollection);
		}
		catch (DaoException | ConverterException e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
