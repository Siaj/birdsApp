/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import com.app.birds.ejbSessions.DistrictCenterFacade;
import com.app.birds.ejbSessions.DistrictFacade;
import com.app.birds.ejbSessions.SecurityQuestionFacade;
import com.app.birds.ejbSessions.UserRoleFacade;
import com.app.birds.entities.District;
import com.app.birds.entities.DistrictCenter;
import com.app.birds.entities.SecurityQuestion;
import com.app.birds.entities.UserRole;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "common_options")
@SessionScoped
public class CommonOptions implements Serializable {

    @Inject
    private DistrictFacade districtFacade;
    @Inject
    private DistrictCenterFacade districtCenterFacade;
    @Inject
    private UserRoleFacade userRoleFacade;
    @Inject
    private SecurityQuestionFacade securityQuestionFacade;

    private SelectItem[] genderOptions;
    private SelectItem[] idTypeOptions;
    private SelectItem[] burialStatusOptions;
    private SelectItem[] facilityOptions;
    private SelectItem[] relationshipOptions;
    private SelectItem[] districtOptions;
    private SelectItem[] districtCenterOptions;
    private SelectItem[] workTypeOptions;
    private SelectItem[] activeStatusOptions;
    private SelectItem[] informantRelation;
    private SelectItem[] placeOfBirth;
    private SelectItem[] placeOfDeath;
    private SelectItem[] securityQuestions;
    private SelectItem[] daysOfMonth;
    private SelectItem[] monthsOfYear;
    private SelectItem[] calenderYears;
    private UserAccessController userAccessController = new UserAccessController();
    private String distCenter = userAccessController.getSystemUser().getDistrict().getDistrictId();
    int count;
    private String saveEditTest = "Save/Update";

//    Class Constructor 
    public CommonOptions() {
    }

    public void saveBirthDummy() {
        System.out.println("Checking to see if validation works!!!");
    }

    public String getSaveEditTest() {
        return saveEditTest;
    }

    public void setSaveEditTest(String saveEditTest) {
        this.saveEditTest = saveEditTest;
    }

    public SelectItem[] getGenderOptions() {
        genderOptions = new SelectItem[3];
        genderOptions[0] = new SelectItem("", "---Select Gender---");
        genderOptions[1] = new SelectItem("Male", "Male");
        genderOptions[2] = new SelectItem("Female", "Female");
        return genderOptions;
    }

    public void setGenderOptions(SelectItem[] genderOptions) {
        this.genderOptions = genderOptions;
    }

    public SelectItem[] getIdTypeOptions() {
        idTypeOptions = new SelectItem[4];
        idTypeOptions[0] = new SelectItem("", "---Select One---");
        idTypeOptions[1] = new SelectItem("Voters' Id", "Voters' Id");
        idTypeOptions[2] = new SelectItem("NHIS Card", "NHIS Card");
        idTypeOptions[3] = new SelectItem("National Id", "National Id");
        return idTypeOptions;
    }

    public void setIdTypeOptions(SelectItem[] idTypeOptions) {
        this.idTypeOptions = idTypeOptions;
    }

    public SelectItem[] getBurialStatusOptions() {
        burialStatusOptions = new SelectItem[3];
        burialStatusOptions[0] = new SelectItem(null, "---Select One---");
        burialStatusOptions[1] = new SelectItem("Buried", "Buried");
        burialStatusOptions[2] = new SelectItem("Not Buried", "Not Buried");
        return burialStatusOptions;
    }

    public void setBurialStatusOptions(SelectItem[] burialStatusOptions) {
        this.burialStatusOptions = burialStatusOptions;
    }

    public SelectItem[] getFacilityOptions() {
        facilityOptions = new SelectItem[4];
        facilityOptions[0] = new SelectItem("", "---Select One---");
        facilityOptions[1] = new SelectItem("Health Center", "Health Center");
        facilityOptions[2] = new SelectItem("Mortuary", "Mortuary");
        facilityOptions[3] = new SelectItem("Others", "Others");
        return facilityOptions;
    }

    public void setFacilityOptions(SelectItem[] facilityOptions) {
        this.facilityOptions = facilityOptions;
    }

