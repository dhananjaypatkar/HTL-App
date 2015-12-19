package com.healthline.entity;

import java.math.BigInteger;

/**
 * 
 * @author 212473687
 * 
 */
public class Media
{

    private BigInteger id;
    private String     url;
    private String     caption;
    private String     credit;

    public Media()
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
     * @return The url
     */
    public String getUrl()
    {
        return this.url;
    }

    /**
     * 
     * @param url
     *            The url
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * 
     * @return The caption
     */
    public String getCaption()
    {
        return this.caption;
    }

    /**
     * 
     * @param caption
     *            The caption
     */
    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    /**
     * 
     * @return The credit
     */
    public String getCredit()
    {
        return this.credit;
    }

    /**
     * 
     * @param credit
     *            The credit
     */
    public void setCredit(String credit)
    {
        this.credit = credit;
    }

}
