package by.samsolutions.dao.impl;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.UserRoleDao;
import by.samsolutions.entity.user.UserRole;

@Repository
public class UserRoleDaoImpl extends GenericDaoImpl<UserRole, Integer> implements UserRoleDao
{
}
