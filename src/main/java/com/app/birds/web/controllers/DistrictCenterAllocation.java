/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.DistrictCenterFacade;
import com.app.birds.ejbSessions.SupportBean;
import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.District;
import com.app.birds.entities.DistrictCenter;
import com.app.birds.entities.SystemUser;
import com.app.birds.entities.UserAccount;
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
@Named(value = "districtCenterAllocation")
@SessionScoped
public class DistrictCenterAllocation implements Serializable {

    @Inject
    private DistrictCenterFacade districtCenterFacade;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private SupportBean supportBean;

    private List<SystemUser> usersList = new ArrayList<>();
    private transient DataModel<SystemUser> usersDataModel;
    private SystemUser systemUser = new SystemUser();
    private District district = new District();
    private DistrictCenter districtCenter = new DistrictCenter();
    private UserAccount userAccount = new UserAccount();
    private UserAccessController accessController = new UserAccessController();

    private boolean renderSave = false;
    private String searchCriteria, searchText, selectedCenter;
    private String saveEditButton = "Save";

    public DistrictCenterAllocation() {
    }

    public void saveAllocation() {
        try {
            if (selectedCenter.equals("null")) {
                JSFUtility.warnMessage("Required:", "District Center is Required for Saving Field Allocation");
            } else {
                System.out.println(selectedCenter);
                districtCenter = districtCenterFacade.districtCenterFind(selectedCenter);

                systemUser = systemUserFacade.systemUserFind(systemUser.getSystemUserId());
                systemUser.setDistrictCenter(districtCenter);

                boolean userCenterUpdated = systemUserFacade.systemUserUpdate(systemUser);

                if (userCenterUpdated) {
                    JSFUtility.infoMessage("Success:", "System User has been successfully allocated to " + districtCenter.getCenterName());
                    cancelAllocation();
                } else {
                    JSFUtility.warnMessage("Error:", "An Error Occured While Trying to allocate Registrar a Field Location. Please Try Again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchRowData() {
        systemUser = (SystemUser) usersDataModel.getRowData();
        districtCenter = systemUser.getDistrictCenter();

        if (districtCenter != null) {
            JSFUtility.infoMessage("Info:", "System User Is Already Assigned a Field.You can Therefore Change User's Allocated Field");
            selectedCenter = String.valueOf(districtCenter.getCenterId());
        } else {
            if (systemUser.getUserRole().getRoleName().equals("Registrar")) {
                JSFUtility.infoMessage("Info:", "System User Is Not Assigned a Field.You Can Proceed to Allocate Field to the Selected User");
                selectedCenter = null;
            } else {
                selectedCenter = null;
            }
        }

        renderSave = true;
    }

    public void cancelAllocation() {
        renderSave = false;
    }

    public void resetButton() {
        systemUser = new SystemUser();
        searchCriteria = null;
        searchText = "";
        usersList = systemUserFacade.systemUserGetByDistrict(false, accessController.getLoginUser().getDistrict());
    }

    public void searchUser() {
        usersList = getUsersList();
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public DistrictCenterFacade getDistrictCenterFacade() {
        return districtCenterFacade;
    }

    public void setDistrictCenterFacade(DistrictCenterFacade districtCenterFacade) {
        this.districtCenterFacade = districtCenterFacade;
    }

    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public List<SystemUser> getUsersList() {
        if (getSearchCriteria() == null || getSearchText() == null) {
            usersList = systemUserFacade.systemUserGetByDistrict(false, accessController.getLoginUser().getDistrict());
        } else {
            usersList = systemUserFacade.systemUserFindByAttributeByDistrict(searchCriteria, searchText, "STRING", accessController.getLoginUser().getDistrict(), false);
        }
        return usersList;
    }

    public void setUsersList(List<SystemUser> usersList) {
        this.usersList = usersList;
    }

    public DataModel<SystemUser> getUsersDataModel() {
        usersDataModel = new ListDataModel<>(getUsersList());
        return usersDataModel;
    }

    public void setUsersDataModel(DataModel<SystemUser> usersDataModel) {
        this.usersDataModel = usersDataModel;
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

    public DistrictCenter getDistrictCenter() {
        return districtCenter;
    }

    public void setDistrictCenter(DistrictCenter districtCenter) {
        this.districtCenter = districtCenter;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public boolean isRenderSave() {
        return renderSave;
    }

    public void setRenderSave(boolean renderSave) {
        this.renderSave = renderSave;
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

    public String getSelectedCenter() {
        return selectedCenter;
    }

    public void setSelectedCenter(String selectedCenter) {
        this.selectedCenter = selectedCenter;
    }

    public String getSaveEditButton() {
        return saveEditButton;
    }

    public void setSaveEditButton(String saveEditButton) {
        this.saveEditButton = saveEditButton;
    }

//</editor-fold>
}
