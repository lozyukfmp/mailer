package by.samsolutions.dao;

import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserProfile;
import by.samsolutions.entity.user.UserRole;

public interface UserDao extends GenericDao<User, String>
{
	User findByUsername(String username);

	User findWithProfile(String username);

	User findWithRoles(String username);
}
