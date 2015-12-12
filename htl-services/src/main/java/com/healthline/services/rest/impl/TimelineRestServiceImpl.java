/**
 * 
 */
package com.healthline.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.healthline.common.RestServiceResponse;
import com.healthline.common.Status;
import com.healthline.services.rest.api.ITimelineRestService;

/**
 * @author Aniket
 * 
 */

@Path("/timeline")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineRestServiceImpl
        implements ITimelineRestService
{

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#createTimeline()
     */
    @Path("/create")
    @POST
    @Override
    public Response createTimeline()
    {
        return Response.ok(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE)).build();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#addEventToTimeLine
     * ()
     */
    @Path("/add")
    @POST
    @Override
    public Response addEventToTimeLine()
    {
        return Response.ok(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE)).build();
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.rest.api.ITimelineRestService#getTimeline()
     */
    @Path("/get")
    @GET
    @Override
    public Response getTimeline()
    {
        return Response.ok(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE)).build();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteTimeline()
     */
    @Path("/delete")
    @POST
    @Override
    public Response deleteTimeline()
    {
        return Response.ok(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE)).build();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteEventFromTimeline
     * ()
     */
    @Path("/deleteEvent")
    @POST
    @Override
    public Response deleteEventFromTimeline()
    {
        return Response.ok(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE)).build();
    }

}
