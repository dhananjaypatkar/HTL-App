package com.healthline.entity;

import java.math.BigInteger;

import org.joda.time.DateTime;

/**
 * 
 * @author 212473687
 * 
 */
public class Event
{

    private BigInteger         id;

    private Media              media;

    private Description        text;

    private EventDate          eventDate;

    private transient DateTime startDate;
    private transient DateTime endDate;

    /**
     * @return the id
     */
    public BigInteger getId()
    {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigInteger id)
    {
        this.id = id;
    }

    public EventDate getEventDate()
    {
        return this.eventDate;
    }

    public void setEventDate(EventDate eventDate)
    {
        this.eventDate = eventDate;
    }

    public Event()
    {
        // TODO Auto-generated constructor stub
    }

    public Media getMedia()
    {
        return this.media;
    }

    public void setMedia(Media media)
    {
        this.media = media;
    }

    public Description getText()
    {
        return this.text;
    }

    public void setText(Description text)
    {
        this.text = text;
    }

    /**
     * @return the startDate
     */
    public DateTime getStartDate()
    {
        return this.startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(DateTime startDate)
    {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public DateTime getEndDate()
    {
        return this.endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(DateTime endDate)
    {
        this.endDate = endDate;
    }

}
