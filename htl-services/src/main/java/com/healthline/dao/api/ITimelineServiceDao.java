/**
 * 
 */
package com.healthline.dao.api;

import com.healthline.entity.Event;
import com.healthline.entity.Timeline;

/**
 * @author Aniket
 * 
 */
public interface ITimelineServiceDao
{
    void createTimeline(Timeline timeline);

    Timeline getTimeline(String userId);

    boolean addEventToTimeline(String userId, Event event);

    boolean deleteTimeline(String userId);

    boolean deleteEventFromTimeline(String userId, long eventId);
}
