package com.healthline.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Aniket
 * 
 * @param <T>
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.NONE)
public class RestServiceResponse<T>
{

    @XmlElement
    private String  status;
    @XmlElement
    private String  errorMessage;
    @XmlElement
    private String  message;
    @XmlElement
    private List<T> result;

    /**
     * 
     */
    public RestServiceResponse()
    {
        super();
    }

    /**
     * 
     * @param status
     *            status for the response
     * @param errorMessage
     *            error message if any failure
     * @param message
     *            general message if any
     * @param result
     *            list of result if success
     */
    public RestServiceResponse(String status, String message, String errorMessage, List<T> result)
    {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
        this.result = result;
    }

    /**
     * 
     * @param status
     *            status for the response
     * @param errorMessage
     *            error message if any failure
     * @param message
     *            general message if any
     * @param result
     *            result if success
     */
    public RestServiceResponse(String status, String message, String errorMessage, T result)
    {
        this(status, message, errorMessage,
                (result == null ? null : new ArrayList<T>(Collections.singletonList(result))));
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return this.status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage()
    {
        return this.errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the result
     */
    public List<T> getResult()
    {
        return this.result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(List<T> result)
    {
        this.result = result;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return this.message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

}
