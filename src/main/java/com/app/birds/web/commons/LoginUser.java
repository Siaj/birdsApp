/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

/**
 *
 * @author seth
 */
public class LoginUser {

    private String userType;
    private String userScreenName;
    private String accessFor;
    private String district;
    private Object userAccessType;
    private Object userLogin;
    private boolean isLogin = false;
    private boolean isAdmin = false;

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the userScreenName
     */
    public String getUserScreenName() {
        return userScreenName;
    }

    /**
     * @param userScreenName the userScreenName to set
     */
    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    /**
     * @return the accessFor
     */
    public String getAccessFor() {
        return accessFor;
    }

    /**
     * @param accessFor the accessFor to set
     */
    public void setAccessFor(String accessFor) {
        this.accessFor = accessFor;
    }

    /**
     * @return the userAccessType
     */
    public Object getUserAccessType() {
        return userAccessType;
    }

    /**
     * @param userAccessType the userAccessType to set
     */
    public void setUserAccessType(Object userAccessType) {
        this.userAccessType = userAccessType;
    }

    /**
     * @return the userLogin
     */
    public Object getUserLogin() {
        return userLogin;
    }

    /**
     * @param userLogin the userLogin to set
     */
    public void setUserLogin(Object userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * @return the isLogin
     */
    public boolean isIsLogin() {
        return isLogin;
    }

    /**
     * @param isLogin the isLogin to set
     */
    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
        
}
