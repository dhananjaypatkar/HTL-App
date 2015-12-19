/**
 * 
 */
package com.healthline.dao.impl;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

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

    @Resource(name = "dbQueries")
    private Properties   dbQueries;

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.dao.api.IUserServiceDao#createNewUser(com.healthline.entity
     * .User)
     */
    @Override
    public User createNewUser(final User user)
    {
        this.jdbcTemplate.execute(this.dbQueries.getProperty("insert.user"), new PreparedStatementCallback<Boolean>()
        {

            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException
            {
                // insert into htl_app.htl_user(fullname, email, last_login_date, city, region) values (?, ?, ?, ?, ?)
                ps.setString(1, user.getFullname());
                ps.setString(2, user.getEmail());
                ps.setTimestamp(3, new Timestamp(user.getLastLoginDate().getMillis()));
                ps.setString(4, user.getCity());
                ps.setString(5, user.getRegion());

                return ps.execute();
            }
        });

        return user;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.IUserServiceDao#getUser(java.lang.String)
     */
    @Override
    public User getUser(String email)
    {
        User user = null;
        try
        {
            user = this.jdbcTemplate.queryForObject(this.dbQueries.getProperty("select.user.by.email"), new Object[]
            {
                email
            }, new RowMapper<User>()
            {
                @Override
                public User mapRow(ResultSet res, int col)
                        throws SQLException
                {
                    User result = new User();
                    result.setId(new BigInteger(res.getString(1)));
                    result.setFullname(res.getString(2));
                    result.setEmail(res.getString(3));
                    result.setLastLoginDate(new DateTime(res.getTimestamp(4)));
                    result.setCity(res.getString(5));
                    result.setRegion(res.getString(6));
                    return result;
                }
            });
        }
        catch (EmptyResultDataAccessException e)
        {
            // do nothing
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
    public boolean deleteUser(String email)
    {
        return true;
    }

    /**
     * 
     * @return jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate()
    {
        return this.jdbcTemplate;
    }

    /**
     * 
     * @param jdbcTemplate the jdbcTemplate to set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @return the dbQueries
     */
    public Properties getDbQueries()
    {
        return this.dbQueries;
    }

    /**
     * @param dbQueries the dbQueries to set
     */
    public void setDbQueries(Properties dbQueries)
    {
        this.dbQueries = dbQueries;
    }

}
