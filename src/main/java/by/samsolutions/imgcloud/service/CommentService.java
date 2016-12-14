package by.samsolutions.imgcloud.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import by.samsolutions.imgcloud.dto.CommentDto;
import by.samsolutions.imgcloud.entity.CommentEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public interface CommentService extends GenericService<CommentDto, CommentEntity, Integer>
{
	Collection<CommentDto> getCommentListByPostId(Integer postId, Integer commentIndex) throws ServiceException;
}
