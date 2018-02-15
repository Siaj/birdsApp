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
@Table(name = "informant_death")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformantDeath.findAll", query = "SELECT i FROM InformantDeath i")
    , @NamedQuery(name = "InformantDeath.findByInformantDeathId", query = "SELECT i FROM InformantDeath i WHERE i.informantDeathId = :informantDeathId")
    , @NamedQuery(name = "InformantDeath.findByInformantName", query = "SELECT i FROM InformantDeath i WHERE i.informantName = :informantName")
    , @NamedQuery(name = "InformantDeath.findByInformantAddress", query = "SELECT i FROM InformantDeath i WHERE i.informantAddress = :informantAddress")
    , @NamedQuery(name = "InformantDeath.findByInformantContact", query = "SELECT i FROM InformantDeath i WHERE i.informantContact = :informantContact")
    , @NamedQuery(name = "InformantDeath.findByIdType", query = "SELECT i FROM InformantDeath i WHERE i.idType = :idType")
    , @NamedQuery(name = "InformantDeath.findByIdNum", query = "SELECT i FROM InformantDeath i WHERE i.idNum = :idNum")
    , @NamedQuery(name = "InformantDeath.findByRelationship", query = "SELECT i FROM InformantDeath i WHERE i.relationship = :relationship")
    , @NamedQuery(name = "InformantDeath.findByUpdated", query = "SELECT i FROM InformantDeath i WHERE i.updated = :updated")
    , @NamedQuery(name = "InformantDeath.findByDeleted", query = "SELECT i FROM InformantDeath i WHERE i.deleted = :deleted")})
public class InformantDeath implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "informant_death_id")
    private String informantDeathId;
    @Size(max = 100)
    @Column(name = "informant_name")
    private String informantName;
    @Size(max = 100)
    @Column(name = "informant_address")
    private String informantAddress;
    @Size(max = 50)
    @Column(name = "informant_contact")
    private String informantContact;
    @Size(max = 50)
    @Column(name = "id_type")
    private String idType;
    @Size(max = 70)
    @Column(name = "id_num")
    private String idNum;
    @Size(max = 70)
    @Column(name = "relationship")
    private String relationship;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "informantDeath")
    private List<DeceasedDetail> deceasedDetailList;

    public InformantDeath() {
    }

    public InformantDeath(String informantDeathId) {
        this.informantDeathId = informantDeathId;
    }

    public String getInformantDeathId() {
        return informantDeathId;
    }

    public void setInformantDeathId(String informantDeathId) {
        this.informantDeathId = informantDeathId;
    }

    public String getInformantName() {
        return informantName;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public String getInformantAddress() {
        return informantAddress;
    }

    public void setInformantAddress(String informantAddress) {
        this.informantAddress = informantAddress;
    }

    public String getInformantContact() {
        return informantContact;
    }

    public void setInformantContact(String informantContact) {
        this.informantContact = informantContact;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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
    public List<DeceasedDetail> getDeceasedDetailList() {
        return deceasedDetailList;
    }

    public void setDeceasedDetailList(List<DeceasedDetail> deceasedDetailList) {
        this.deceasedDetailList = deceasedDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informantDeathId != null ? informantDeathId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformantDeath)) {
            return false;
        }
        InformantDeath other = (InformantDeath) object;
        if ((this.informantDeathId == null && other.informantDeathId != null) || (this.informantDeathId != null && !this.informantDeathId.equals(other.informantDeathId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.InformantDeath[ informantDeathId=" + informantDeathId + " ]";
    }
    
}
