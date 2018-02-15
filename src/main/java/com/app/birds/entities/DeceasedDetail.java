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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "deceased_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeceasedDetail.findAll", query = "SELECT d FROM DeceasedDetail d")
    , @NamedQuery(name = "DeceasedDetail.findByDeceasedId", query = "SELECT d FROM DeceasedDetail d WHERE d.deceasedId = :deceasedId")
    , @NamedQuery(name = "DeceasedDetail.findBySurname", query = "SELECT d FROM DeceasedDetail d WHERE d.surname = :surname")
    , @NamedQuery(name = "DeceasedDetail.findByOthername", query = "SELECT d FROM DeceasedDetail d WHERE d.othername = :othername")
    , @NamedQuery(name = "DeceasedDetail.findByDeceasedFullname", query = "SELECT d FROM DeceasedDetail d WHERE d.deceasedFullname = :deceasedFullname")
    , @NamedQuery(name = "DeceasedDetail.findByGender", query = "SELECT d FROM DeceasedDetail d WHERE d.gender = :gender")
    , @NamedQuery(name = "DeceasedDetail.findByDateOfBirth", query = "SELECT d FROM DeceasedDetail d WHERE d.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "DeceasedDetail.findByNationality", query = "SELECT d FROM DeceasedDetail d WHERE d.nationality = :nationality")
    , @NamedQuery(name = "DeceasedDetail.findByIdType", query = "SELECT d FROM DeceasedDetail d WHERE d.idType = :idType")
    , @NamedQuery(name = "DeceasedDetail.findByIdNum", query = "SELECT d FROM DeceasedDetail d WHERE d.idNum = :idNum")
    , @NamedQuery(name = "DeceasedDetail.findByDateOfDeath", query = "SELECT d FROM DeceasedDetail d WHERE d.dateOfDeath = :dateOfDeath")
    , @NamedQuery(name = "DeceasedDetail.findByAgeOfDeath", query = "SELECT d FROM DeceasedDetail d WHERE d.ageOfDeath = :ageOfDeath")
    , @NamedQuery(name = "DeceasedDetail.findByPlaceOfBurial", query = "SELECT d FROM DeceasedDetail d WHERE d.placeOfBurial = :placeOfBurial")
    , @NamedQuery(name = "DeceasedDetail.findByAddressOfBurialPlace", query = "SELECT d FROM DeceasedDetail d WHERE d.addressOfBurialPlace = :addressOfBurialPlace")
    , @NamedQuery(name = "DeceasedDetail.findByPlaceOfDeath", query = "SELECT d FROM DeceasedDetail d WHERE d.placeOfDeath = :placeOfDeath")
    , @NamedQuery(name = "DeceasedDetail.findByAddressOfDeathPlace", query = "SELECT d FROM DeceasedDetail d WHERE d.addressOfDeathPlace = :addressOfDeathPlace")
    , @NamedQuery(name = "DeceasedDetail.findByCauseOfDeath", query = "SELECT d FROM DeceasedDetail d WHERE d.causeOfDeath = :causeOfDeath")
    , @NamedQuery(name = "DeceasedDetail.findByRegistrationType", query = "SELECT d FROM DeceasedDetail d WHERE d.registrationType = :registrationType")
    , @NamedQuery(name = "DeceasedDetail.findByDateOfRegistration", query = "SELECT d FROM DeceasedDetail d WHERE d.dateOfRegistration = :dateOfRegistration")
    , @NamedQuery(name = "DeceasedDetail.findByParentName", query = "SELECT d FROM DeceasedDetail d WHERE d.parentName = :parentName")
    , @NamedQuery(name = "DeceasedDetail.findByParentRelation", query = "SELECT d FROM DeceasedDetail d WHERE d.parentRelation = :parentRelation")
    , @NamedQuery(name = "DeceasedDetail.findByParentIdType", query = "SELECT d FROM DeceasedDetail d WHERE d.parentIdType = :parentIdType")
    , @NamedQuery(name = "DeceasedDetail.findByParentIdNum", query = "SELECT d FROM DeceasedDetail d WHERE d.parentIdNum = :parentIdNum")
    , @NamedQuery(name = "DeceasedDetail.findByParentNationality", query = "SELECT d FROM DeceasedDetail d WHERE d.parentNationality = :parentNationality")
    , @NamedQuery(name = "DeceasedDetail.findByCenterRegistered", query = "SELECT d FROM DeceasedDetail d WHERE d.centerRegistered = :centerRegistered")
    , @NamedQuery(name = "DeceasedDetail.findByDistrictApproved", query = "SELECT d FROM DeceasedDetail d WHERE d.districtApproved = :districtApproved")
    , @NamedQuery(name = "DeceasedDetail.findByRegionalApproved", query = "SELECT d FROM DeceasedDetail d WHERE d.regionalApproved = :regionalApproved")
    , @NamedQuery(name = "DeceasedDetail.findByUpdated", query = "SELECT d FROM DeceasedDetail d WHERE d.updated = :updated")
    , @NamedQuery(name = "DeceasedDetail.findByDeleted", query = "SELECT d FROM DeceasedDetail d WHERE d.deleted = :deleted")})
