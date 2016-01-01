/**
 * 
 */
package com.healthline.services.rest.impl;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.healthline.common.RestServiceResponse;
import com.healthline.common.Status;
import com.healthline.entity.Description;
import com.healthline.entity.Event;
import com.healthline.entity.Timeline;
import com.healthline.services.api.ITimelineService;
import com.healthline.services.rest.api.ITimelineRestService;

/**
 * @author Aniket
 * 
 */

@Path("/timelines")
@Produces(MediaType.APPLICATION_JSON)
public class TimelineRestServiceImpl
        implements ITimelineRestService
{
    @Autowired
    private ITimelineService timelineService;
    @Autowired
    private Gson             gson;

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#createTimeline()
     */
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String createTimeline(@FormParam("userId") String userId, @FormParam("headline") String headline,
            @FormParam("description") String description)
    {
        try
        {
            this.timelineService.createTimeline(new Long(userId), headline, description);
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(),
                    "Timeline created successfully", null, Boolean.TRUE));
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.FALSE));
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#addEventToTimeLine
     * ()
     */
    @Path("/events")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public String addEventToTimeLine(FormDataMultiPart form)
    {
        Long timelineId = new Long(form.getField("timelineId").getValue());
        Long userId = new Long(form.getField("userId").getValue());
        String description = form.getField("description").getValue();
        DateTime startDate = new DateTime(form.getField("startDate").getValue());
        DateTime endDate = startDate;
        String endDateString = null;
        if ( form.getField("endDate") != null )
        {
            endDateString = form.getField("endDate").getValue();
            endDate = new DateTime(endDateString);
        }

        FormDataBodyPart filePart = form.getField("file");
        ContentDisposition headerOfFilePart = filePart.getContentDisposition();
        File fileData = filePart.getValueAs(File.class);
        String fileName = headerOfFilePart.getFileName();

        final Event event = new Event();
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setText(new Description(null, description));
        try
        {
            this.timelineService.addEventToTimeLine(userId, timelineId, event, fileName,
                    IOUtils.toByteArray(new FileInputStream(fileData)));
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), "Event added successfully",
                    null, Boolean.TRUE));
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.FALSE));
        }

    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.rest.api.ITimelineRestService#getTimeline()
     */
    @Path("{userId}")
    @GET
    @Override
    public String getTimeline(@PathParam("userId") Long userId)
    {
        try
        {
            Timeline res = this.timelineService.getTimeline(userId);
            if ( res != null )
            {
                return this.gson.toJson(new RestServiceResponse<Timeline>(Status.SUCCESS.name(), null, null, res));
            }
            // no timeline for the user
            return this.gson.toJson(new RestServiceResponse<Timeline>(Status.ERROR.name(), null,
                    "No Timeline for you yet", res));
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.FALSE));
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteTimeline()
     */
    @Path("{userId}")
    @DELETE
    @Override
    public String deleteTimeline(@PathParam("userId") Long userId)
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteEventFromTimeline
     * ()
     */
    @Path("/events/{eventId}")
    @DELETE
    @Override
    public String deleteEventFromTimeline(@PathParam("eventId") Long eventId)
    {
        try
        {
            boolean deleted = this.timelineService.deleteEventFromTimeline(eventId);
            if ( deleted )
            {
                return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null,
                        Boolean.TRUE));
            }
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null, "Event does not exist",
                    Boolean.FALSE));
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.TRUE));
        }

    }

    /**
     * @return the timelineService
     */
    public ITimelineService getTimelineService()
    {
        return this.timelineService;
    }

    /**
     * @param timelineService the timelineService to set
     */
    public void setTimelineService(ITimelineService timelineService)
    {
        this.timelineService = timelineService;
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
