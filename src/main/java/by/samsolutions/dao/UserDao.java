package by.samsolutions.dao;

import java.util.Collection;

import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.user.UserEntity;

public interface UserDao extends GenericDao<UserEntity, String>
{
	Collection<UserEntity> getAll(Integer userCount) throws DaoException;
	UserEntity getByUsernameWithProfile(String username) throws DaoException;
	void setUserEnabled(String username, Boolean enabled) throws DaoException;
}
