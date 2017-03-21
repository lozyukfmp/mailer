package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.converter.exception.ConverterException;
import by.samsolutions.imgcloud.converter.impl.CommentConverter;
import by.samsolutions.imgcloud.dao.CommentDao;
import by.samsolutions.imgcloud.dao.PostDao;
import by.samsolutions.imgcloud.dto.CommentDto;
import by.samsolutions.imgcloud.nodeentity.CommentNodeEntity;
import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import by.samsolutions.imgcloud.service.CommentService;
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
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl extends GenericServiceImpl<CommentDto, CommentNodeEntity, Long> implements CommentService {
	private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);

	private CommentDao commentDao;

	@Autowired
	private PostDao postDao;
	private CommentConverter commentConverter;

	public CommentServiceImpl() {

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
	public Collection<CommentDto> getCommentListByPostId(final Long postId, final Integer commentIndex) throws ServiceException
	{
		logger.trace("GETTING COMMENT LIST BY POST ID = " + postId);
		try
		{
            Pageable pageRequest = new PageRequest(0, commentIndex, Sort.Direction.DESC, "date");
            List<CommentNodeEntity> entityList = new ArrayList<>();
            commentDao.findByPostId(postId, pageRequest).forEach(entity -> entityList.add(entity));
            Collection<CommentDto> commentDtoCollection = commentConverter.toDtoCollection(entityList);
			return commentDtoCollection;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public CommentDto create(CommentDto dto) throws ServiceException {
        try {
            CommentNodeEntity commentNodeEntity = commentConverter.toEntity(dto);
            PostNodeEntity postNodeEntity = postDao.findByUuid(commentNodeEntity.getPostId());
            postNodeEntity.getComments().add(commentNodeEntity);
            postDao.save(postNodeEntity);
            return dto;
        } catch (ConverterException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public CommentDto update(CommentDto dto) throws ServiceException {
        try {
            CommentNodeEntity commentNodeEntity = commentConverter.toEntity(dto);
            PostNodeEntity postNodeEntity = postDao.findByUuid(commentNodeEntity.getPostId());
            System.out.println("ENTITY: " + commentNodeEntity);
            System.out.println("BEFORE: " + postNodeEntity);
            Set filtered = postNodeEntity.getComments()
                    .stream()
                    .filter(comment -> !comment.getUuid().equals(commentNodeEntity.getUuid()))
                    .collect(Collectors.toSet());
            System.out.println("FILTERED: " + filtered);
            postNodeEntity.setComments(filtered);
            postNodeEntity.getComments().remove(commentNodeEntity);
            System.out.println("AFTER: " + postNodeEntity);
            postDao.save(postNodeEntity);
            return dto;
        } catch (ConverterException e) {
            throw new ServiceException(e);
        }
    }
}
