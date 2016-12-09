package by.samsolutions.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import by.samsolutions.dto.CommentDto;
import by.samsolutions.entity.CommentEntity;
import by.samsolutions.service.exception.ServiceException;

@Service
public interface CommentService extends GenericService<CommentDto, CommentEntity, Integer>
{
	Collection<CommentDto> getCommentListByPostId(Integer postId, Integer commentIndex) throws ServiceException;
}
