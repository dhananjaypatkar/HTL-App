/**
 * 
 */
package com.healthline.services.api;

import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public interface IUserService
{

    User createNewUser(User user);

    User getUser(String userId);

    User updateUser(User user);

    boolean deleteUser(String userId);

}
