/**
 * 
 */
package com.healthline.services.rest.api;

import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public interface IUserRestService
{

    String createNewUser(User user);

    String getUser(String userId);

    String updateUser(User user);

    String deleteUser(String userId);
}
