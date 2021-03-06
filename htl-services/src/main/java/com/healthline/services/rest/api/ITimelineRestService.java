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

    String getTimeline(Long userId);

    String deleteTimeline(Long userId);

    String deleteEventFromTimeline(Long eventId);
}
