/**
 * 
 */
package com.healthline.entity;

import java.io.Serializable;
import java.math.BigInteger;

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
    private BigInteger        id;
    private String            email;
    private String            phoneNo;
    private String            fullname;
    private transient String  password;
    private DateTime          birthdate;
    private String            gender;
    private String            city;
    private DateTime          lastLoginDate;
    private String            region;

    // Change password functionality
    private transient String  oldPassword;
    private transient String  newPassword;

    // for soft deleting an user
    private boolean           isDeleted;

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
     * @return the email
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo()
    {
        return this.phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the fullname
     */
    public String getFullname()
    {
        return this.fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname)
    {
        this.fullname = fullname;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the birthdate
     */
    public DateTime getBirthdate()
    {
        return this.birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(DateTime birthdate)
    {
        this.birthdate = birthdate;
    }

    /**
     * @return the gender
     */
    public String getGender()
    {
        return this.gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return this.city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return the lastLoginDate
     */
    public DateTime getLastLoginDate()
    {
        return this.lastLoginDate;
    }

    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(DateTime lastLoginDate)
    {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword()
    {
        return this.oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword()
    {
        return this.newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

    /**
     * @return the isDeleted
     */
    public boolean isDeleted()
    {
        return this.isDeleted;
    }

    /**
     * @return the region
     */
    public String getRegion()
    {
        return this.region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setDeleted(boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        User other = (User) obj;
        if ( this.email == null )
        {
            if ( other.email != null ) return false;
        }
        else if ( !this.email.equals(other.email) ) return false;
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "User [id=" + this.id + ", email=" + this.email + ", phoneNo=" + this.phoneNo + ", fullname=" + this.fullname
                + ", birthdate=" + this.birthdate + ", gender=" + this.gender + ", city=" + this.city + ", lastLoginDate="
                + this.lastLoginDate + ", region=" + this.region + ", isDeleted=" + this.isDeleted + "]";
    }

}
