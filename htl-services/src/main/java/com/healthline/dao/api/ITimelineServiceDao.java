/**
 * 
 */
package com.healthline.dao.api;

import java.util.List;
import java.util.Map;

import com.healthline.entity.Event;
import com.healthline.entity.Media;
import com.healthline.entity.Timeline;

/**
 * @author Aniket
 * 
 */
public interface ITimelineServiceDao
{
    void createTimeline(Long userId, Timeline timeline);

    Timeline getTimeline(Long userId);

    void addEventToTimeline(Long timelineId, Event event);

    boolean deleteTimeline(Long userId);

    boolean deleteEventFromTimeline(Long eventId);

    List<Event> getEventsOnTimeline(Long timelineId);

    Map<Long, Media> getMediaForEvents(List<Long> events);
}
