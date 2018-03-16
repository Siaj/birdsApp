/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.detailedClass;

import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.SystemUser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author USER
 */
@ManagedBean
@SessionScoped
public class BirthDetailedClass implements Serializable {

    private String child_birth_id;
    private String child_name;
    private String entry_number;
    private String gender;
    private Date date_of_birth;
    private String town_delivered;
    private Date date_of_registration;
    private String mother_name;
    private String mum_nationality;
    private String father_name;
    private String father_nationality;
    private String informant_name;
    private String relationship;
    private String sign_of_regis;
    private String district;
    private String district_center;
    // private String margin;
    private SystemUser systemUser = new SystemUser();

    public BirthDetailedClass() {
    }

    public List<BirthDetailedClass> loadBirth(BirthCertRequest c, SystemUser systemUser) {
        String birth_id = c.getBirthApplicantId().getBirthId();

        String split_id[] = birth_id.split("-");
        String district_code = split_id[0];
        String entry_num = split_id[1];

        //     District districta = c.getBirthApplicantId().getSystemUser().getDistrict();
//        systemUser = sessionBeansLocal.getDistrictAdministrator(districta);
        // System.out.println("System User is>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+systemUser);
        List<BirthDetailedClass> allChildDetails = new ArrayList<>();
        BirthDetailedClass childDetailedClass = new BirthDetailedClass();

        //for(ChildComplete c : child){
        childDetailedClass.setChild_birth_id(c.getBirthApplicantId().getBirthId());
        childDetailedClass.setEntry_number(entry_num);
        childDetailedClass.setChild_name(c.getBirthApplicantId().getFullName());
        childDetailedClass.setGender(c.getBirthApplicantId().getGender());
        childDetailedClass.setDate_of_birth(c.getBirthApplicantId().getDateOfBirth());
        childDetailedClass.setDate_of_registration(c.getBirthApplicantId().getDateOfRegistration());
        childDetailedClass.setDistrict(systemUser.getDistrict().getDistrictName());
        childDetailedClass.setDistrict_center(systemUser.getDistrictCenter().getCenterName());
        childDetailedClass.setFather_name(c.getBirthApplicantId().getGuardian().getDadFullname());
        childDetailedClass.setFather_nationality(c.getBirthApplicantId().getGuardian().getDadNationality());
        childDetailedClass.setInformant_name(c.getBirthApplicantId().getInformantBirth().getInformantName());
        childDetailedClass.setMother_name(c.getBirthApplicantId().getGuardian().getMumFullname());
        childDetailedClass.setMum_nationality(c.getBirthApplicantId().getGuardian().getMumNationality());
        childDetailedClass.setSign_of_regis(systemUser.getFirstName());
        childDetailedClass.setTown_delivered(c.getBirthApplicantId().getTownDelivered());
        childDetailedClass.setRelationship(c.getBirthApplicantId().getInformantBirth().getRelationship());

        allChildDetails.add(childDetailedClass);

        return allChildDetails;
    }

    public String getChild_birth_id() {
        return child_birth_id;
    }

    public void setChild_birth_id(String child_birth_id) {
        this.child_birth_id = child_birth_id;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getDistrict_center() {
        return district_center;
    }

    public void setDistrict_center(String district_center) {
        this.district_center = district_center;
    }

    public Date getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(Date date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEntry_number() {
        return entry_number;
    }

    public void setEntry_number(String entry_number) {
        this.entry_number = entry_number;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public String getFather_nationality() {
        return father_nationality;
    }

    public void setFather_nationality(String father_nationality) {
        this.father_nationality = father_nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getInformant_name() {
        return informant_name;
    }

    public void setInformant_name(String informant_name) {
        this.informant_name = informant_name;
    }

    public String getMother_name() {
        return mother_name;
    }

    public void setMother_name(String mother_name) {
        this.mother_name = mother_name;
    }

    public String getMum_nationality() {
        return mum_nationality;
    }

    public void setMum_nationality(String mum_nationality) {
        this.mum_nationality = mum_nationality;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSign_of_regis() {
        return sign_of_regis;
    }

    public void setSign_of_regis(String sign_of_regis) {
        this.sign_of_regis = sign_of_regis;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public String getTown_delivered() {
        return town_delivered;
    }

    public void setTown_delivered(String town_delivered) {
        this.town_delivered = town_delivered;
    }
}
