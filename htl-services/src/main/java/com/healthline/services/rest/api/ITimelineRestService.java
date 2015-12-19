/**
 * 
 */
package com.healthline.services.rest.api;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

/**
 * @author Aniket
 * 
 */
public interface ITimelineRestService
{

    String createTimeline(String userId, String headline, String description);

    String addEventToTimeLine(FormDataMultiPart form);

    String getTimeline(String userId);

    String deleteTimeline(String userId);

    String deleteEventFromTimeline(String eventId);
}
