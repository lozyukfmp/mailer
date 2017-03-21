package by.samsolutions.imgcloud.service;

import by.samsolutions.imgcloud.dto.CommentDto;
import by.samsolutions.imgcloud.nodeentity.CommentNodeEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CommentService extends GenericService<CommentDto, CommentNodeEntity, Long>
{
	Collection<CommentDto> getCommentListByPostId(Long postId, Integer commentIndex) throws ServiceException;
}
