package by.samsolutions.dao.impl;

import java.util.List;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserRole;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao
{
	@Override
	public List<User> all()
	{
		return entityManager.createNamedQuery("User.findAll").getResultList();
	}

	@Override
	public User findByUsername(final String username)
	{
		return (User) entityManager.createNamedQuery("User.findByUsername")
		                           .setParameter("username", username).getSingleResult();
	}

	@Override
	public User findWithProfile(final String username)
	{
		return (User) entityManager.createNamedQuery("User.findWithProfile")
		                           .setParameter("username", username).getSingleResult();
	}

	@Override
	public User findWithRoles(final String username)
	{
		return (User) entityManager.createNamedQuery("User.findWithRoles")
		                           .setParameter("username", username).getSingleResult();
	}
}