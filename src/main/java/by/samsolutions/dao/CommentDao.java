package by.samsolutions.dao;

import java.util.List;

import by.samsolutions.entity.CommentEntity;

public interface CommentDao extends GenericDao<CommentEntity, Integer>
{
	List<CommentEntity> findAllByPostId(Integer postId, Integer commentIndex);
}
