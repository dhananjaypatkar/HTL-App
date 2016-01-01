/**
 * 
 */
package com.healthline.services.api;

import java.io.FileInputStream;

import com.healthline.entity.Event;
import com.healthline.entity.Timeline;

/**
 * @author Aniket
 * 
 */
public interface ITimelineService
{

    void createTimeline(Long userId, String headline, String description);

    void addEventToTimeLine(Long userId, Long timelineId, Event event, String fileName, byte[] content);

    Timeline getTimeline(Long userId);

    boolean deleteTimeline();

    boolean deleteEventFromTimeline(Long eventId);
}
