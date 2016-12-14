package by.samsolutions.dao;

import java.util.List;

import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.PostEntity;

public interface PostDao extends GenericDao<PostEntity, Integer>
{
	List<PostEntity> all(String username, Integer messageCount) throws DaoException;

	PostEntity findWithComments(Integer postId) throws DaoException;
}
