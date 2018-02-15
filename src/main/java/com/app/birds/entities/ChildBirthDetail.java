/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "child_birth_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChildBirthDetail.findAll", query = "SELECT c FROM ChildBirthDetail c")
    , @NamedQuery(name = "ChildBirthDetail.findByBirthId", query = "SELECT c FROM ChildBirthDetail c WHERE c.birthId = :birthId")
    , @NamedQuery(name = "ChildBirthDetail.findBySurname", query = "SELECT c FROM ChildBirthDetail c WHERE c.surname = :surname")
    , @NamedQuery(name = "ChildBirthDetail.findByOthername", query = "SELECT c FROM ChildBirthDetail c WHERE c.othername = :othername")
    , @NamedQuery(name = "ChildBirthDetail.findByFullName", query = "SELECT c FROM ChildBirthDetail c WHERE c.fullName = :fullName")
    , @NamedQuery(name = "ChildBirthDetail.findByGender", query = "SELECT c FROM ChildBirthDetail c WHERE c.gender = :gender")
    , @NamedQuery(name = "ChildBirthDetail.findByDateOfBirth", query = "SELECT c FROM ChildBirthDetail c WHERE c.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "ChildBirthDetail.findByPlaceOfBirth", query = "SELECT c FROM ChildBirthDetail c WHERE c.placeOfBirth = :placeOfBirth")
    , @NamedQuery(name = "ChildBirthDetail.findByTownDelivered", query = "SELECT c FROM ChildBirthDetail c WHERE c.townDelivered = :townDelivered")
    , @NamedQuery(name = "ChildBirthDetail.findByDateOfRegistration", query = "SELECT c FROM ChildBirthDetail c WHERE c.dateOfRegistration = :dateOfRegistration")
    , @NamedQuery(name = "ChildBirthDetail.findByTimeOfRegistration", query = "SELECT c FROM ChildBirthDetail c WHERE c.timeOfRegistration = :timeOfRegistration")
    , @NamedQuery(name = "ChildBirthDetail.findByCenterRegistered", query = "SELECT c FROM ChildBirthDetail c WHERE c.centerRegistered = :centerRegistered")
    , @NamedQuery(name = "ChildBirthDetail.findByDistrictApproved", query = "SELECT c FROM ChildBirthDetail c WHERE c.districtApproved = :districtApproved")
    , @NamedQuery(name = "ChildBirthDetail.findByRegionalApproved", query = "SELECT c FROM ChildBirthDetail c WHERE c.regionalApproved = :regionalApproved")
    , @NamedQuery(name = "ChildBirthDetail.findByUpdated", query = "SELECT c FROM ChildBirthDetail c WHERE c.updated = :updated")
    , @NamedQuery(name = "ChildBirthDetail.findByDeleted", query = "SELECT c FROM ChildBirthDetail c WHERE c.deleted = :deleted")})
public class ChildBirthDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "birth_id")
    private String birthId;
    @Size(max = 70)
    @Column(name = "surname")
    private String surname;
    @Size(max = 70)
    @Column(name = "othername")
    private String othername;
    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 50)
    @Column(name = "place_of_birth")
    private String placeOfBirth;
    @Size(max = 50)
    @Column(name = "town_delivered")
    private String townDelivered;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
    @Column(name = "time_of_registration")
    @Temporal(TemporalType.TIME)
    private Date timeOfRegistration;
    @Size(max = 70)
    @Column(name = "center_registered")
    private String centerRegistered;
    @Size(max = 10)
    @Column(name = "district_approved")
    private String districtApproved;
    @Size(max = 10)
    @Column(name = "regional_approved")
    private String regionalApproved;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "birthApplicantId")
    private List<BirthCertRequest> birthCertRequestList;
    @JoinColumn(name = "guardian", referencedColumnName = "guardian_id")
    @ManyToOne
    private ChildGuardian guardian;
    @JoinColumn(name = "informant_birth", referencedColumnName = "informant_birth_id")
    @ManyToOne
    private InformantBirth informantBirth;
    @JoinColumn(name = "system_user", referencedColumnName = "system_user_id")
    @ManyToOne
    private SystemUser systemUser;

    public ChildBirthDetail() {
    }

    public ChildBirthDetail(String birthId) {
        this.birthId = birthId;
    }

    public String getBirthId() {
        return birthId;
    }

    public void setBirthId(String birthId) {
        this.birthId = birthId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOthername() {
        return othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getTownDelivered() {
        return townDelivered;
    }

    public void setTownDelivered(String townDelivered) {
        this.townDelivered = townDelivered;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Date getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(Date timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    public String getCenterRegistered() {
        return centerRegistered;
    }

    public void setCenterRegistered(String centerRegistered) {
        this.centerRegistered = centerRegistered;
    }

    public String getDistrictApproved() {
        return districtApproved;
    }

    public void setDistrictApproved(String districtApproved) {
        this.districtApproved = districtApproved;
    }

    public String getRegionalApproved() {
        return regionalApproved;
    }

    public void setRegionalApproved(String regionalApproved) {
        this.regionalApproved = regionalApproved;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public List<BirthCertRequest> getBirthCertRequestList() {
        return birthCertRequestList;
    }

    public void setBirthCertRequestList(List<BirthCertRequest> birthCertRequestList) {
        this.birthCertRequestList = birthCertRequestList;
    }

    public ChildGuardian getGuardian() {
        return guardian;
    }

    public void setGuardian(ChildGuardian guardian) {
        this.guardian = guardian;
    }

    public InformantBirth getInformantBirth() {
        return informantBirth;
    }

    public void setInformantBirth(InformantBirth informantBirth) {
        this.informantBirth = informantBirth;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (birthId != null ? birthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChildBirthDetail)) {
            return false;
        }
        ChildBirthDetail other = (ChildBirthDetail) object;
        if ((this.birthId == null && other.birthId != null) || (this.birthId != null && !this.birthId.equals(other.birthId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.ChildBirthDetail[ birthId=" + birthId + " ]";
    }
    
}
