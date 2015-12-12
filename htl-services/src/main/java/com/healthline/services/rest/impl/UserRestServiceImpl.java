package com.healthline.services.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.healthline.common.RestServiceResponse;
import com.healthline.common.Status;
import com.healthline.entity.User;
import com.healthline.services.api.IUserService;
import com.healthline.services.impl.UserServiceImpl;
import com.healthline.services.rest.api.IUserRestService;

@SuppressWarnings("javadoc")
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserRestServiceImpl
        implements IUserRestService
{

    private IUserService userService = new UserServiceImpl();

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response createNewUser(User user)
    {
        List<User> ret = new ArrayList<User>();
        try
        {
            User res = this.userService.createNewUser(user);
            ret.add(res);
        }
        catch (Exception e)
        {
            return Response.ok(new RestServiceResponse<User>(Status.ERROR.name(), null, "Exception Occured", ret))
                    .build();
        }
        return Response.ok(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, ret)).build();
    }

    @Path("/deleteUser")
    @POST
    @Override
    public Response deleteUser(String userId)
    {
        try
        {
            this.userService.deleteUser(userId);
        }
        catch (Exception e)
        {
            return Response.ok(new RestServiceResponse<Boolean>(Status.ERROR.name(), null, "Exception Occured", Boolean.FALSE))
                    .build();
        }

        return Response.ok(
                new RestServiceResponse<Boolean>(Status.SUCCESS.name(), "Deleted account successfully", null, Boolean.TRUE))
                .build();
    }

    @Path("/updateUser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response updateUser(User user)
    {
        List<User> ret = new ArrayList<User>();
        try
        {
            User res = this.userService.updateUser(user);
            ret.add(res);
        }
        catch (Exception e)
        {
            return Response.ok(new RestServiceResponse<User>(Status.ERROR.name(), null, "Exception Occured", ret))
                    .build();
        }
        return Response.ok(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, ret)).build();
    }

    @Path("/getUser")
    @GET
    @Override
    public Response getUser(@QueryParam("userId") String userId)
    {
        List<User> ret = new ArrayList<User>();
        try
        {
            User res = this.userService.getUser(userId);
            ret.add(res);
        }
        catch (Exception e)
        {
            return Response.ok(new RestServiceResponse<User>(Status.ERROR.name(), null, "Exception Occured", ret))
                    .build();
        }
        return Response.ok(new RestServiceResponse<User>(Status.SUCCESS.name(), null, null, ret)).build();
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

}
