/**
 * 
 */
package com.healthline.services.rest.api;

import javax.ws.rs.core.Response;


/**
 * @author Aniket
 *
 */
public interface ITimelineRestService {

	Response createTimeline();
	
	Response addEventToTimeLine();
	
	Response getTimeline();
	
	Response deleteTimeline();
	
}
