/**
 * 
 */
package com.healthline.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.healthline.dao.api.ITimelineServiceDao;
import com.healthline.entity.Event;
import com.healthline.entity.Timeline;

/**
 * @author aniketd
 *
 */
public class TimelineServiceDaoImpl
        implements ITimelineServiceDao
{

    private JdbcTemplate jdbcTemplate;

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#createTimeline(com.healthline.entity.Timeline)
     */
    @Override
    public void createTimeline(Timeline timeline)
    {

    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getTimeline(java.lang.String)
     */
    @Override
    public Timeline getTimeline(String userId)
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#addEventToTimeline(java.lang.String, com.healthline.entity.Event)
     */
    @Override
    public boolean addEventToTimeline(String userId, Event event)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteTimeline(java.lang.String)
     */
    @Override
    public boolean deleteTimeline(String userId)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteEventFromTimeline(java.lang.String, long)
     */
    @Override
    public boolean deleteEventFromTimeline(String userId, long eventId)
    {
        return false;
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
