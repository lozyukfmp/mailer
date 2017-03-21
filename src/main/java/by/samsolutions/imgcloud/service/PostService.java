package by.samsolutions.imgcloud.service;

import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.nodeentity.PostNodeEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface PostService extends GenericService<PostDto, PostNodeEntity, Long>
{

	Collection<PostDto> getAll(String username, Integer messageCount) throws ServiceException;

}
