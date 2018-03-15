/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "district")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "District.findAll", query = "SELECT d FROM District d")
    , @NamedQuery(name = "District.findByDistrictId", query = "SELECT d FROM District d WHERE d.districtId = :districtId")
    , @NamedQuery(name = "District.findByDistrictName", query = "SELECT d FROM District d WHERE d.districtName = :districtName")
    , @NamedQuery(name = "District.findByPrimaryContact", query = "SELECT d FROM District d WHERE d.primaryContact = :primaryContact")
    , @NamedQuery(name = "District.findBySecondayContact", query = "SELECT d FROM District d WHERE d.secondayContact = :secondayContact")
    , @NamedQuery(name = "District.findByUpdated", query = "SELECT d FROM District d WHERE d.updated = :updated")
    , @NamedQuery(name = "District.findByDeleted", query = "SELECT d FROM District d WHERE d.deleted = :deleted")})
public class District implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "district_id")
    private String districtId;
    @Size(max = 50)
    @Column(name = "district_name")
    private String districtName;
    @Size(max = 50)
    @Column(name = "primary_contact")
    private String primaryContact;
    @Size(max = 50)
    @Column(name = "seconday_contact")
    private String secondayContact;
    @Lob
    @Size(max = 65535)
    @Column(name = "physical_location")
    private String physicalLocation;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "districtUnder")
    private List<DistrictCenter> districtCenterList;
    @OneToMany(mappedBy = "district")
    private List<DeathCertRequest> deathCertRequestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "district")
    private List<BirthCertRequest> birthCertRequestList;
    @JoinColumn(name = "region", referencedColumnName = "region_id")
    @ManyToOne
    private Region region;
    @OneToMany(mappedBy = "district")
    private List<SystemUser> systemUserList;

    public District() {
    }

    public District(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getSecondayContact() {
        return secondayContact;
    }

    public void setSecondayContact(String secondayContact) {
        this.secondayContact = secondayContact;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
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
    public List<DistrictCenter> getDistrictCenterList() {
        return districtCenterList;
    }

    public void setDistrictCenterList(List<DistrictCenter> districtCenterList) {
        this.districtCenterList = districtCenterList;
    }

    @XmlTransient
    public List<DeathCertRequest> getDeathCertRequestList() {
        return deathCertRequestList;
    }

    public void setDeathCertRequestList(List<DeathCertRequest> deathCertRequestList) {
        this.deathCertRequestList = deathCertRequestList;
    }

    @XmlTransient
    public List<BirthCertRequest> getBirthCertRequestList() {
        return birthCertRequestList;
    }

    public void setBirthCertRequestList(List<BirthCertRequest> birthCertRequestList) {
        this.birthCertRequestList = birthCertRequestList;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @XmlTransient
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (districtId != null ? districtId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof District)) {
            return false;
        }
        District other = (District) object;
        if ((this.districtId == null && other.districtId != null) || (this.districtId != null && !this.districtId.equals(other.districtId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.District[ districtId=" + districtId + " ]";
    }
    
}
