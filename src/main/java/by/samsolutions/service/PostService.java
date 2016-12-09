package by.samsolutions.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import by.samsolutions.dto.PostDto;
import by.samsolutions.entity.PostEntity;
import by.samsolutions.service.exception.ServiceException;

@Service
public interface PostService extends GenericService<PostDto, PostEntity, Integer>
{

	Collection<PostDto> getAll(String username, Integer messageCount) throws ServiceException;

}
