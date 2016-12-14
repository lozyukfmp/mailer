package by.samsolutions.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.UserDao;
import by.samsolutions.dao.exception.DaoException;
import by.samsolutions.entity.user.UserEntity;
import by.samsolutions.entity.user.UserRoleEntity;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{

	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		logger.trace("GETTING USER WITH USERNAME = " + username);
		try
		{
			UserEntity user = userDao.find(username);

			if (user == null)
			{
				throw new UsernameNotFoundException("UserEntity not found!");
			}

			List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

			return buildUserForAuthentication(user, authorities);
		}
		catch (DaoException e)
		{
			logger.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private List<GrantedAuthority> buildUserAuthority(final Collection<UserRoleEntity> userRoles)
	{

		return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(final UserEntity user,
	                                                                                      final List<GrantedAuthority> authorities)
	{
		return new org.springframework.security.core.userdetails.
						User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

}
