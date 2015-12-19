package com.healthline.entity;

/**
 * 
 * @author 212473687
 * 
 */
public class Description
{

    private String headline;
    private String text;

    public Description()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param headline
     * @param text
     */
    public Description(String headline, String text)
    {
        this.headline = headline;
        this.text = text;
    }

    /**
     * 
     * @return The headline
     */
    public String getHeadline()
    {
        return headline;
    }

    /**
     * 
     * @param headline
     *            The headline
     */
    public void setHeadline(String headline)
    {
        this.headline = headline;
    }

    /**
     * 
     * @return The text
     */
    public String getText()
    {
        return text;
    }

    /**
     * 
     * @param text
     *            The text
     */
    public void setText(String text)
    {
        this.text = text;
    }

}
