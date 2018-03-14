/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.GenerateIDs;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "userAccountController")
@SessionScoped
public class UserAccountController implements Serializable {

    @Inject
    SystemUserFacade userFacade;

    private SystemUser systemUser = new SystemUser();
    private UserAccessController accessController = new UserAccessController();
    private String currentPassword, newPassword, confirmPassword;
    private UserAuthentication authentication = new UserAuthentication();
    private String searchCriteria, searchText;
    private List<SystemUser> listOfUsers = new ArrayList<>();
    private List<SystemUser> listForDistAdmin = new ArrayList<>();
    private DataModel<SystemUser> systemUserModel;

    public UserAccountController() {
    }

    public void searchAccount() {
        if (searchCriteria.equals("null") || searchText.equals("")) {
            JSFUtility.warnMessage("Alert: ", "All parameters are required in order to make a search");
            listOfUsers = getListOfUsers();
        } else {
            listOfUsers = getListOfUsers();
        }
    }

    public void resetEntries() {
        searchCriteria = null;
        searchText = "";
        listOfUsers = getListOfUsers();
    }

    public void fetchRowData() {

    }

    public void activateUserAccount() {
        systemUser = systemUserModel.getRowData();
        systemUser.setAccountStatus("Active");
        Boolean deactivated = userFacade.systemUserUpdate(systemUser);
        if (deactivated) {
            JSFUtility.infoMessage("Success: ", "User Account successfully activated");
        } else {
            JSFUtility.warnMessage("Error: ", "User Account could not be activated");
        }
    }

    public void deActivateUserAccount() {
        systemUser = systemUserModel.getRowData();
        systemUser.setAccountStatus("Inactive");
        Boolean deactivated = userFacade.systemUserUpdate(systemUser);
        if (deactivated) {
            JSFUtility.infoMessage("Success: ", "User Account successfully deactivated");
        } else {
            JSFUtility.warnMessage("Error: ", "User Account could not be deactivated");
        }
    }

    public void changePassword() {
        systemUser = accessController.getSystemUser();

        if (currentPassword.equals("")) {
            JSFUtility.warnMessage("Required: ", "Current Password required to proceed with change");
        } else {
            if (accessController.getSystemUser().getPassword().equals(GenerateIDs.generateHash(currentPassword))) {
                System.out.println(accessController.getSystemUser().getPassword());
                System.out.println(GenerateIDs.generateHash(currentPassword));
                if (getNewPassword().equals("")) {
                    JSFUtility.warnMessage("Required: ", "Please enter your new password to proceed with change");
                } else {
                    if (getNewPassword().equals(getConfirmPassword())) {
                        systemUser.setPassword(GenerateIDs.generateHash(newPassword));
                        boolean success = userFacade.systemUserUpdate(systemUser);
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
        String roleName = accessController.getSystemUser().getUserRole().getRoleName();
        System.out.println(roleName + "tried to cancel password change");
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<SystemUser> getListOfUsers() {
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            listOfUsers = userFacade.systemUserGetAll(false);
        } else {
            listOfUsers = userFacade.systemUserFindByAttribute(searchCriteria, searchText, "STRING", false);
        }

        return listOfUsers;
    }

    public void setListOfUsers(List<SystemUser> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public List<SystemUser> getListForDistAdmin() {
        return listForDistAdmin;
    }

    public void setListForDistAdmin(List<SystemUser> listForDistAdmin) {
        this.listForDistAdmin = listForDistAdmin;
    }

    public DataModel<SystemUser> getSystemUserModel() {
        systemUserModel = new ListDataModel<>(getListOfUsers());
        return systemUserModel;
    }

    public void setSystemUserModel(DataModel<SystemUser> systemUserModel) {
        this.systemUserModel = systemUserModel;
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
