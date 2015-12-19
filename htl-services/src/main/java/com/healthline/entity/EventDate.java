package com.healthline.entity;

/**
 * 
 * @author 212473687
 * 
 */
public class EventDate
{

    private String month;
    private String day;
    private String year;

    public EventDate()
    {
        // TODO Auto-generated constructor stub
    }

    public EventDate(int year, int month, int day)
    {
        this(String.valueOf(year), String.valueOf(month), String.valueOf(day));
    }

    public EventDate(String year, String month, String day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * 
     * @return The month
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * 
     * @param month
     *            The month
     */
    public void setMonth(String month)
    {
        this.month = month;
    }

    /**
     * 
     * @return The day
     */
    public String getDay()
    {
        return day;
    }

    /**
     * 
     * @param day
     *            The day
     */
    public void setDay(String day)
    {
        this.day = day;
    }

    /**
     * 
     * @return The year
     */
    public String getYear()
    {
        return year;
    }

    /**
     * 
     * @param year
     *            The year
     */
    public void setYear(String year)
    {
        this.year = year;
    }

}
