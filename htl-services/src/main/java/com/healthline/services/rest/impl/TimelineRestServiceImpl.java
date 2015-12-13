/**
 * 
 */
package com.healthline.services.rest.impl;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
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
    @Autowired
    private Gson gson ;
    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#createTimeline()
     */
    @Path("/create")
    @POST
    @Override
    public String createTimeline()
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
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
    public String addEventToTimeLine()
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.rest.api.ITimelineRestService#getTimeline()
     */
    @Path("/get")
    @GET
    @Override
    public String getTimeline()
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteTimeline()
     */
    @Path("/delete")
    @POST
    @Override
    public String deleteTimeline()
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
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
    public String deleteEventFromTimeline()
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
    }

    /**
     * @return the gson
     */
    public Gson getGson()
    {
        return this.gson;
    }

    /**
     * @param gson the gson to set
     */
    public void setGson(Gson gson)
    {
        this.gson = gson;
    }
}
