package by.samsolutions.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.UserEntity;

@Repository
public class UserDaoImpl extends GenericDaoImpl<UserEntity, String> implements UserDao
{

	public UserDaoImpl()
	{
		super(UserEntity.class);
	}

	@Override
	public List<UserEntity> all()
	{
		return entityManager.createNamedQuery("User.findAll", UserEntity.class)
		             .getResultList();
	}
}
