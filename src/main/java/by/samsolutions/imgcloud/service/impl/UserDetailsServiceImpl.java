package by.samsolutions.imgcloud.service.impl;

import by.samsolutions.imgcloud.dao.UserDao;
import by.samsolutions.imgcloud.nodeentity.user.UserNodeEntity;
import by.samsolutions.imgcloud.nodeentity.user.UserRoleNodeEntity;
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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
			UserNodeEntity user = userDao.findByUsername(username);

			if (user == null)
			{
				throw new UsernameNotFoundException("UserEntity not found!");
			}

			List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

			return buildUserForAuthentication(user, authorities);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private List<GrantedAuthority> buildUserAuthority(final Collection<UserRoleNodeEntity> userRoles)
	{

		return userRoles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	private org.springframework.security.core.userdetails.User buildUserForAuthentication(final UserNodeEntity user,
	                                                                                      final List<GrantedAuthority> authorities)
	{
		return new org.springframework.security.core.userdetails.
						User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

}
