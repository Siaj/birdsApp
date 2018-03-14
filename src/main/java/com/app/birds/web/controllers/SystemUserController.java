/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.DistrictFacade;
import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.ejbSessions.UserRoleFacade;
import com.app.birds.entities.District;
import com.app.birds.entities.SystemUser;
import com.app.birds.entities.UserRole;
import com.app.birds.web.commons.GenerateIDs;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.utilities.CommonUtil;
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
    @Inject
    private DistrictFacade districtFacade;
    @Inject
    private UserRoleFacade roleFacade;

    private List<SystemUser> usersList = new ArrayList<>();
    private transient DataModel<SystemUser> usersDataTable;
    private SystemUser systemUser = new SystemUser();
    private District district = new District();
    private UserRole userRole = new UserRole();
    private UserAccessController accessController = new UserAccessController();
    private String selectedDistrict, selectedRole;
//    private boolean renderAccountDetails = false;
//    private boolean renderSaveButton = true;
    private String renderAccount = "false", renderSave = "true";
    private String username, surname;
    private String password, othername;
    private String searchCriteria, searchText;
    String regId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();

    public SystemUserController() {
    }

    public void resetUserList() {
        searchCriteria = null;
        searchText = "";
        usersList = getUsersList();
    }

    public void searchUser() {
        if (getSearchCriteria() == null || getSearchText() == null) {
            JSFUtility.warnMessage("Required Fields: ", "All the fields are required for the search");
        } else {
            if (getUsersList().isEmpty()) {
                JSFUtility.infoMessage("Empty List:", "No User with Such Criteria Found");
            } else {
                usersList = getUsersList();
            }
        }
    }

    public void saveNewUser() {
        surname = systemUser.getLastName();
        othername = systemUser.getMiddleName();
        username = GenerateIDs.generateUsername(surname, othername);
        password = GenerateIDs.generateHash(GenerateIDs.generateUsername(surname, othername));

        System.out.println(GenerateIDs.generateUsername(surname, othername));
        System.out.println(password);

        String userId = CommonUtil.generateID();

//        userAccount.setUserAccountId(userId);
        systemUser.setSystemUserId(userId);
        systemUser.setUsername(username);
        systemUser.setPassword(password);
        systemUser.setAccountStatus("Active");

        String systemUserFullName = systemUser.getLastName().toUpperCase() + " " + systemUser.getMiddleName();
        district = districtFacade.districtFind(selectedDistrict);
        userRole = roleFacade.roleFind(selectedRole);
        if (district == null || userRole == null) {
            JSFUtility.warnMessage("Required:", "Both District and User Role Details Required For Save");
        } else {
            systemUser.setDistrict(district);
            systemUser.setUserRole(userRole);
            systemUser.setFirstName(systemUserFullName);

            String systemUserSaved = systemUserFacade.systemUserCreate(systemUser);

            if (systemUserSaved != null) {
                resetButton();
                JSFUtility.infoMessage("Success :", "User Details Have Been Saved Successfully. Account Login Details Found Below."
                        + "Username Same As Password, Advise User to Change Password Immediately for Security Reasons");
                renderAccount = "true";
            } else {
                JSFUtility.errorMessage("Error :", "User couldn't be saved due to some errors.");
            }
        }
    }

    public void updateUser() {
        try {
            district = districtFacade.districtFind(selectedDistrict);
            userRole = roleFacade.roleFind(selectedRole);
            if (district == null || userRole == null) {
                JSFUtility.warnMessage("Required:", "District and User Role Needs TO Be Defined Before Save Can Proceed");
            } else {
                if (selectedDistrict.equals(systemUser.getDistrict().getDistrictId())) {
                    systemUser.setDistrict(district);
                } else {
                    systemUser.setDistrict(district);
                    systemUser.setDistrictCenter(null);
                    //Send an update to district admin of arrival of a new user to district
                }

                String systemUserFullName = systemUser.getLastName().toUpperCase() + " " + systemUser.getMiddleName();

                systemUser.setFirstName(systemUserFullName);
                systemUser.setUserRole(userRole);
            }

            systemUserFacade.systemUserUpdate(getSystemUser());
            resetButton();
            JSFUtility.infoMessage("System User :", "System User was Successfully Update");
        } catch (Exception e) {
            e.printStackTrace();
            JSFUtility.warnMessage("System User :", "Unable to Update User Details");
        }
    }

    public void deleteUser() {
// May not need to delete any user from system
    }

    public void rowSelectData() {
        systemUser = (SystemUser) usersDataTable.getRowData();
        userRole = systemUser.getUserRole();
        System.out.println(userRole.getRoleId() + " " + userRole.getRoleName());
        district = systemUser.getDistrict();
        if (district == null) {
            selectedDistrict = null;
        } else {
            selectedDistrict = String.valueOf(district.getDistrictId());
        }
        selectedRole = String.valueOf(userRole.getRoleId());
        renderSave = "false";
    }

    public void resetButton() {
        systemUser = new SystemUser();
        selectedDistrict = "";
        selectedRole = "";
        renderAccount = "false";
        renderSave = "true";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public UserRoleFacade getRoleFacade() {
        return roleFacade;
    }

    public void setRoleFacade(UserRoleFacade roleFacade) {
        this.roleFacade = roleFacade;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public DistrictFacade getDistrictFacade() {
        return districtFacade;
    }

    public void setDistrictFacade(DistrictFacade districtFacade) {
        this.districtFacade = districtFacade;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public List<SystemUser> getUsersList() {
        if (getSearchCriteria() == null || getSearchText().equals("")) {
            usersList = systemUserFacade.systemUserGetByRegion(false, regId);
        } else {
            usersList = systemUserFacade.systemUserFindByAttributeByRegion(getSearchCriteria(), getSearchText(), "STRING", regId, false);
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

//    public boolean isRenderAccountDetails() {
//        return renderAccountDetails;
//    }
//
//    public void setRenderAccountDetails(boolean renderAccountDetails) {
//        this.renderAccountDetails = renderAccountDetails;
//    }
//    public boolean isRenderSaveButton() {
//        return renderSaveButton;
//    }
//
//    public void setRenderSaveButton(boolean renderSaveButton) {
//        this.renderSaveButton = renderSaveButton;
//    }
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
