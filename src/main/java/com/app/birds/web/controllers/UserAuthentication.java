/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.SupportBean;
import com.app.birds.entities.UserAccount;
import com.app.birds.web.commons.BirdsConstant;
import com.app.birds.web.commons.GenerateIDs;
import com.app.birds.web.commons.LoginUser;
import com.app.birds.web.utilities.BirdsMenuConfiguration;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAuthentication")
@SessionScoped
public class UserAuthentication implements Serializable {
    private static final long serialVersionUID = 7090138834846165429L;
    protected UserAccount userAccount = new UserAccount();
    protected BirdsMenuConfiguration mnuConfig = new BirdsMenuConfiguration();
    private String username = "";
    private String password = "";

    @EJB
    private SupportBean supportBean;
    
    public UserAuthentication() {
    }

    public String authenticateUser() {

//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
//            request.login(username, password);
            if (username == null || "".equals(username)) {
                JSFUtility.warnMessage("Login: ", "Username is required");
                return "index.xhtml";
            } else if (password == null || "".equals(password)) {
                JSFUtility.warnMessage("Login: ", "Password is required for login");
                return "index.xhtml";
            } else {
                password = GenerateIDs.generateHash(password);
                System.out.println("Hashed Password: " + password);
                userAccount = supportBean.authenticateUser(username, password);
                if (userAccount != null) {
                    LoginUser loginUser = new LoginUser();

                    if (null == userAccount.getSystemUser().getUserRole().getRoleName()) {
                        JSFUtility.errorMessage("Role: ", "Your role is not defined, contact systems administrator");
                        return "index.xhtml?faces-redirect=true";
                    } else {
                        switch (userAccount.getSystemUser().getUserRole().getRoleName()) {
                            case "Regional Administrator":
                                loginUser.setAccessFor("Regional Administrator");
                                loginUser.setUserLogin(userAccount);
                                loginUser.setUserScreenName(username);
                                loginUser.setDistrict(userAccount.getSystemUser().getDistrict().getDistrictId());
                                loginUser.setIsLogin(true);
                                loginUser.setIsAdmin(true);

                                //Configure Menu Rendering
                                mnuConfig.renderAllRegAdmin();

                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_USER, loginUser);
                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_MENU, mnuConfig);
                                JSFUtility.putSessionValue(BirdsConstant.LOGIN_USER, userAccount);

                                return "pages/reg_admin/regional_admin.xhtml?faces-redirect=true";
                            case "District Administrator":
                                loginUser.setAccessFor("District Admininstartor");
                                loginUser.setUserLogin(userAccount);
                                loginUser.setUserScreenName(username);
                                loginUser.setDistrict(userAccount.getSystemUser().getDistrict().getDistrictId());
                                loginUser.setUserLogin(true);
                                loginUser.setIsAdmin(false);

                                mnuConfig.renderAllDistAdmin();

                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_USER, loginUser);
                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_MENU, mnuConfig);
                                JSFUtility.putSessionValue(BirdsConstant.LOGIN_USER, userAccount);

                                return "pages/dist_admin/district_admin.xhtml?faces-redirect=true";
                            case "Registrar":
                                loginUser.setAccessFor("Registrar");
                                loginUser.setUserLogin(userAccount);
                                loginUser.setUserScreenName(username);
                                loginUser.setDistrict(userAccount.getSystemUser().getDistrict().getDistrictId());
                                loginUser.setIsLogin(true);
                                loginUser.setIsAdmin(false);

                                mnuConfig.renderRegistrar();

                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_USER, loginUser);
                                JSFUtility.putSessionValue(BirdsConstant.ADMIN_MENU, mnuConfig);
                                JSFUtility.putSessionValue(BirdsConstant.LOGIN_USER, userAccount);

                                return "pages/registrar/registrar.xhtml?faces-redirect=true";
                            default:
                                JSFUtility.errorMessage("Role: ", "Your role is not defined, contact systems administrator");
                                return "index.xhtml?faces-redirect=true";
                        }
                    }
                } else {
                    JSFUtility.warnMessage("Login :", "Username or Password Incorrect");
                    System.out.println("Wrong username or password");
                    return "index.xhtml";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in authenticating user: " + e.getLocalizedMessage());
            return null;
        }
    }

    public String logOutUser() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            JSFUtility.putSessionValue(BirdsConstant.LOGIN_USER, null);
            JSFUtility.destroySession();
            this.userAccount = null;
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "/start.html?faces-redirect=true";

    }

    public BirdsMenuConfiguration getMnuConfig() {
        return mnuConfig;
    }

    public void setMnuConfig(BirdsMenuConfiguration mnuConfig) {
        this.mnuConfig = mnuConfig;
    }

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}
