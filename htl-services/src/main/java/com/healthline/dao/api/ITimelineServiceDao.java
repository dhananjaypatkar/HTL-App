/**
 * 
 */
package com.healthline.dao.api;

import java.math.BigInteger;
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
    void createTimeline(BigInteger userId, Timeline timeline);

    Timeline getTimeline(BigInteger userId);

    void addEventToTimeline(BigInteger timelineId, Event event);

    boolean deleteTimeline(BigInteger userId);

    boolean deleteEventFromTimeline(BigInteger eventId);

    List<Event> getEventsOnTimeline(BigInteger timelineId);

    Map<BigInteger, Media> getMediaForEvents(List<BigInteger> events);
}
