/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.DistrictCenterFacade;
import com.app.birds.ejbSessions.DistrictFacade;
import com.app.birds.ejbSessions.GenIdFacade;
import com.app.birds.entities.District;
import com.app.birds.entities.DistrictCenter;
import com.app.birds.entities.GenId;
import com.app.birds.entities.Region;
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
@Named(value = "officesRegistration")
@SessionScoped
public class OfficesRegistration implements Serializable {

    @Inject
    private DistrictFacade districtFacade;
    @Inject
    private DistrictCenterFacade centerFacade;
    @Inject
    private GenIdFacade genIdFacade;

    private District district = new District();
    private DistrictCenter districtCenter = new DistrictCenter();
    private Region region = new Region();
    private GenId genId = new GenId();
    private String searchCriteria, searchText;
    private String centerSearchCriteria, centeSearchText, selectedDistrict;
    Integer districtCode, centerCode;
    String deleted;
    private List<District> listOfDistricts = new ArrayList<>();
    private transient DataModel<District> districtDataModel;
    private List<DistrictCenter> listOfDistrictCenters = new ArrayList<>();
    private transient DataModel<DistrictCenter> districtCenterDataModel;
    private UserAccessController accessController = new UserAccessController();

    private boolean renderSave = true;
    private boolean renderCenterSave = true;
    private String regionName = accessController.getSystemUser().getDistrict().getRegion().getRegionName();

    public OfficesRegistration() {
    }

    public String districtCode(int low, int high) {
        districtCode = (int) (Math.random() * (high - low + 1)) + low;
        return districtCode.toString();
    }

    public String centerCode(int low, int high) {
        centerCode = (int) (Math.random() * (high - low + 1)) + low;
        return centerCode.toString();
    }

    public void saveDistrict() {
        String regionId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();
        String district_id = districtCode(1000, 9000);

        accessController.getSystemUser().getDistrict().getRegion().getDistrictList();

        district.setDistrictId(regionId + district_id);
        district.setRegion(accessController.getSystemUser().getDistrict().getRegion());
        genId.setGenTableId(CommonUtil.generateID());
        genId.setDistrictCode(district.getDistrictId());
        genId.setDistLastBirthNum(0);
        genId.setDistLastDeathNum(0);

        String savedId = genIdFacade.genIdCreate(genId);
        String saved = districtFacade.districtCreate(district);

        if (saved != null && savedId != null) {
            resetButton();
            JSFUtility.infoMessage("Success:", "District Office has been Successfully Saved.");
        } else {
            JSFUtility.warnMessage("Error:", "Couldn't Save District Office.");
        }
    }

    public void updateDistrict() {
        if (district.getDistrictName().equals("") || district.getPrimaryContact().equals("") || district.getPhysicalLocation().equals("")) {
            JSFUtility.warnMessage("Alert: ", "All fields are required in order to proceeed with update");
        } else {
            boolean updated = districtFacade.districtUpdate(district);
            if (updated) {
                resetButton();
                JSFUtility.infoMessage("Success:", "District Details Were Successfully Updated.");
            } else {
                JSFUtility.warnMessage("Error:", "District Details Couldn't Be Updated.");
            }
        }
    }

    public void deleteDistrict() {
        district = districtDataModel.getRowData();
        boolean deletedDistOffice = districtFacade.districtDelete(district, false);
        if (deletedDistOffice) {
            JSFUtility.infoMessage("Success:", "District Has Been Successfully Deleted");
//            resetButton();
            district = new District();
        } else {
            JSFUtility.warnMessage("Error:", "Couldn't Delete District Due To Errors.Please Try Again");
        }
    }

    public void restoreDeleted() {
        district = districtDataModel.getRowData();
        district.setDeleted("NO");
        boolean restored = districtFacade.districtUpdate(district);
        if (restored) {
            JSFUtility.infoMessage("Success:", "District Has Been Successfully Restored");
//            resetButton();
            district = new District();
        } else {
            JSFUtility.warnMessage("Error:", "Couldn't Restore District Due To Errors.Please Try Again");
        }

    }

    public void searchDistrict() {
        listOfDistricts = getListOfDistricts();
    }

    public void resetButton() {
        district = new District();
        searchCriteria = null;
        searchText = "";
        renderSave = true;
        listOfDistricts = getListOfDistricts();

        districtCenter = new DistrictCenter();
        centerSearchCriteria = null;
        centeSearchText = "";
        renderCenterSave = true;
        listOfDistrictCenters = getListOfDistrictCenters();
        selectedDistrict = "";
    }

    public void fetchRowData() {
        district = (District) districtDataModel.getRowData();
        region = district.getRegion();
        renderSave = false;
    }

//    District Center Functionalities
    public void searchDistrictCenter() {
        listOfDistrictCenters = getListOfDistrictCenters();
    }

    public void saveCenter() {
        String center_id = centerCode(1000, 9000);

        districtCenter.setCenterId(center_id);
        district = districtFacade.districtFind(selectedDistrict);

        if (district == null) {
            JSFUtility.warnMessage("Alert:", "District Cannot Be Null.");
        } else {
            districtCenter.setDistrictUnder(district);
            String saveCenter = centerFacade.districtCenterCreate(districtCenter);

            if (saveCenter != null) {
                resetButton();
                JSFUtility.infoMessage("Success:", "District Center has Been Successfully Saved");
            } else {
                JSFUtility.warnMessage("Error:", "District Center Couldn't be Saved.Please Try Again");
            }
        }
    }

