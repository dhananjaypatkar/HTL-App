package com.healthline.services.impl;

import com.healthline.dao.api.ITimelineServiceDao;
import com.healthline.entity.Description;
import com.healthline.entity.Event;
import com.healthline.entity.Media;
import com.healthline.entity.Timeline;
import com.healthline.entity.Title;
import com.healthline.services.api.ITimelineService;
import com.healthline.storage.api.IDocumentStorageGateway;

/**
 * @author Aniket
 * 
 */
public class TimelineServiceImpl
        implements ITimelineService
{

    private ITimelineServiceDao timelineServiceDao;
    private IDocumentStorageGateway documentStorageGateway;

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#createTimeline()
     */
    @Override
    public void createTimeline(Long userId, String headline, String description)
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
    public void addEventToTimeLine(Long userId, Long timelineId, Event event, String fileName, byte[] content)
    {
        Media media = new Media();
        if ( content != null )
        {
            String newFileName = userId + "/" + fileName;
            String mediUrl = this.documentStorageGateway.storeFile(newFileName, content);
            media.setUrl(mediUrl);
        }
        event.setMedia(media);
        this.timelineServiceDao.addEventToTimeline(timelineId, event);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#getTimeline()
     */
    @Override
    public Timeline getTimeline(Long userId)
    {
        return this.timelineServiceDao.getTimeline(userId);
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.api.ITimelineService#deleteTimeline()
     */
    @Override
    public boolean deleteTimeline()
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.api.ITimelineService#deleteEventFromTimeline()
     */
    @Override
    public boolean deleteEventFromTimeline(Long eventId)
    {
        return this.timelineServiceDao.deleteEventFromTimeline(eventId);
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

    /**
     * @return the documentStorageGateway
     */
    public IDocumentStorageGateway getDocumentStorageGateway()
    {
        return this.documentStorageGateway;
    }

    /**
     * @param documentStorageGateway the documentStorageGateway to set
     */
    public void setDocumentStorageGateway(IDocumentStorageGateway documentStorageGateway)
    {
        this.documentStorageGateway = documentStorageGateway;
    }
}
