package by.samsolutions.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.samsolutions.dao.UserDao;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserRole;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.find(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(final Collection<UserRole> userRoles) {

        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    private org.springframework.security.core.userdetails.User
    buildUserForAuthentication(final User user, final List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

}