    public void updateCenterDetails() {
        if (getDistrictCenter().getCenterName() == null || getDistrictCenter().getCenterType() == null
                || getDistrictCenter().getPrimaryContact() == null || getDistrictCenter().getCenterLocation() == null || getSelectedDistrict() == null) {
            JSFUtility.warnMessage("Error: ", "All form fields are required to update center office details");
        } else {
            district = districtFacade.districtFind(selectedDistrict);
            districtCenter.setDistrictUnder(district);
            boolean updated = centerFacade.districtCenterUpdate(districtCenter);
            if (updated) {
                resetButton();
                JSFUtility.infoMessage("Success:", "District Details Were Successfully Updated.");
            } else {
                JSFUtility.warnMessage("Error:", "District Details Couldn't Be Updated.");
            }
        }
    }

    public void deleteCenter() {
        districtCenter = districtCenterDataModel.getRowData();
        boolean deletedCenter = centerFacade.districtCenterDelete(districtCenter, false);

        if (deletedCenter) {
            resetButton();
            JSFUtility.infoMessage("Success", "Center Office has Been Successfully Deleted");
        } else {
            JSFUtility.warnMessage("Error:", "Center Office Could Not be Deleted");
        }
    }

    public void restoreDeletedCenter() {
        districtCenter = districtCenterDataModel.getRowData();
        districtCenter.setDeleted("NO");

        boolean restoreCenter = centerFacade.districtCenterUpdate(districtCenter);
        if (restoreCenter) {
            JSFUtility.infoMessage("Success", "Center Office has Been Successfully Deleted");
            resetButton();
        } else {
            JSFUtility.infoMessage("Success", "Center Office has Been Successfully Deleted");
        }
    }

    public void fetchCenterRowData() {
        districtCenter = (DistrictCenter) districtCenterDataModel.getRowData();
        district = districtCenter.getDistrictUnder();
        selectedDistrict = String.valueOf(district.getDistrictId());
        renderCenterSave = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public DistrictFacade getDistrictFacade() {
        return districtFacade;
    }

    public void setDistrictFacade(DistrictFacade districtFacade) {
        this.districtFacade = districtFacade;
    }

    public List<District> getListOfDistricts() {
        if (getSearchCriteria() == null || getSearchText() == null) {
            System.out.println(accessController.getLoginUser().getDistrict());
            listOfDistricts = districtFacade.districtFindByRegion(accessController.getSystemUser().getDistrict().getRegion().getRegionId(), true);
        } else {
            listOfDistricts = districtFacade.districtFindByAttributeforRegion(getSearchCriteria(), getSearchText(), "STRING", accessController.getSystemUser().getDistrict().getRegion().getRegionId(), true);
        }
        return listOfDistricts;
    }

    public void setListOfDistricts(List<District> listOfDistricts) {
        this.listOfDistricts = listOfDistricts;
    }

    public DataModel<District> getDistrictDataModel() {
        districtDataModel = new ListDataModel<>(getListOfDistricts());
        return districtDataModel;
    }

    public void setDistrictDataModel(DataModel<District> districtDataModel) {
        this.districtDataModel = districtDataModel;
    }

    public List<DistrictCenter> getListOfDistrictCenters() {
        String regId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();
        if (getCenterSearchCriteria() == null || getCenteSearchText() == null) {
            listOfDistrictCenters = centerFacade.distictCenterFindByRegionId(true, regId);
        } else {
            listOfDistrictCenters = centerFacade.districtCenterFindWithRegionIdByAttribute(getCenterSearchCriteria(), getCenteSearchText(), "STRING", regId, true);
        }
        return listOfDistrictCenters;
    }

    public void setListOfDistrictCenters(List<DistrictCenter> listOfDistrictCenters) {
        this.listOfDistrictCenters = listOfDistrictCenters;
    }

    public DataModel<DistrictCenter> getDistrictCenterDataModel() {
        districtCenterDataModel = new ListDataModel<>(getListOfDistrictCenters());
        return districtCenterDataModel;
    }

    public void setDistrictCenterDataModel(DataModel<DistrictCenter> districtCenterDataModel) {
        this.districtCenterDataModel = districtCenterDataModel;
    }

    public String getCenterSearchCriteria() {
        return centerSearchCriteria;
    }

    public void setCenterSearchCriteria(String centerSearchCriteria) {
        this.centerSearchCriteria = centerSearchCriteria;
    }

    public String getCenteSearchText() {
        return centeSearchText;
    }

    public void setCenteSearchText(String centeSearchText) {
        this.centeSearchText = centeSearchText;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public GenIdFacade getGenIdFacade() {
        return genIdFacade;
    }

    public void setGenIdFacade(GenIdFacade genIdFacade) {
        this.genIdFacade = genIdFacade;
    }

    public DistrictCenterFacade getCenterFacade() {
        return centerFacade;
    }

    public void setCenterFacade(DistrictCenterFacade centerFacade) {
        this.centerFacade = centerFacade;
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

    public GenId getGenId() {
        return genId;
    }

    public void setGenId(GenId genId) {
        this.genId = genId;
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

    public Integer getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(Integer centerCode) {
        this.centerCode = centerCode;
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

    public Integer getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Integer districtCode) {
        this.districtCode = districtCode;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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

    public boolean isRenderCenterSave() {
        return renderCenterSave;
    }

    public void setRenderCenterSave(boolean renderCenterSave) {
        this.renderCenterSave = renderCenterSave;
    }

//</editor-fold>
}
