package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.converter.impl.PostConverter;
import by.samsolutions.imgcloud.dao.PostDao;
import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import by.samsolutions.imgcloud.service.PostService;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PostServiceImpl extends GenericServiceImpl<PostDto, PostNodeEntity, Long> implements PostService
{
	private static final Logger logger = LogManager.getLogger(PostServiceImpl.class);

	private PostDao       postDao;
	private PostConverter postConverter;

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
	@Transactional(readOnly = true)
	public Collection<PostDto> getAll(final String username, final Integer messageCount) throws ServiceException
	{
		logger.trace("GETTING POSTS BY USERNAME = " + username);
		try
		{
			Pageable pageRequest = new PageRequest(0, messageCount, Sort.Direction.DESC, "date");
			List<PostNodeEntity> postEntityCollection = new ArrayList<>();
            postDao.findByUsername(username, pageRequest).forEach(entity -> postEntityCollection.add(entity));
            return postConverter.toDtoCollection(postEntityCollection);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

}
