/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "birth_cert_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BirthCertRequest.findAll", query = "SELECT b FROM BirthCertRequest b")
    , @NamedQuery(name = "BirthCertRequest.findByBirthCertRequestId", query = "SELECT b FROM BirthCertRequest b WHERE b.birthCertRequestId = :birthCertRequestId")
    , @NamedQuery(name = "BirthCertRequest.findBySystemUserId", query = "SELECT b FROM BirthCertRequest b WHERE b.systemUserId = :systemUserId")
    , @NamedQuery(name = "BirthCertRequest.findByDistrict", query = "SELECT b FROM BirthCertRequest b WHERE b.district = :district")
    , @NamedQuery(name = "BirthCertRequest.findByDistrictApproved", query = "SELECT b FROM BirthCertRequest b WHERE b.districtApproved = :districtApproved")
    , @NamedQuery(name = "BirthCertRequest.findByRegionalApproved", query = "SELECT b FROM BirthCertRequest b WHERE b.regionalApproved = :regionalApproved")
    , @NamedQuery(name = "BirthCertRequest.findByCertPrinted", query = "SELECT b FROM BirthCertRequest b WHERE b.certPrinted = :certPrinted")
    , @NamedQuery(name = "BirthCertRequest.findByUpdated", query = "SELECT b FROM BirthCertRequest b WHERE b.updated = :updated")
    , @NamedQuery(name = "BirthCertRequest.findByDeleted", query = "SELECT b FROM BirthCertRequest b WHERE b.deleted = :deleted")})
public class BirthCertRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "birth_cert_request_id")
    private String birthCertRequestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "system_user_id")
    private String systemUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "district")
    private String district;
    @Size(max = 10)
    @Column(name = "district_approved")
    private String districtApproved;
    @Size(max = 10)
    @Column(name = "regional_approved")
    private String regionalApproved;
    @Size(max = 10)
    @Column(name = "cert_printed")
    private String certPrinted;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @JoinColumn(name = "birth_applicant_id", referencedColumnName = "birth_id")
    @ManyToOne(optional = false)
    private ChildBirthDetail birthApplicantId;

    public BirthCertRequest() {
    }

    public BirthCertRequest(String birthCertRequestId) {
        this.birthCertRequestId = birthCertRequestId;
    }

    public BirthCertRequest(String birthCertRequestId, String systemUserId, String district) {
        this.birthCertRequestId = birthCertRequestId;
        this.systemUserId = systemUserId;
        this.district = district;
    }

    public String getBirthCertRequestId() {
        return birthCertRequestId;
    }

    public void setBirthCertRequestId(String birthCertRequestId) {
        this.birthCertRequestId = birthCertRequestId;
    }

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public String getCertPrinted() {
        return certPrinted;
    }

    public void setCertPrinted(String certPrinted) {
        this.certPrinted = certPrinted;
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

    public ChildBirthDetail getBirthApplicantId() {
        return birthApplicantId;
    }

    public void setBirthApplicantId(ChildBirthDetail birthApplicantId) {
        this.birthApplicantId = birthApplicantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (birthCertRequestId != null ? birthCertRequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BirthCertRequest)) {
            return false;
        }
        BirthCertRequest other = (BirthCertRequest) object;
        if ((this.birthCertRequestId == null && other.birthCertRequestId != null) || (this.birthCertRequestId != null && !this.birthCertRequestId.equals(other.birthCertRequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.BirthCertRequest[ birthCertRequestId=" + birthCertRequestId + " ]";
    }
    
}
