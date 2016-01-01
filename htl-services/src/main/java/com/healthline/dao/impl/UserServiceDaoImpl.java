/**
 * 
 */
package com.healthline.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.healthline.dao.api.IUserServiceDao;
import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public class UserServiceDaoImpl
        implements IUserServiceDao
{

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "dbQueries")
    private Properties                 dbQueries;

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.dao.api.IUserServiceDao#createNewUser(com.healthline.entity
     * .User)
     */
    @Override
    public User createNewUser(final User user)
    {
        // create.user=insert into htl_app.htl_user(fullname, email, last_login_date, city, region) values (:fullname, :email, :last_login_date, :city, :region)
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("fullname", user.getFullname());
        params.addValue("email", user.getEmail());
        params.addValue("last_login_date", new Timestamp(new DateTime().getMillis()));
        params.addValue("city", user.getCity());
        params.addValue("region", user.getRegion());

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(this.dbQueries.getProperty("create.user"), params, keyHolder);
        user.setId(Long.valueOf(keyHolder.getKeys().get("id").toString()));
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
            // select.user.by.email=select * from htl_app.htl_user where email = :email
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("email", email);
            user = this.jdbcTemplate.queryForObject(this.dbQueries.getProperty("select.user.by.email"), params,
                    new RowMapper<User>()
                    {
                        @Override
                        public User mapRow(ResultSet res, int col)
                                throws SQLException
                        {
                            User result = new User();
                            result.setId(new Long(res.getString("id")));
                            result.setFullname(res.getString("fullname"));
                            result.setEmail(res.getString("email"));
                            result.setLastLoginDate(new DateTime(res.getTimestamp("last_login_date")));
                            result.setCity(res.getString("city"));
                            result.setRegion(res.getString("region"));
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
    public NamedParameterJdbcTemplate getJdbcTemplate()
    {
        return this.jdbcTemplate;
    }

    /**
     * 
     * @param jdbcTemplate the jdbcTemplate to set
     */
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate)
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
