/**
 * 
 */
package com.healthline.services.api;

/**
 * @author Aniket
 * 
 */
public interface ITimelineService
{

    String createTimeline();

    String addEventToTimeLine();

    String getTimeline();

    String deleteTimeline();

    String deleteEventFromTimeline();
}
