/**
 * 
 */
package com.healthline.dao.api;

import com.healthline.entity.User;

/**
 * @author Aniket
 * 
 */
public interface IUserServiceDao
{

    User createNewUser(User user);

    User getUser(String email);

    User updateUser(User user);

    boolean deleteUser(String email);
}
