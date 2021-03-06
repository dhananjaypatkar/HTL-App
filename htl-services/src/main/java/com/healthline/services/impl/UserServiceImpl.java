/**
 * 
 */
package com.healthline.services.impl;

import com.healthline.dao.api.IUserServiceDao;
import com.healthline.entity.User;
import com.healthline.services.api.IUserService;

/**
 * @author Aniket
 * 
 */
public class UserServiceImpl
        implements IUserService
{

    private IUserServiceDao userServiceDao;

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.IUserService#createNewUser(com.healthline
     * .entity.User)
     */
    @Override
    public User createNewUser(User user)
    {
        this.userServiceDao.createNewUser(user);
        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.IUserService#getUser(java.lang.String)
     */
    @Override
    public User getUser(String email)
    {
        User user = this.userServiceDao.getUser(email);
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
    public boolean deleteUser(String email)
    {
        this.userServiceDao.deleteUser(email);
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
