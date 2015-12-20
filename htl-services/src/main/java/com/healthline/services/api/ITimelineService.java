/**
 * 
 */
package com.healthline.services.api;

import java.io.InputStream;

import com.healthline.entity.Event;
import com.healthline.entity.Timeline;

/**
 * @author Aniket
 * 
 */
public interface ITimelineService
{

    void createTimeline(Long userId, String headline, String description);

    void addEventToTimeLine(Long timelineId, Event event, String fileName, InputStream fileData);

    Timeline getTimeline(Long userId);

    String deleteTimeline();

    String deleteEventFromTimeline();
}
