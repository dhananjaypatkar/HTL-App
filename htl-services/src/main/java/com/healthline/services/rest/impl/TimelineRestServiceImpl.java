/**
 * 
 */
package com.healthline.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.healthline.services.rest.api.ITimelineRestService;

/**
 * @author Aniket
 *
 */

@Path("/timeline")
@Produces({ "application/json" })
public class TimelineRestServiceImpl implements ITimelineRestService {

	/* (non-Javadoc)
	 * @see com.healthline.services.rest.api.ITimelineRestService#createTimeline()
	 */
	@Path("/create")
	@GET
	@Override
	public String createTimeline() {
		return "{ \"success\" : \"true\" }";
	}

	/* (non-Javadoc)
	 * @see com.healthline.services.rest.api.ITimelineRestService#addEventToTimeLine()
	 */
	@Path("/add")
	@GET
	@Override
	public String addEventToTimeLine() {
		return "{ \"success\" : \"true\" }";
	}

	/* (non-Javadoc)
	 * @see com.healthline.services.rest.api.ITimelineRestService#getTimeline()
	 */
	@Path("/get")
	@GET
	@Override
	public String getTimeline() {
		return "{\"result\" : []}";
	}

	/* (non-Javadoc)
	 * @see com.healthline.services.rest.api.ITimelineRestService#deleteTimeline()
	 */
	@Path("/delete")
	@GET
	@Override
	public String deleteTimeline() {
		return "{\"success\" : \"true\"}";
	}

}