    public SelectItem[] getRelationshipOptions() {
        relationshipOptions = new SelectItem[6];
        relationshipOptions[0] = new SelectItem("", "---Select One---");
        relationshipOptions[1] = new SelectItem("Mother", "Mum");
        relationshipOptions[2] = new SelectItem("Father", "Dad");
        relationshipOptions[3] = new SelectItem("Brother", "Brother");
        relationshipOptions[4] = new SelectItem("Sister", "Sister");
        relationshipOptions[5] = new SelectItem("Others", "Others");
        return relationshipOptions;
    }

    public void setRelationshipOptions(SelectItem[] relationshipOptions) {
        this.relationshipOptions = relationshipOptions;
    }

    public SelectItem[] getDistrictOptions() {
        String region_Id = userAccessController.getSystemUser().getDistrict().getRegion().getRegionId();
        districtOptions = new SelectItem[districtFacade.districtFindByRegion(region_Id, true).size() + 1];
        districtOptions[0] = new SelectItem("", "---Select One---");
        int c = 1;

        try {
            for (District d : districtFacade.districtFindByRegion(region_Id, true)) {
                districtOptions[c] = new SelectItem(d.getDistrictId(), d.getDistrictName());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtOptions;
    }

    public void setDistrictOptions(SelectItem[] districtOptions) {
        this.districtOptions = districtOptions;
    }

    public SelectItem[] getDistrictCenterOptions() {
        String district_Id = userAccessController.getLoginUser().getDistrict();
        districtCenterOptions = new SelectItem[districtCenterFacade.distictCenterFindByDistrictId(false, district_Id).size() + 1];
        districtCenterOptions[0] = new SelectItem("null", "---Select One---");
        int c = 1;

        try {
            for (DistrictCenter d : districtCenterFacade.distictCenterFindByDistrictId(false, district_Id)) {
                districtCenterOptions[c] = new SelectItem(d.getCenterId(), d.getCenterName());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return districtCenterOptions;
    }

    public void setDistrictCenterOptions(SelectItem[] districtCenterOptions) {
        this.districtCenterOptions = districtCenterOptions;
    }

    public SelectItem[] getWorkTypeOptions() {
        workTypeOptions = new SelectItem[userRoleFacade.roleGetAll(false).size() + 1];
        workTypeOptions[0] = new SelectItem("", "---Select One---");
        int c = 1;

        try {
            for (UserRole d : userRoleFacade.roleGetAll(false)) {
                workTypeOptions[c] = new SelectItem(d.getRoleId(), d.getRoleName());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workTypeOptions;
    }

    public void setWorkTypeOptions(SelectItem[] workTypeOptions) {
        this.workTypeOptions = workTypeOptions;
    }

    public SelectItem[] getActiveStatusOptions() {
        activeStatusOptions = new SelectItem[3];
        activeStatusOptions[0] = new SelectItem("", "---Select One---");
        activeStatusOptions[1] = new SelectItem("Active", "Active");
        activeStatusOptions[2] = new SelectItem("Inactive", "Inactive");
        return activeStatusOptions;
    }

    public void setActiveStatusOptions(SelectItem[] activeStatusOptions) {
        this.activeStatusOptions = activeStatusOptions;
    }

    public SelectItem[] getInformantRelation() {
        informantRelation = new SelectItem[5];
        informantRelation[0] = new SelectItem("", "---Select One---");
        informantRelation[1] = new SelectItem("Father", "Father");
        informantRelation[2] = new SelectItem("Mother", "Mother");
        informantRelation[3] = new SelectItem("Self", "Self");
        informantRelation[4] = new SelectItem("Other", "Other(Specify)");
        return informantRelation;
    }

    public void setInformantRelation(SelectItem[] informantRelation) {
        this.informantRelation = informantRelation;
    }

    public SelectItem[] getPlaceOfBirth() {
        placeOfBirth = new SelectItem[6];
        placeOfBirth[0] = new SelectItem("", "---Select One---");
        placeOfBirth[1] = new SelectItem("Hospital", "Hospital");
        placeOfBirth[2] = new SelectItem("Clinic", "Clinic");
        placeOfBirth[3] = new SelectItem("Maternity Home", "Maternity Home");
        placeOfBirth[4] = new SelectItem("House", "House");
        placeOfBirth[5] = new SelectItem("Others", "Others");
        return placeOfBirth;
    }

    public void setPlaceOfBirth(SelectItem[] placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public SelectItem[] getPlaceOfDeath() {
        placeOfDeath = new SelectItem[6];
        placeOfDeath[0] = new SelectItem("", "---Select One---");
        placeOfDeath[1] = new SelectItem("Hospital", "Hospital");
        placeOfDeath[2] = new SelectItem("Clinic", "Clinic");
        placeOfDeath[3] = new SelectItem("House", "House");
        placeOfDeath[4] = new SelectItem("Spiritual/Traditional Home", "Spiritual/Traditional Home");
        placeOfDeath[5] = new SelectItem("Others", "Others");
        return placeOfDeath;
    }

    public SelectItem[] getSecurityQuestions() {
        securityQuestions = new SelectItem[securityQuestionFacade.questionGetAll(true).size() + 1];
        securityQuestions[0] = new SelectItem("", "---Select One---");

        int c = 1;

        try {
            for (SecurityQuestion sq : securityQuestionFacade.questionGetAll(true)) {
                securityQuestions[c] = new SelectItem(sq.getQuestionId(), sq.getQuestion());
                c++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return securityQuestions;
    }

    public void setSecurityQuestions(SelectItem[] securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public SelectItem[] getDaysOfMonth() {
        daysOfMonth = new SelectItem[32];
        daysOfMonth[0] = new SelectItem("", "-DD-");
        for (int i = 1; i <= 31; i++) {
            daysOfMonth[i] = new SelectItem(i, Integer.toString(i));
        }
        return daysOfMonth;
    }

    public void setDaysOfMonth(SelectItem[] daysOfMonth) {
        this.daysOfMonth = daysOfMonth;
    }

    public SelectItem[] getMonthsOfYear() {
        monthsOfYear = new SelectItem[13];
        String[] months = new DateFormatSymbols().getMonths();
        monthsOfYear[0] = new SelectItem("", "-MM-");
        for (int i = 1; i <= 12; i++) {
            monthsOfYear[i] = new SelectItem(i, months[i]);
        }
        return monthsOfYear;
    }

    public void setMonthsOfYear(SelectItem[] monthsOfYear) {
        this.monthsOfYear = monthsOfYear;
    }

    public SelectItem[] getCalenderYears() {
        Calendar c = Calendar.getInstance();
        System.out.println(Calendar.getInstance().get(Calendar.YEAR) - 1970);
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
        int startYr = 1970;
        calenderYears = new SelectItem[(Calendar.getInstance().get(Calendar.YEAR) - 1970) + 2];
        calenderYears[0] = new SelectItem("", "-YY-");
        for (int i = 1; i <= (Calendar.getInstance().get(Calendar.YEAR) - 1970) + 1; i++) {
            calenderYears[i] = new SelectItem(startYr, Integer.toString(startYr));
            startYr++;
        }
        return calenderYears;
    }

    public void setCalenderYears(SelectItem[] calenderYears) {
        this.calenderYears = calenderYears;
    }

    public void setPlaceOfDeath(SelectItem[] placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public UserAccessController getUserAccessController() {
        return userAccessController;
    }

    public void setUserAccessController(UserAccessController userAccessController) {
        this.userAccessController = userAccessController;
    }

    public String getDistCenter() {
        return distCenter;
    }

    public void setDistCenter(String distCenter) {
        this.distCenter = distCenter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DistrictFacade getDistrictFacade() {
        return districtFacade;
    }

    public void setDistrictFacade(DistrictFacade districtFacade) {
        this.districtFacade = districtFacade;
    }

    public DistrictCenterFacade getDistrictCenterFacade() {
        return districtCenterFacade;
    }

    public void setDistrictCenterFacade(DistrictCenterFacade districtCenterFacade) {
        this.districtCenterFacade = districtCenterFacade;
    }

    public UserRoleFacade getUserRoleFacade() {
        return userRoleFacade;
    }

    public void setUserRoleFacade(UserRoleFacade userRoleFacade) {
        this.userRoleFacade = userRoleFacade;
    }

}
