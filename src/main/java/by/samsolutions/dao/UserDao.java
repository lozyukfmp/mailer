package by.samsolutions.dao;

import javax.persistence.NoResultException;

import by.samsolutions.entity.user.User;

public interface UserDao extends GenericDao<User, String>
{
	User findByUsername(String username);

	User findWithProfile(String username);

	User findWithRoles(String username);

	User findWithProfileAndPosts(String username) throws NoResultException;
}
