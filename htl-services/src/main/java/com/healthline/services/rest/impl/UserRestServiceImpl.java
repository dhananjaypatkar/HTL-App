package com.healthline.services.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.healthline.common.RestServiceResponse;
import com.healthline.common.Status;
import com.healthline.entity.User;
import com.healthline.services.api.IUserService;
import com.healthline.services.rest.api.IUserRestService;

@SuppressWarnings("javadoc")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestServiceImpl
        implements IUserRestService
{

    @Autowired
    private IUserService userService;
    @Autowired
    private Gson gson ;
    
    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public String createNewUser(User user)
    {
        List<User> ret = new ArrayList<User>();
        try
        {
            User res = this.userService.createNewUser(user);
            ret.add(res);
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<User>(Status.ERROR.name(), null, "Exception Occured", ret))
                    ;
        }
        return this.gson.toJson(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, ret));
    }

    @Path("/deleteUser")
    @POST
    @Override
    public String deleteUser(@FormParam("userId") String userId)
    {
        try
        {
            this.userService.deleteUser(userId);
        }
        catch (Exception e)
        {
            return this.gson.toJson(
                    new RestServiceResponse<Boolean>(Status.ERROR.name(), null, "Exception Occured", Boolean.FALSE));
        }

        return this.gson.toJson(
                new RestServiceResponse<Boolean>(Status.SUCCESS.name(), "Deleted account successfully " + userId, null,
                        Boolean.TRUE));
    }

    @Path("/updateUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public String updateUser(User user)
    {
        List<User> ret = new ArrayList<User>();
        try
        {
            User res = this.userService.updateUser(user);
            ret.add(res);
        }
        catch (Exception e)
        {
            return this.gson.toJson(new RestServiceResponse<User>(Status.ERROR.name(), null, "Exception Occured", ret));
        }
        return this.gson.toJson(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, ret));
    }

    @Path("/getUser")
    @GET
    @Override
    public String getUser(@QueryParam("userId") String userId)
    {
        try
        {
            User res = this.userService.getUser(userId);
            return this.gson.toJson(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, res));
        }
        catch (Exception e)
        {
            return this.gson.toJson(
                    new RestServiceResponse<Boolean>(Status.ERROR.name(), null, "Exception Occured", Boolean.FALSE));
        }
    }

    /**
     * @return the userService
     */
    public IUserService getUserService()
    {
        return this.userService;
    }

    /**
     * @param userService
     *            the userService to set
     */
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
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
