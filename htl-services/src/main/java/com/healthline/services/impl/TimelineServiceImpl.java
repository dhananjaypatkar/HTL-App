package com.healthline.services.impl;

import java.util.UUID;

import com.healthline.dao.api.ITimelineServiceDao;
import com.healthline.entity.Description;
import com.healthline.entity.Event;
import com.healthline.entity.Media;
import com.healthline.entity.Timeline;
import com.healthline.entity.Title;
import com.healthline.services.api.ITimelineService;
import com.healthline.storage.api.IDocumentStorageGateway;
import com.healthline.storage.api.ISecuredDocumentStorageGateway;

/**
 * @author Aniket
 * 
 */
public class TimelineServiceImpl
        implements ITimelineService
{

    private ITimelineServiceDao     timelineServiceDao;
    private ISecuredDocumentStorageGateway documentStorageGateway;

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
            String passcode = UUID.randomUUID().toString();
            String newFileName = userId + "/" + generateFileName(fileName, passcode);
            String mediUrl = this.documentStorageGateway.encryptAndStoreFile(newFileName, content, passcode);
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
    
    private String generateFileName(String oldFileName, String passcode)
    {
        String newFileName = "";
        int dot = oldFileName.lastIndexOf(".");
        String name = oldFileName.substring(0, dot);
        String extension = oldFileName.substring(dot);
        newFileName = name + "_" + passcode + extension;
        return newFileName;
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
    public void setDocumentStorageGateway(ISecuredDocumentStorageGateway documentStorageGateway)
    {
        this.documentStorageGateway = documentStorageGateway;
    }

}
