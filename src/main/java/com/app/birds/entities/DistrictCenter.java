/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "district_center")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistrictCenter.findAll", query = "SELECT d FROM DistrictCenter d")
    , @NamedQuery(name = "DistrictCenter.findByCenterId", query = "SELECT d FROM DistrictCenter d WHERE d.centerId = :centerId")
    , @NamedQuery(name = "DistrictCenter.findByCenterName", query = "SELECT d FROM DistrictCenter d WHERE d.centerName = :centerName")
    , @NamedQuery(name = "DistrictCenter.findByCenterType", query = "SELECT d FROM DistrictCenter d WHERE d.centerType = :centerType")
    , @NamedQuery(name = "DistrictCenter.findByCenterLocation", query = "SELECT d FROM DistrictCenter d WHERE d.centerLocation = :centerLocation")
    , @NamedQuery(name = "DistrictCenter.findByPrimaryContact", query = "SELECT d FROM DistrictCenter d WHERE d.primaryContact = :primaryContact")
    , @NamedQuery(name = "DistrictCenter.findBySecondaryContact", query = "SELECT d FROM DistrictCenter d WHERE d.secondaryContact = :secondaryContact")
    , @NamedQuery(name = "DistrictCenter.findByUpdated", query = "SELECT d FROM DistrictCenter d WHERE d.updated = :updated")
    , @NamedQuery(name = "DistrictCenter.findByDeleted", query = "SELECT d FROM DistrictCenter d WHERE d.deleted = :deleted")})
public class DistrictCenter implements Serializable {

    @OneToMany(mappedBy = "districtCenter")
    private List<SystemUser> systemUserList;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "center_id")
    private String centerId;
    @Size(max = 50)
    @Column(name = "center_name")
    private String centerName;
    @Size(max = 50)
    @Column(name = "center_type")
    private String centerType;
    @Size(max = 50)
    @Column(name = "center_location")
    private String centerLocation;
    @Size(max = 50)
    @Column(name = "primary_contact")
    private String primaryContact;
    @Size(max = 50)
    @Column(name = "secondary_contact")
    private String secondaryContact;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @JoinColumn(name = "district_under", referencedColumnName = "district_id")
    @ManyToOne
    private District districtUnder;

    public DistrictCenter() {
    }

    public DistrictCenter(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterType() {
        return centerType;
    }

    public void setCenterType(String centerType) {
        this.centerType = centerType;
    }

    public String getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getSecondaryContact() {
        return secondaryContact;
    }

    public void setSecondaryContact(String secondaryContact) {
        this.secondaryContact = secondaryContact;
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

    public District getDistrictUnder() {
        return districtUnder;
    }

    public void setDistrictUnder(District districtUnder) {
        this.districtUnder = districtUnder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (centerId != null ? centerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistrictCenter)) {
            return false;
        }
        DistrictCenter other = (DistrictCenter) object;
        if ((this.centerId == null && other.centerId != null) || (this.centerId != null && !this.centerId.equals(other.centerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.DistrictCenter[ centerId=" + centerId + " ]";
    }

    @XmlTransient
    public List<SystemUser> getSystemUserList() {
        return systemUserList;
    }

    public void setSystemUserList(List<SystemUser> systemUserList) {
        this.systemUserList = systemUserList;
    }
    
}
