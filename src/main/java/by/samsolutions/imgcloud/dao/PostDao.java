package by.samsolutions.imgcloud.dao;

import java.util.List;

import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.PostEntity;

public interface PostDao extends GenericDao<PostEntity, Integer>
{
	List<PostEntity> all(String username, Integer messageCount) throws DaoException;

	PostEntity findWithComments(Integer postId) throws DaoException;
}
