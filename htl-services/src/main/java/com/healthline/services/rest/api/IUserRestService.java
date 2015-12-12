/**
 * 
 */
package com.healthline.services.rest.api;

import javax.ws.rs.core.Response;

import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public interface IUserRestService
{

    Response createNewUser(User user);

    Response getUser(String userId);

    Response updateUser(User user);

    Response deleteUser(String userId);
}
