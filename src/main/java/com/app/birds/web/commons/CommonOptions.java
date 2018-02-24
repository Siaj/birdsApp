/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.SelectItem;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "common_options")
@SessionScoped
public class CommonOptions implements Serializable {

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
    private UserAccessController userAccessController = new UserAccessController();
    private String distCenter = userAccessController.getUserAccount().getSystemUser().getDistrict().getDistrictId();
    int count;

//    Class Constructor 
    public CommonOptions() {
    }
    
    
    public void saveBirthDummy(){
        System.out.println("Checking to see if validation works!!!");
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
        burialStatusOptions[0] = new SelectItem("", "---Select One---");
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
        return districtOptions;
    }

    public void setDistrictOptions(SelectItem[] districtOptions) {
        this.districtOptions = districtOptions;
    }

    public SelectItem[] getDistrictCenterOptions() {
        return districtCenterOptions;
    }

    public void setDistrictCenterOptions(SelectItem[] districtCenterOptions) {
        this.districtCenterOptions = districtCenterOptions;
    }

    public SelectItem[] getWorkTypeOptions() {
//        Get from database later
        workTypeOptions = new SelectItem[4];
        workTypeOptions[0] = new SelectItem("", "---Select One---");
        workTypeOptions[1] = new SelectItem("Regional Administrator", "Regional Administrator");
        workTypeOptions[2] = new SelectItem("District Administrator", "District Administrator");
        workTypeOptions[3] = new SelectItem("Registrar", "Registrar");
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

}
