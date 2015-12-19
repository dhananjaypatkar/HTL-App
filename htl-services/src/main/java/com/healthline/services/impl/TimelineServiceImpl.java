package com.healthline.services.impl;

import java.io.InputStream;
import java.math.BigInteger;

import com.healthline.dao.api.ITimelineServiceDao;
import com.healthline.entity.Description;
import com.healthline.entity.Event;
import com.healthline.entity.Media;
import com.healthline.entity.Timeline;
import com.healthline.entity.Title;
import com.healthline.services.api.ITimelineService;

/**
 * @author Aniket
 * 
 */
public class TimelineServiceImpl
        implements ITimelineService
{

    private ITimelineServiceDao timelineServiceDao;

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#createTimeline()
     */
    @Override
    public void createTimeline(BigInteger userId, String headline, String description)
    {
        Title title = new Title();
        title.setText(new Description(headline, description));
        Timeline timeline = new Timeline();
        timeline.setTitle(title);
        this.timelineServiceDao.createTimeline(userId, timeline);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#addEventToTimeLine()
     */
    @Override
    public void addEventToTimeLine(BigInteger timelineId, Event event, String fileName, InputStream fileData)
    {
        Media media = new Media();
        if ( fileData != null )
        {
            // TODO upload filedata to some server(say azure) and set the appropriate url below
            media.setUrl(fileName);
        }
        event.setMedia(media);
        this.timelineServiceDao.addEventToTimeline(timelineId, event);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#getTimeline()
     */
    @Override
    public Timeline getTimeline(BigInteger userId)
    {
        return this.timelineServiceDao.getTimeline(userId);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#deleteTimeline()
     */
    @Override
    public String deleteTimeline()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.ITimelineService#deleteEventFromTimeline()
     */
    @Override
    public String deleteEventFromTimeline()
    {
        return null;
    }

    /**
     * @return timelineServiceDao
     */
    public ITimelineServiceDao getTimelineServiceDao()
    {
        return this.timelineServiceDao;
    }

    /**
     * @param timelineServiceDao the timelineServiceDao to set
     */
    public void setTimelineServiceDao(ITimelineServiceDao timelineServiceDao)
    {
        this.timelineServiceDao = timelineServiceDao;
    }
}
