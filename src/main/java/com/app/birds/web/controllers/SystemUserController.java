/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.District;
import com.app.birds.entities.SystemUser;
import com.app.birds.entities.UserAccount;
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
@Named(value = "systemUserController")
@SessionScoped
public class SystemUserController implements Serializable {

    @Inject
    private SystemUserFacade systemUserFacade;

    private List<SystemUser> usersList = new ArrayList<>();
    private transient DataModel<SystemUser> usersDataTable;
    private SystemUser systemUser = new SystemUser();
    private UserAccount userAccount = new UserAccount();
    private District district = new District();
    private String selectedDistrict;
    private boolean renderAccountDetails = false;
    private boolean renderSaveButton = true;
    private String renderAccount = "false", renderSave = "true";
    private String username, surname;
    private String password, othername;
    private String searchCriteria, searchText;

    public SystemUserController() {
    }

    public void saveNewSystemUser() {

    }

    public void resetButton() {

    }

    public void searchSystemUser() {
        System.out.println("Searching for user");
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            JSFUtility.warnMessage("Alert: ", "All parameters are required beforh search can proceed, please enter details to find user");
            usersList = getDistrict().getSystemUserList();
        } else {
            if (getUsersList() == null) {
                JSFUtility.infoMessage("Empty List:", "No User with Such Criteria Found");
            } else {
                usersList = getUsersList();
            }
        }
    }

    public void resetSearchFields() {
        System.out.println("Reset user search fields");
        searchCriteria = null;
        searchText = "";
        usersList = getUsersList();
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public List<SystemUser> getUsersList() {
        if (searchCriteria == null || searchCriteria.equals("null") || searchText.equals("")) {
            usersList = systemUserFacade.systemUserGetAll(false);
        } else {
            usersList = systemUserFacade.systemUserFindByAttribute(searchCriteria, searchText, "STRING", false);
        }
        return usersList;
    }

    public void setUsersList(List<SystemUser> usersList) {
        this.usersList = usersList;
    }

    public DataModel<SystemUser> getUsersDataTable() {
        usersDataTable = new ListDataModel<>(getUsersList());
        return usersDataTable;
    }

    public void setUsersDataTable(DataModel<SystemUser> usersDataTable) {
        this.usersDataTable = usersDataTable;
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

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

    public boolean isRenderAccountDetails() {
        return renderAccountDetails;
    }

    public void setRenderAccountDetails(boolean renderAccountDetails) {
        this.renderAccountDetails = renderAccountDetails;
    }

    public boolean isRenderSaveButton() {
        return renderSaveButton;
    }

    public void setRenderSaveButton(boolean renderSaveButton) {
        this.renderSaveButton = renderSaveButton;
    }

    public String getRenderAccount() {
        return renderAccount;
    }

    public void setRenderAccount(String renderAccount) {
        this.renderAccount = renderAccount;
    }

    public String getRenderSave() {
        return renderSave;
    }

    public void setRenderSave(String renderSave) {
        this.renderSave = renderSave;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
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
//</editor-fold>
}
