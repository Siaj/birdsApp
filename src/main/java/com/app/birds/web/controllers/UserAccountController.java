/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.ejbSessions.UserAccountFacade;
import com.app.birds.entities.SystemUser;
import com.app.birds.entities.UserAccount;
import com.app.birds.web.commons.GenerateIDs;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAccountController")
@SessionScoped
public class UserAccountController implements Serializable {

    @Inject
    UserAccountFacade accountFacade;
    @Inject
    SystemUserFacade userFacade;

    private SystemUser systemUser = new SystemUser();
    private UserAccount userAccount = new UserAccount();
    private UserAccessController accessController = new UserAccessController();
    private String currentPassword, newPassword, confirmPassword;
    private UserAuthentication authentication = new UserAuthentication();

    public UserAccountController() {
    }

    public void changePassword() {
        userAccount = accessController.getUserAccount();

        if (currentPassword.equals("")) {
            JSFUtility.warnMessage("Required: ", "Current Password required to proceed with change");
        } else {
            if (accessController.getUserAccount().getPassword().equals(GenerateIDs.generateHash(currentPassword))) {
                System.out.println(accessController.getUserAccount().getPassword());
                System.out.println(GenerateIDs.generateHash(currentPassword));
                if (getNewPassword().equals("")) {
                    JSFUtility.warnMessage("Required: ", "Please enter your new password to proceed with change");
                } else {
                    if (getNewPassword().equals(getConfirmPassword())) {
                        userAccount.setPassword(GenerateIDs.generateHash(newPassword));
                        boolean success = accountFacade.userAccountUpdate(userAccount);
                        if (success) {
                            authentication.logOutUser();
                            JSFUtility.infoMessage("Success:", "Your Password Has Been Successfully Changed.You Should Re-Login With Your New Password");
                        }
                    } else {
                        JSFUtility.warnMessage("Error:", "New Password and its Confirmation Should Match.Please Check and Enter Again");
                    }

                }

            } else {
                JSFUtility.warnMessage("Error:", "Current Password Entered is Incorrect.Please Check and re-nter Again");
            }

        }
    }

    public void resetPasswordChageFields() {
        currentPassword = "";
        newPassword = "";
        confirmPassword = "";
    }

    public void cancelChange() {
        String roleName = accessController.getUserAccount().getSystemUser().getUserRole().getRoleName();
        System.out.println(roleName + "tried to cancel passwrod change");
    }

    public UserAccountFacade getAccountFacade() {
        return accountFacade;
    }

    public void setAccountFacade(UserAccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    public SystemUserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(SystemUserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public UserAuthentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(UserAuthentication authentication) {
        this.authentication = authentication;
    }

}
