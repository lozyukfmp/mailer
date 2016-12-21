package by.samsolutions.imgcloud.dao;

import java.util.Collection;

import by.samsolutions.imgcloud.dao.exception.DaoException;
import by.samsolutions.imgcloud.entity.user.UserEntity;

public interface UserDao extends GenericDao<UserEntity, String>
{
	Collection<UserEntity> getAll(Integer userCount) throws DaoException;

	Collection<UserEntity> getByUsernameWithProfile(String username) throws DaoException;

	void setUserEnabled(String username, Boolean enabled) throws DaoException;
}
