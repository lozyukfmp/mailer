package by.samsolutions.service.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.entity.user.User;
import by.samsolutions.entity.user.UserRole;
import by.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User createUserAccount(UserDto accountDto) {

        if (userDao.findByUsername(accountDto.getUsername()) != null) {
            return null;
        }

        User user = new User();
        UserRole userRole = new UserRole();
        userRole.setRole("ROLE_USER");
        userRole.setUser(user);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setUserRole(userRoleSet);
        user.setEnabled(true);

        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUserProfileInfo(String username) {
        return null;
    }
}
