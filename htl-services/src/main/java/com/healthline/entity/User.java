/**
 * 
 */
package com.healthline.entity;

import java.io.Serializable;

import org.joda.time.DateTime;

/**
 * @author Aniket
 * 
 */
public class User
        implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -2587942269908270351L;

    // basic user details
    private String            userId;
    private String            email;
    private String            phoneNo;
    private String            firstname;
    private String            lastname;
    private transient String  password;
    private DateTime          birthdate;
    private String            gender;

    // Change password functionality
    private transient String  oldPassword;
    private transient String  newPassword;

    // for soft deleting an user
    private boolean           isDeleted;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public DateTime getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(DateTime birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    public boolean isDeleted()
    {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        User other = (User) obj;
        if ( userId == null )
        {
            if ( other.userId != null ) return false;
        }
        else if ( !userId.equals(other.userId) ) return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "User [userId=" + userId + ", email=" + email + ", phoneNo=" + phoneNo + ", firstname=" + firstname
                + ", lastname=" + lastname + ", birthdate=" + birthdate + ", gender=" + gender + ", isDeleted="
                + isDeleted + "]";
    }

}
