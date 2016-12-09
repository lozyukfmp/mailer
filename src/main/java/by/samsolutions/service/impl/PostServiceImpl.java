package by.samsolutions.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.converter.exception.ConverterException;
import by.samsolutions.converter.impl.PostConverter;
import by.samsolutions.dao.CommentDao;
import by.samsolutions.dao.PostDao;
import by.samsolutions.dto.PostDto;
import by.samsolutions.entity.CommentEntity;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.service.PostService;
import by.samsolutions.service.exception.ServiceException;

@Service
public class PostServiceImpl extends GenericServiceImpl<PostDto, PostEntity, Integer> implements PostService
{
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
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PostDto> getAll(final String username, final Integer messageCount) throws ServiceException
	{
		try
		{
			Collection<PostEntity> postEntityCollection = postDao.all(username, messageCount);
			return postConverter.toDtoCollection(postEntityCollection);
		}
		catch (ConverterException e)
		{
			throw new ServiceException(e);
		}
	}

}
