package by.samsolutions.imgcloud.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import by.samsolutions.imgcloud.dto.PostDto;
import by.samsolutions.imgcloud.entity.PostEntity;
import by.samsolutions.imgcloud.service.exception.ServiceException;

@Service
public interface PostService extends GenericService<PostDto, PostEntity, Integer>
{

	Collection<PostDto> getAll(String username, Integer messageCount) throws ServiceException;

}
