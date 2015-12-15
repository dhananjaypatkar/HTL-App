/**
 * 
 */
package com.healthline.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.healthline.dao.api.IUserServiceDao;
import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public class UserServiceDaoImpl
        implements IUserServiceDao
{

    private JdbcTemplate jdbcTemplate;

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
        User user = null;
        try
        {
            this.jdbcTemplate.queryForObject("select * from htl_app.htl_user where user_id = ?", User.class, userId);
        }
        catch (EmptyResultDataAccessException e)
        {
        }
        return user;
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

    public JdbcTemplate getJdbcTemplate()
    {
        return this.jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

}
