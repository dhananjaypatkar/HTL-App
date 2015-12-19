/**
 * 
 */
package com.healthline.services.rest.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

@Path("/timeline")
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
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String createTimeline(@FormParam("userId") String userId, @FormParam("headline") String headline,
            @FormParam("description") String description)
    {
        try
        {
            this.timelineService.createTimeline(new BigInteger(userId), headline, description);
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(),
                    "Timeline created successfully", null, Boolean.TRUE));
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
    @Path("/add-event")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public String addEventToTimeLine(FormDataMultiPart form)
    {
        BigInteger timelineId = new BigInteger(form.getField("timelineId").getValue());
        String description = form.getField("description").getValue();
        DateTime startDate = new DateTime(form.getField("startDate").getValue());
        DateTime endDate = startDate;
        String endDateString = null;
        if(form.getField("endDate") != null)
        {
            endDateString = form.getField("endDate").getValue();
            endDate = new DateTime(endDateString);
        }

        FormDataBodyPart filePart = form.getField("file");
        ContentDisposition headerOfFilePart = filePart.getContentDisposition();
        InputStream fileData = filePart.getValueAs(InputStream.class);
        String fileName = headerOfFilePart.getFileName();

        final Event event = new Event();
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setText(new Description(null, description));
        try
        {
            this.timelineService.addEventToTimeLine(timelineId, event, fileName, fileData);
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), "Event added successfully",
                    null, Boolean.TRUE));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.FALSE));
        }
        finally
        {
            if ( fileData != null )
            {
                try
                {
                    fileData.close();
                }
                catch (IOException e)
                {
                    // log error
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see com.healthline.services.rest.api.ITimelineRestService#getTimeline()
     */
    @Path("/get-timeline")
    @GET
    @Override
    public String getTimeline(@QueryParam("userId") String userId)
    {
        try
        {
            Timeline res = this.timelineService.getTimeline(new BigInteger(userId));
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
            e.printStackTrace();
            return this.gson.toJson(new RestServiceResponse<Boolean>(Status.ERROR.name(), null,
                    "There was some error at our end", Boolean.FALSE));
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteTimeline()
     */
    @Path("/delete-timeline")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String deleteTimeline(@FormParam("userId") String userId)
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
    }

    /*
     * (non-Javadoc)
     * @see
     * com.healthline.services.rest.api.ITimelineRestService#deleteEventFromTimeline
     * ()
     */
    @Path("/delete-event")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Override
    public String deleteEventFromTimeline(@FormParam("eventId") String eventId)
    {
        return this.gson.toJson(new RestServiceResponse<Boolean>(Status.SUCCESS.name(), null, null, Boolean.TRUE));
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
