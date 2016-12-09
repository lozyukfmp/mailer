package by.samsolutions.dao;

import java.util.List;

import by.samsolutions.entity.PostEntity;

public interface PostDao extends GenericDao<PostEntity, Integer>
{
	List<PostEntity> all(String username, Integer messageCount);
	PostEntity findWithComments(Integer postId);
}
