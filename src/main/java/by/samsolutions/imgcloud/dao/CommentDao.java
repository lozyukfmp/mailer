package by.samsolutions.imgcloud.dao;

import java.util.List;

import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.CommentEntity;

public interface CommentDao extends GenericDao<CommentEntity, Integer>
{
	List<CommentEntity> findAllByPostId(Integer postId, Integer commentIndex) throws DaoException;
}
