/**
 * 
 */
package com.healthline.services.api;

import java.io.InputStream;
import java.math.BigInteger;

import com.healthline.entity.Event;
import com.healthline.entity.Timeline;

/**
 * @author Aniket
 * 
 */
public interface ITimelineService
{

    void createTimeline(BigInteger userId, String headline, String description);

    void addEventToTimeLine(BigInteger timelineId, Event event, String fileName, InputStream fileData);

    Timeline getTimeline(BigInteger userId);

    String deleteTimeline();

    String deleteEventFromTimeline();
}
