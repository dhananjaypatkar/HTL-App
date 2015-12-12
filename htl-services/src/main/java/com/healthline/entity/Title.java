package com.healthline.entity;

/**
 * 
 * @author 212473687
 * 
 */
public class Title
{

    private Media       media;
    private Description text;

    public Title()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * 
     * @return The media
     */
    public Media getMedia()
    {
        return media;
    }

    /**
     * 
     * @param media
     *            The media
     */
    public void setMedia(Media media)
    {
        this.media = media;
    }

    /**
     * 
     * @return The text
     */
    public Description getText()
    {
        return text;
    }

    /**
     * 
     * @param text
     *            The text
     */
    public void setText(Description text)
    {
        this.text = text;
    }

}
