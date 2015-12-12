package com.healthline.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 212473687
 * 
 */
public class Timeline
{

    private Title       title;
    private List<Event> events = new ArrayList<Event>();

    public Timeline()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @return The title
     */
    public Title getTitle()
    {
        return title;
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
        return events;
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
