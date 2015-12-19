package com.healthline.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 212473687
 * 
 */
public class Timeline
{

    private BigInteger  id;
    private Title       title;
    private List<Event> events = new ArrayList<Event>();

    public Timeline()
    {
        // TODO Auto-generated constructor stub
    }

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

    /**
     * 
     * @return The title
     */
    public Title getTitle()
    {
        return this.title;
    }

    /**
     * 
     * @param title
     *            The title
     */
    public void setTitle(Title title)
    {
        this.title = title;
    }

    /**
     * 
     * @return The events
     */
    public List<Event> getEvents()
    {
        return this.events;
    }

    /**
     * 
     * @param events
     *            The events
     */
    public void setEvents(List<Event> events)
    {
        this.events = events;
    }
}
