/**
 * 
 */
package com.healthline.services.rest.api;


/**
 * @author Aniket
 * 
 */
public interface ITimelineRestService
{

    String createTimeline();

    String addEventToTimeLine();

    String getTimeline();

    String deleteTimeline();

    String deleteEventFromTimeline();
}
