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
@Table(name = "death_cert_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeathCertRequest.findAll", query = "SELECT d FROM DeathCertRequest d")
    , @NamedQuery(name = "DeathCertRequest.findByDeathCertRequestId", query = "SELECT d FROM DeathCertRequest d WHERE d.deathCertRequestId = :deathCertRequestId")
    , @NamedQuery(name = "DeathCertRequest.findBySystemUser", query = "SELECT d FROM DeathCertRequest d WHERE d.systemUser = :systemUser")
    , @NamedQuery(name = "DeathCertRequest.findByDistrictApproved", query = "SELECT d FROM DeathCertRequest d WHERE d.districtApproved = :districtApproved")
    , @NamedQuery(name = "DeathCertRequest.findByRegionalApproved", query = "SELECT d FROM DeathCertRequest d WHERE d.regionalApproved = :regionalApproved")
    , @NamedQuery(name = "DeathCertRequest.findByCertPrinted", query = "SELECT d FROM DeathCertRequest d WHERE d.certPrinted = :certPrinted")
    , @NamedQuery(name = "DeathCertRequest.findByUpdated", query = "SELECT d FROM DeathCertRequest d WHERE d.updated = :updated")
    , @NamedQuery(name = "DeathCertRequest.findByDeleted", query = "SELECT d FROM DeathCertRequest d WHERE d.deleted = :deleted")})
public class DeathCertRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "death_cert_request_id")
    private String deathCertRequestId;
    @Size(max = 70)
    @Column(name = "system_user")
    private String systemUser;
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
    @JoinColumn(name = "deceased_details", referencedColumnName = "deceased_id")
    @ManyToOne
    private DeceasedDetail deceasedDetails;
    @JoinColumn(name = "district", referencedColumnName = "district_id")
    @ManyToOne
    private District district;

    public DeathCertRequest() {
    }

    public DeathCertRequest(String deathCertRequestId) {
        this.deathCertRequestId = deathCertRequestId;
    }

    public String getDeathCertRequestId() {
        return deathCertRequestId;
    }

    public void setDeathCertRequestId(String deathCertRequestId) {
        this.deathCertRequestId = deathCertRequestId;
    }

    public String getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(String systemUser) {
        this.systemUser = systemUser;
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

    public DeceasedDetail getDeceasedDetails() {
        return deceasedDetails;
    }

    public void setDeceasedDetails(DeceasedDetail deceasedDetails) {
        this.deceasedDetails = deceasedDetails;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deathCertRequestId != null ? deathCertRequestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeathCertRequest)) {
            return false;
        }
        DeathCertRequest other = (DeathCertRequest) object;
        if ((this.deathCertRequestId == null && other.deathCertRequestId != null) || (this.deathCertRequestId != null && !this.deathCertRequestId.equals(other.deathCertRequestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.DeathCertRequest[ deathCertRequestId=" + deathCertRequestId + " ]";
    }
    
}
