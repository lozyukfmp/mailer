package by.samsolutions.dao.impl;

import org.springframework.stereotype.Repository;

import by.samsolutions.dao.UserProfileDao;
import by.samsolutions.entity.user.UserProfile;

@Repository
public class UserProfileDaoImpl extends GenericDaoImpl<UserProfile, String> implements UserProfileDao
{
}
