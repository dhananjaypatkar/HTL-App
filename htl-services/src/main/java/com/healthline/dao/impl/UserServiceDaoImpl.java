/**
 * 
 */
package com.healthline.dao.impl;

import com.healthline.dao.api.IUserServiceDao;
import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public class UserServiceDaoImpl
        implements IUserServiceDao
{

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.dao.api.IUserServiceDao#createNewUser(com.healthline.entity
     * .User)
     */
    @Override
    public User createNewUser(User user)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.IUserServiceDao#getUser(java.lang.String)
     */
    @Override
    public User getUser(String userId)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.dao.api.IUserServiceDao#updateUser(com.healthline.entity
     * .User)
     */
    @Override
    public User updateUser(User user)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.IUserServiceDao#deleteUser(java.lang.String)
     */
    @Override
    public boolean deleteUser(String userId)
    {
        return true;
    }

}