public class DeceasedDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "deceased_id")
    private String deceasedId;
    @Size(max = 50)
    @Column(name = "surname")
    private String surname;
    @Size(max = 50)
    @Column(name = "othername")
    private String othername;
    @Size(max = 100)
    @Column(name = "deceased_fullname")
    private String deceasedFullname;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Lob
    @Size(max = 65535)
    @Column(name = "deceased_residence")
    private String deceasedResidence;
    @Size(max = 50)
    @Column(name = "nationality")
    private String nationality;
    @Size(max = 50)
    @Column(name = "id_type")
    private String idType;
    @Size(max = 70)
    @Column(name = "id_num")
    private String idNum;
    @Column(name = "date_of_death")
    @Temporal(TemporalType.DATE)
    private Date dateOfDeath;
    @Column(name = "age_of_death")
    private Integer ageOfDeath;
    @Size(max = 50)
    @Column(name = "place_of_burial")
    private String placeOfBurial;
    @Size(max = 100)
    @Column(name = "address_of_burial_place")
    private String addressOfBurialPlace;
    @Size(max = 100)
    @Column(name = "place_of_death")
    private String placeOfDeath;
    @Size(max = 100)
    @Column(name = "address_of_death_place")
    private String addressOfDeathPlace;
    @Size(max = 100)
    @Column(name = "cause_of_death")
    private String causeOfDeath;
    @Size(max = 70)
    @Column(name = "registration_type")
    private String registrationType;
    @Column(name = "date_of_registration")
    @Temporal(TemporalType.DATE)
    private Date dateOfRegistration;
    @Size(max = 100)
    @Column(name = "parent_name")
    private String parentName;
    @Size(max = 50)
    @Column(name = "parent_relation")
    private String parentRelation;
    @Size(max = 50)
    @Column(name = "parent_id_type")
    private String parentIdType;
    @Size(max = 70)
    @Column(name = "parent_id_num")
    private String parentIdNum;
    @Size(max = 50)
    @Column(name = "parent_nationality")
    private String parentNationality;
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
    @JoinColumn(name = "system_user", referencedColumnName = "system_user_id")
    @ManyToOne
    private SystemUser systemUser;
    @JoinColumn(name = "informant_death", referencedColumnName = "informant_death_id")
    @ManyToOne
    private InformantDeath informantDeath;
    @OneToMany(mappedBy = "deceasedDetails")
    private List<DeathCertRequest> deathCertRequestList;

    public DeceasedDetail() {
    }

    public DeceasedDetail(String deceasedId) {
        this.deceasedId = deceasedId;
    }

    public String getDeceasedId() {
        return deceasedId;
    }

    public void setDeceasedId(String deceasedId) {
        this.deceasedId = deceasedId;
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

    public String getDeceasedFullname() {
        return deceasedFullname;
    }

    public void setDeceasedFullname(String deceasedFullname) {
        this.deceasedFullname = deceasedFullname;
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

    public String getDeceasedResidence() {
        return deceasedResidence;
    }

    public void setDeceasedResidence(String deceasedResidence) {
        this.deceasedResidence = deceasedResidence;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public Integer getAgeOfDeath() {
        return ageOfDeath;
    }

    public void setAgeOfDeath(Integer ageOfDeath) {
        this.ageOfDeath = ageOfDeath;
    }

    public String getPlaceOfBurial() {
        return placeOfBurial;
    }

    public void setPlaceOfBurial(String placeOfBurial) {
        this.placeOfBurial = placeOfBurial;
    }

    public String getAddressOfBurialPlace() {
        return addressOfBurialPlace;
    }

    public void setAddressOfBurialPlace(String addressOfBurialPlace) {
        this.addressOfBurialPlace = addressOfBurialPlace;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getAddressOfDeathPlace() {
        return addressOfDeathPlace;
    }

    public void setAddressOfDeathPlace(String addressOfDeathPlace) {
        this.addressOfDeathPlace = addressOfDeathPlace;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }

    public String getParentIdType() {
        return parentIdType;
    }

    public void setParentIdType(String parentIdType) {
        this.parentIdType = parentIdType;
    }

    public String getParentIdNum() {
        return parentIdNum;
    }

    public void setParentIdNum(String parentIdNum) {
        this.parentIdNum = parentIdNum;
    }

    public String getParentNationality() {
        return parentNationality;
    }

    public void setParentNationality(String parentNationality) {
        this.parentNationality = parentNationality;
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

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public InformantDeath getInformantDeath() {
        return informantDeath;
    }

    public void setInformantDeath(InformantDeath informantDeath) {
        this.informantDeath = informantDeath;
    }

    @XmlTransient
    public List<DeathCertRequest> getDeathCertRequestList() {
        return deathCertRequestList;
    }

    public void setDeathCertRequestList(List<DeathCertRequest> deathCertRequestList) {
        this.deathCertRequestList = deathCertRequestList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deceasedId != null ? deceasedId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeceasedDetail)) {
            return false;
        }
        DeceasedDetail other = (DeceasedDetail) object;
        if ((this.deceasedId == null && other.deceasedId != null) || (this.deceasedId != null && !this.deceasedId.equals(other.deceasedId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.DeceasedDetail[ deceasedId=" + deceasedId + " ]";
    }
    
}
