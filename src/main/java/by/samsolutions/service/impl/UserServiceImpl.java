package by.samsolutions.service.impl;

import by.samsolutions.dao.UserDao;
import by.samsolutions.dto.UserDto;
import by.samsolutions.dto.UserProfileDto;
import by.samsolutions.entity.User;
import by.samsolutions.entity.UserProfile;
import by.samsolutions.entity.UserRole;
import by.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User findByUsername(final String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    public User createUserAccount(final UserDto accountDto) {

        if (userDao.findByUsername(accountDto.getUsername()) != null) {
            return null;
        }

        User user = new User();
        UserRole userRole = new UserRole();
        UserProfile userProfile = new UserProfile();

        userRole.setRole("ROLE_USER");
        userRole.setUser(user);
        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole);

        userProfile.setUsername(accountDto.getUsername());
        userProfile.setEmail(accountDto.getUserProfileDto().getEmail());
        userProfile.setFirstName(accountDto.getUserProfileDto().getFirstName());
        userProfile.setSecondName(accountDto.getUserProfileDto().getSecondName());
        userProfile.setThirdName(accountDto.getUserProfileDto().getThirdName());
        //userProfile.setUser(user);

        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setUserRole(userRoleSet);
        user.setEnabled(true);
        user.setProfile(userProfile);

        return userDao.saveUser(user);
    }

    @Override
    @Transactional
    public UserProfile getUserProfileInfo(final String username) {
        return userDao.getUserProfile(username);
    }

    @Override
    @Transactional
    public void saveUserProfileInfo(final UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(userProfileDto.getUsername());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setSecondName(userProfileDto.getSecondName());
        userProfile.setThirdName(userProfileDto.getThirdName());

        userDao.saveUserProfile(userProfile);
    }
}
