package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, String> implements UserDao
{

	public UserDaoImpl()
	{
		super(User.class);
	}

	@Override
	public List<User> all()
	{
		return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
	}
}
