/**
 * 
 */
package com.healthline.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.healthline.dao.api.ITimelineServiceDao;
import com.healthline.entity.Description;
import com.healthline.entity.Event;
import com.healthline.entity.EventDate;
import com.healthline.entity.Media;
import com.healthline.entity.Timeline;
import com.healthline.entity.Title;

/**
 * @author aniketd
 * 
 */
public class TimelineServiceDaoImpl
        implements ITimelineServiceDao
{

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dbQueries")
    private Properties   dbQueries;

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#createTimeline(com.healthline.entity.Timeline)
     */
    @Override
    public void createTimeline(final BigInteger userId, final Timeline timeline)
    {
        // create.timeline.for.user=insert into htl_app.htl_timeline(description, user_id) values (?, ?)
        final String query = this.dbQueries.getProperty("create.timeline.for.user");
        final PreparedStatementCreator psc = getInsertTimelinePreparedStatement(userId, timeline, query);
        // create timeline
        this.jdbcTemplate.update(psc);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getTimeline(java.lang.String)
     */
    @Override
    public Timeline getTimeline(BigInteger userId)
    {
        Timeline timeline = null;
        try
        {
            // select.timeline.by.user.id=select * from htl_app.htl_timeline where user_id = ?
            timeline = this.jdbcTemplate.queryForObject(this.dbQueries.getProperty("select.timeline.by.user.id"),
                    new Object[]
                    {
                        userId.longValue()
                    }, new RowMapper<Timeline>()
                    {

                        @Override
                        public Timeline mapRow(ResultSet res, int col)
                                throws SQLException
                        {
                            Timeline result = new Timeline();
                            Title title = new Title();
                            result.setId(new BigInteger(res.getString("id")));
                            title.setText(new Description(res.getString("description"), null));
                            result.setTitle(title);
                            return result;
                        }

                    });
            List<Event> events = getEventsOnTimeline(timeline.getId());
            timeline.setEvents(events);
            List<BigInteger> eventIds = new ArrayList<>();

            for (Event event : events)
            {
                eventIds.add(event.getId());
            }
            // get media info for each event
            Map<BigInteger, Media> eventToMedia = getMediaForEvents(eventIds);
            if ( !eventToMedia.isEmpty() )
            {
                for (Event event : events)
                {
                    event.setMedia(eventToMedia.get(event.getId()));
                }
            }
        }
        catch (EmptyResultDataAccessException e)
        {
            // do nothing
        }

        return timeline;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#addEventToTimeline(java.lang.String, com.healthline.entity.Event)
     */
    @Override
    public void addEventToTimeline(final BigInteger timelineId, final Event event)
    {
        // create.event.on.timeline=insert into htl_app.htl_event(description, start_date, end_date, timeline_id) value (?, ?, ?, ?)
        final String eventQuery = this.dbQueries.getProperty("create.event.on.timeline");
        final PreparedStatementCreator eventPsc = getInsertEventPreparedStatement(timelineId, event, eventQuery);
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        // create event
        this.jdbcTemplate.update(eventPsc, keyHolder);

        final Long eventId = Long.valueOf(keyHolder.getKeys().get("id").toString());

        // create.media.for.event=insert into htl_app.htl_media(url, event_id) values (?, ?)
        final String mediaQuery = this.dbQueries.getProperty("create.media.for.event");
        final PreparedStatementCreator mediaPsc = getInsertMediaPreparedStatement(event, eventId, mediaQuery);

        // insert media
        this.jdbcTemplate.update(mediaPsc);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteTimeline(java.lang.String)
     */
    @Override
    public boolean deleteTimeline(BigInteger userId)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteEventFromTimeline(java.lang.String, long)
     */
    @Override
    public boolean deleteEventFromTimeline(BigInteger eventId)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getEventsOnTimeline(java.math.BigInteger)
     */
    @Override
    public List<Event> getEventsOnTimeline(BigInteger timelineId)
    {
        List<Event> events = this.jdbcTemplate.query(this.dbQueries.getProperty("select.event.by.timeline.id"),
                new Object[]
                {
                    timelineId.longValue()
                }, new RowMapper<Event>()
                {

                    @Override
                    public Event mapRow(ResultSet res, int col)
                            throws SQLException
                    {
                        Event event = new Event();
                        event.setId(new BigInteger(res.getString("id")));
                        event.setText(new Description(null, res.getString("description")));
                        DateTime eventDate = new DateTime(res.getTimestamp("start_date"));
                        event.setEventDate(new EventDate(eventDate.getYear(), eventDate.getMonthOfYear(), eventDate
                                .getDayOfMonth()));
                        return event;
                    }
                });
        return events;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getMediaForEvents(java.util.List)
     */
    @Override
    public Map<BigInteger, Media> getMediaForEvents(List<BigInteger> events)
    {
        final Map<BigInteger, Media> result = new HashMap<>();
        // won't happen
        if(events.isEmpty())
        {
            // no need to go to DB
            return result;
        }
        
        String params = "";
        int i = 0;
        for(;i < events.size()-1; i++)
        {
            params = params + events.get(i).longValue() + ",";
        }
        params = params + events.get(i).longValue();
        
        this.jdbcTemplate.query(this.dbQueries.getProperty("select.media.by.event.ids").replace(":eventIds", params),new RowMapper<Media>()
        {
            @Override
            public Media mapRow(ResultSet res, int col)
                    throws SQLException
            {
                Media media = new Media();
                media.setId(new BigInteger(res.getString("id")));
                media.setUrl(res.getString("url"));
                result.put(new BigInteger(res.getString("event_id")), media);
                return media;
            }

        });

        return result;
    }

    /**
     * @param timelineId
     * @param event
     * @param query
     * @return
     */
    private PreparedStatementCreator getInsertEventPreparedStatement(final BigInteger timelineId, final Event event,
            final String query)
    {
        return new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con)
                    throws SQLException
            {
                PreparedStatement ps = null;
                try
                {
                    ps = con.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
                    int paramIndex = 0;
                    ps.setString(++paramIndex, event.getText().getText());
                    ps.setTimestamp(++paramIndex, new Timestamp(event.getStartDate().getMillis()));
                    ps.setTimestamp(++paramIndex, new Timestamp(event.getEndDate().getMillis()));
                    ps.setLong(++paramIndex, timelineId.longValue());
                }
                catch (SQLException exception)
                {
                    if ( ps != null )
                    {
                        ps.close();
                    }
                    throw exception;
                }
                return ps;
            }
        };
    }

    /**
     * @param event
     * @param eventId
     * @param mediaQuery
     * @return
     */
    private PreparedStatementCreator getInsertMediaPreparedStatement(final Event event, final Long eventId,
            final String mediaQuery)
    {
        return new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con)
                    throws SQLException
            {
                PreparedStatement ps = null;
                try
                {
                    ps = con.prepareStatement(mediaQuery);
                    int paramIndex = 0;
                    ps.setString(++paramIndex, event.getMedia().getUrl());
                    ps.setLong(++paramIndex, eventId);
                }
                catch (SQLException exception)
                {
                    if ( ps != null )
                    {
                        ps.close();
                    }
                    throw exception;
                }
                return ps;
            }
        };
    }

    /**
     * @param userId
     * @param timeline
     * @param query
     * @return
     */
    private PreparedStatementCreator getInsertTimelinePreparedStatement(final BigInteger userId,
            final Timeline timeline, final String query)
    {
        return new PreparedStatementCreator()
        {
            @Override
            public PreparedStatement createPreparedStatement(final Connection con)
                    throws SQLException
            {
                PreparedStatement ps = null;
                try
                {
                    ps = con.prepareStatement(query);
                    int paramIndex = 0;
                    ps.setString(++paramIndex, timeline.getTitle().getText().getHeadline());
                    ps.setLong(++paramIndex, userId.longValue());
                }
                catch (SQLException exception)
                {
                    if ( ps != null )
                    {
                        ps.close();
                    }
                    throw exception;
                }
                return ps;
            }
        };
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
