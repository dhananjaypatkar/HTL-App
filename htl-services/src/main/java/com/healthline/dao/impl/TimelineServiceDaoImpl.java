/**
 * 
 */
package com.healthline.dao.impl;

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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "dbQueries")
    private Properties                 dbQueries;

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#createTimeline(com.healthline.entity.Timeline)
     */
    @Override
    public void createTimeline(final Long userId, final Timeline timeline)
    {
        // create.timeline.for.user=insert into htl_app.htl_timeline(description, user_id) values (:description, :user_id)
        final String query = this.dbQueries.getProperty("create.timeline.for.user");

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("description", timeline.getTitle().getText().getHeadline());
        params.addValue("user_id", userId);

        // create timeline
        this.jdbcTemplate.update(query, params);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getTimeline(java.lang.String)
     */
    @Override
    public Timeline getTimeline(Long userId)
    {
        Timeline timeline = null;
        try
        {
            // select.timeline.by.user.id=select * from htl_app.htl_timeline where user_id = :user_id
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("user_id", userId);
            timeline = this.jdbcTemplate.queryForObject(this.dbQueries.getProperty("select.timeline.by.user.id"),
                    params, new RowMapper<Timeline>()
                    {

                        @Override
                        public Timeline mapRow(ResultSet res, int col)
                                throws SQLException
                        {
                            Timeline result = new Timeline();
                            Title title = new Title();
                            result.setId(new Long(res.getString("id")));
                            title.setText(new Description(res.getString("description"), null));
                            result.setTitle(title);
                            return result;
                        }

                    });
            List<Event> events = getEventsOnTimeline(timeline.getId());
            timeline.setEvents(events);
            List<Long> eventIds = new ArrayList<>();

            for (Event event : events)
            {
                eventIds.add(event.getId());
            }
            // get media info for each event
            Map<Long, Media> eventToMedia = getMediaForEvents(eventIds);
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
    public void addEventToTimeline(final Long timelineId, final Event event)
    {
        // create.event.on.timeline=insert into htl_app.htl_event(description, start_date, end_date, timeline_id) values (:description, :start_date, :end_date,
        // :timeline_id)
        final String eventQuery = this.dbQueries.getProperty("create.event.on.timeline");

        MapSqlParameterSource eventParams = new MapSqlParameterSource();
        eventParams.addValue("description", event.getText().getText());
        eventParams.addValue("start_date", new Timestamp(event.getStartDate().getMillis()));
        eventParams.addValue("end_date", new Timestamp(event.getEndDate().getMillis()));
        eventParams.addValue("timeline_id", timelineId);
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        // create event
        this.jdbcTemplate.update(eventQuery, eventParams, keyHolder);

        final Long eventId = Long.valueOf(keyHolder.getKeys().get("id").toString());

        // create.media.for.event=insert into htl_app.htl_media(url, event_id) values (:url, :event_id)
        final String mediaQuery = this.dbQueries.getProperty("create.media.for.event");

        MapSqlParameterSource mediaParams = new MapSqlParameterSource();
        mediaParams.addValue("url", event.getMedia().getUrl());
        mediaParams.addValue("event_id", eventId);
        // insert media
        this.jdbcTemplate.update(mediaQuery, mediaParams);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteTimeline(java.lang.String)
     */
    @Override
    public boolean deleteTimeline(Long userId)
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#deleteEventFromTimeline(java.lang.String, long)
     */
    @Override
    public boolean deleteEventFromTimeline(Long eventId)
    {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("event_id", eventId);
        this.jdbcTemplate.update(this.dbQueries.getProperty("delete.media.of.event"), params);
        return this.jdbcTemplate.update(this.dbQueries.getProperty("delete.event.on.timeline"), params) != 0;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.dao.api.ITimelineServiceDao#getEventsOnTimeline(java.math.Long)
     */
    @Override
    public List<Event> getEventsOnTimeline(Long timelineId)
    {
        // select.event.by.timeline.id=select * from htl_app.htl_event where timeline_id = :timeline_id
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("timeline_id", timelineId);
        List<Event> events = this.jdbcTemplate.query(this.dbQueries.getProperty("select.event.by.timeline.id"), params,
                new RowMapper<Event>()
                {

                    @Override
                    public Event mapRow(ResultSet res, int col)
                            throws SQLException
                    {
                        Event event = new Event();
                        event.setId(new Long(res.getString("id")));
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
    public Map<Long, Media> getMediaForEvents(List<Long> events)
    {
        final Map<Long, Media> result = new HashMap<>();
        // won't happen
        if ( events.isEmpty() )
        {
            // no need to go to DB
            return result;
        }
        List<Long> eventIds = new ArrayList<>();
        for (Long eventId : events)
        {
            eventIds.add(eventId);
        }

        // select.media.by.event.ids=select * from htl_app.htl_media where event_id in (:event_ids)
        MapSqlParameterSource mapParams = new MapSqlParameterSource();
        mapParams.addValue("event_ids", eventIds);

        this.jdbcTemplate.query(this.dbQueries.getProperty("select.media.by.event.ids"), mapParams,
                new RowMapper<Media>()
                {
                    @Override
                    public Media mapRow(ResultSet res, int col)
                            throws SQLException
                    {
                        Media media = new Media();
                        media.setId(new Long(res.getString("id")));
                        media.setUrl(res.getString("url"));
                        result.put(new Long(res.getString("event_id")), media);
                        return media;
                    }

                });

        return result;
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
