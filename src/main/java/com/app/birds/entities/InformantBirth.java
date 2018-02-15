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
@Table(name = "informant_birth")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformantBirth.findAll", query = "SELECT i FROM InformantBirth i")
    , @NamedQuery(name = "InformantBirth.findByInformantBirthId", query = "SELECT i FROM InformantBirth i WHERE i.informantBirthId = :informantBirthId")
    , @NamedQuery(name = "InformantBirth.findByInformantName", query = "SELECT i FROM InformantBirth i WHERE i.informantName = :informantName")
    , @NamedQuery(name = "InformantBirth.findByRelationship", query = "SELECT i FROM InformantBirth i WHERE i.relationship = :relationship")
    , @NamedQuery(name = "InformantBirth.findByIdType", query = "SELECT i FROM InformantBirth i WHERE i.idType = :idType")
    , @NamedQuery(name = "InformantBirth.findByIdNum", query = "SELECT i FROM InformantBirth i WHERE i.idNum = :idNum")
    , @NamedQuery(name = "InformantBirth.findByResidence", query = "SELECT i FROM InformantBirth i WHERE i.residence = :residence")
    , @NamedQuery(name = "InformantBirth.findByUpdated", query = "SELECT i FROM InformantBirth i WHERE i.updated = :updated")
    , @NamedQuery(name = "InformantBirth.findByDeleted", query = "SELECT i FROM InformantBirth i WHERE i.deleted = :deleted")})
public class InformantBirth implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "informant_birth_id")
    private String informantBirthId;
    @Size(max = 100)
    @Column(name = "informant_name")
    private String informantName;
    @Size(max = 50)
    @Column(name = "relationship")
    private String relationship;
    @Size(max = 50)
    @Column(name = "id_type")
    private String idType;
    @Size(max = 70)
    @Column(name = "id_num")
    private String idNum;
    @Size(max = 100)
    @Column(name = "residence")
    private String residence;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "informantBirth")
    private List<ChildBirthDetail> childBirthDetailList;

    public InformantBirth() {
    }

    public InformantBirth(String informantBirthId) {
        this.informantBirthId = informantBirthId;
    }

    public String getInformantBirthId() {
        return informantBirthId;
    }

    public void setInformantBirthId(String informantBirthId) {
        this.informantBirthId = informantBirthId;
    }

    public String getInformantName() {
        return informantName;
    }

    public void setInformantName(String informantName) {
        this.informantName = informantName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
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
    public List<ChildBirthDetail> getChildBirthDetailList() {
        return childBirthDetailList;
    }

    public void setChildBirthDetailList(List<ChildBirthDetail> childBirthDetailList) {
        this.childBirthDetailList = childBirthDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (informantBirthId != null ? informantBirthId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformantBirth)) {
            return false;
        }
        InformantBirth other = (InformantBirth) object;
        if ((this.informantBirthId == null && other.informantBirthId != null) || (this.informantBirthId != null && !this.informantBirthId.equals(other.informantBirthId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.InformantBirth[ informantBirthId=" + informantBirthId + " ]";
    }
    
}
