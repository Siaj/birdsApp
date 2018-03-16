/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.detailedClass;

import com.app.birds.entities.DeathCertRequest;
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
public class DeceasedDetailClass implements Serializable {

    private String deceased_id;
    private String entry_number;
    private String deceased_name;
    private String deceased_gender;
    private Integer age;
    private String nationality;
    private String place_of_deceased;
    private String place_of_death;
    private Date date_of_death;
    private String cause_of_death;
    private String margin;
    private Date date_of_registration;
    private String informant_name;
    private String relationship;
    private String place_of_burial;
    private String town_of_burial;
    private String sign_of_regis;
    private String district;
    private SystemUser systemUser = new SystemUser();

    public DeceasedDetailClass() {
    }

    public List<DeceasedDetailClass> loadDeceased(DeathCertRequest d, SystemUser systemUser) {
        String deceased_number = d.getDeceasedDetails().getDeceasedId();

        String split_id[] = deceased_number.split("-");
        String district_code = split_id[0];
        String entry_num = split_id[1];

        // String district_id = d.getDeceasedDetail().getSystemUser().getDistrict().getDistrictId();
        //systemUser = sessionBeansLocal.getDistrictAdministrator(district_id);
        List<DeceasedDetailClass> deceasedDetailList = new ArrayList<>();
        DeceasedDetailClass deceasedDetailedClass = new DeceasedDetailClass();

        // for (DeathCertificateRequest d : deceasedCertList) {
        deceasedDetailedClass.setAge(d.getDeceasedDetails().getAgeOfDeath());
        deceasedDetailedClass.setCause_of_death(d.getDeceasedDetails().getCauseOfDeath());
        deceasedDetailedClass.setDate_of_death(d.getDeceasedDetails().getDateOfDeath());
        deceasedDetailedClass.setDate_of_registration(d.getDeceasedDetails().getDateOfRegistration());
        deceasedDetailedClass.setDeceased_gender(d.getDeceasedDetails().getGender());
        deceasedDetailedClass.setDeceased_name(d.getDeceasedDetails().getDeceasedFullname());
        deceasedDetailedClass.setDistrict(systemUser.getDistrict().getDistrictName());
        deceasedDetailedClass.setEntry_number(entry_num);
        deceasedDetailedClass.setInformant_name(d.getDeceasedDetails().getInformantDeath().getInformantName());
        deceasedDetailedClass.setNationality(d.getDeceasedDetails().getNationality());
        deceasedDetailedClass.setPlace_of_burial(d.getDeceasedDetails().getPlaceOfBurial());
        deceasedDetailedClass.setPlace_of_death(d.getDeceasedDetails().getPlaceOfDeath());
        deceasedDetailedClass.setPlace_of_deceased(d.getDeceasedDetails().getDeceasedResidence());
        deceasedDetailedClass.setRelationship(d.getDeceasedDetails().getInformantDeath().getRelationship());
        deceasedDetailedClass.setSign_of_regis(systemUser.getFirstName());
        deceasedDetailedClass.setTown_of_burial(d.getDeceasedDetails().getAddressOfBurialPlace());
        deceasedDetailedClass.setDeceased_id(d.getDeceasedDetails().getDeceasedId());

        deceasedDetailList.add(deceasedDetailedClass);
        // }

        return deceasedDetailList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCause_of_death() {
        return cause_of_death;
    }

    public void setCause_of_death(String cause_of_death) {
        this.cause_of_death = cause_of_death;
    }

    public Date getDate_of_death() {
        return date_of_death;
    }

    public void setDate_of_death(Date date_of_death) {
        this.date_of_death = date_of_death;
    }

    public Date getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(Date date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public String getDeceased_gender() {
        return deceased_gender;
    }

    public void setDeceased_gender(String deceased_gender) {
        this.deceased_gender = deceased_gender;
    }

    public String getDeceased_id() {
        return deceased_id;
    }

    public void setDeceased_id(String deceased_id) {
        this.deceased_id = deceased_id;
    }

    public String getDeceased_name() {
        return deceased_name;
    }

    public void setDeceased_name(String deceased_name) {
        this.deceased_name = deceased_name;
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

    public String getInformant_name() {
        return informant_name;
    }

    public void setInformant_name(String informant_name) {
        this.informant_name = informant_name;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlace_of_burial() {
        return place_of_burial;
    }

    public void setPlace_of_burial(String place_of_burial) {
        this.place_of_burial = place_of_burial;
    }

    public String getPlace_of_death() {
        return place_of_death;
    }

    public void setPlace_of_death(String place_of_death) {
        this.place_of_death = place_of_death;
    }

    public String getPlace_of_deceased() {
        return place_of_deceased;
    }

    public void setPlace_of_deceased(String place_of_deceased) {
        this.place_of_deceased = place_of_deceased;
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

    public String getTown_of_burial() {
        return town_of_burial;
    }

    public void setTown_of_burial(String town_of_burial) {
        this.town_of_burial = town_of_burial;
    }

}
