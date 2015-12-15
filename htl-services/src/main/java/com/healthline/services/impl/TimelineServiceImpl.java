package com.healthline.services.impl;

import com.healthline.dao.api.ITimelineServiceDao;
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
    public String createTimeline()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#addEventToTimeLine()
     */
    @Override
    public String addEventToTimeLine()
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#getTimeline()
     */
    @Override
    public String getTimeline()
    {
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    public ITimelineServiceDao getTimelineServiceDao()
    {
        return timelineServiceDao;
    }

    public void setTimelineServiceDao(ITimelineServiceDao timelineServiceDao)
    {
        this.timelineServiceDao = timelineServiceDao;
    }

}
