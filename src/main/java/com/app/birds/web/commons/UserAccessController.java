/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import com.app.birds.entities.UserAccount;
import com.app.birds.web.utilities.BirdsMenuConfiguration;
import com.app.birds.web.utilities.JSFUtility;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author seth
 */
@ManagedBean
@RequestScoped
public class UserAccessController implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private boolean isLogin = false;
    private boolean isAdmin = false;
    private boolean isComplainOnly = false;
    private LoginUser loginUser = null;
    private boolean subUser = false;
    private String loginUsername = "";
    private String district = null;
    private BirdsMenuConfiguration mnuConfig = null;
    private UserAccount userAccount = null;
    
public String showURL(){
    String url = JSFUtility.getCurrentContext().getExternalContext().getRequestPathInfo();
    System.out.println(url);
    return url;
}
    /** Creates a new instance of UserAccessController */
    public UserAccessController() {
    }

    /**
     * @return the isLogin
     */
    public boolean isIsLogin() {

        LoginUser user = getLoginUser();
        if (user == null) {
            isLogin = false;
        } else {
            isLogin = user.isIsLogin();
        }

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
        LoginUser user = getLoginUser();
        if (user == null) {
            isAdmin = false;
        } else {
            isAdmin = user.isIsAdmin();
        }
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return the loginUser
     */
    public LoginUser getLoginUser() {
        try {
            loginUser = (LoginUser) JSFUtility.getSessionValue(BirdsConstant.ADMIN_USER);
        } catch (Exception e) {
            loginUser = null;
        }
        return loginUser;
    }

    /**
     * @param loginUser the loginUser to set
     */
    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    /**
     * @return the isComplainOnly
     */
    /**
     * @param isComplainOnly the isComplainOnly to set
     */
    public void setIsComplainOnly(boolean isComplainOnly) {
        this.isComplainOnly = isComplainOnly;
    }

    /**
     * @return the subUser
     */
    /**
     * @param subUser the subUser to set
     */
    public void setSubUser(boolean subUser) {
        this.subUser = subUser;
    }

    /**
     * @return the loginUsername
     */
    public String getLoginUsername() {
        LoginUser user = getLoginUser();
        loginUsername = user.getUserScreenName();

        return loginUsername;
    }

    /**
     * @param loginUsername the loginUsername to set
     */
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public BirdsMenuConfiguration getMnuConfig() {
        try {
           mnuConfig = (BirdsMenuConfiguration) JSFUtility.getSessionValue(BirdsConstant.ADMIN_MENU);
        } catch (Exception e) {
            mnuConfig = null;
        }
        return mnuConfig;
    }

    public void setMnuConfig(BirdsMenuConfiguration mnuConfig) {
        this.mnuConfig = mnuConfig;
    }

    public UserAccount getUserAccount() {
        try {
            userAccount = (UserAccount) JSFUtility.getSessionValue(BirdsConstant.LOGIN_USER);
        } catch (Exception e) {
            userAccount = null;
        }
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    

}