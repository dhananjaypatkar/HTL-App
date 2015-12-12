/**
 * 
 */
package com.healthline.services.impl;

import java.util.UUID;

import com.healthline.dao.api.IUserServiceDao;
import com.healthline.dao.impl.UserServiceDaoImpl;
import com.healthline.entity.User;
import com.healthline.services.api.IUserService;

/**
 * @author Aniket
 * 
 */
public class UserServiceImpl
        implements IUserService
{

    private IUserServiceDao userServiceDao = new UserServiceDaoImpl();

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.IUserService#createNewUser(com.healthline
     * .entity.User)
     */
    @Override
    public User createNewUser(User user)
    {
        user.setUserId(UUID.randomUUID().toString());
        this.userServiceDao.createNewUser(user);
        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.IUserService#getUser(java.lang.String)
     */
    @Override
    public User getUser(String userId)
    {
        User user = this.userServiceDao.getUser(userId);
        return user;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.IUserService#updateUser(com.healthline.entity
     * .User)
     */
    @Override
    public User updateUser(User user)
    {
        this.userServiceDao.updateUser(user);
        return user;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.IUserService#deleteUser(java.lang.String)
     */
    @Override
    public boolean deleteUser(String userId)
    {
        this.userServiceDao.deleteUser(userId);
        return false;
    }

    /**
     * @return the userServiceDao
     */
    public IUserServiceDao getUserServiceDao()
    {
        return this.userServiceDao;
    }

    /**
     * @param userServiceDao the userServiceDao to set
     */
    public void setUserServiceDao(IUserServiceDao userServiceDao)
    {
        this.userServiceDao = userServiceDao;
    }

}
