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
@Table(name = "child_guardian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChildGuardian.findAll", query = "SELECT c FROM ChildGuardian c")
    , @NamedQuery(name = "ChildGuardian.findByGuardianId", query = "SELECT c FROM ChildGuardian c WHERE c.guardianId = :guardianId")
    , @NamedQuery(name = "ChildGuardian.findByMumSurname", query = "SELECT c FROM ChildGuardian c WHERE c.mumSurname = :mumSurname")
    , @NamedQuery(name = "ChildGuardian.findByMumOthername", query = "SELECT c FROM ChildGuardian c WHERE c.mumOthername = :mumOthername")
    , @NamedQuery(name = "ChildGuardian.findByMumFullname", query = "SELECT c FROM ChildGuardian c WHERE c.mumFullname = :mumFullname")
    , @NamedQuery(name = "ChildGuardian.findByMumAge", query = "SELECT c FROM ChildGuardian c WHERE c.mumAge = :mumAge")
    , @NamedQuery(name = "ChildGuardian.findByMumNationality", query = "SELECT c FROM ChildGuardian c WHERE c.mumNationality = :mumNationality")
    , @NamedQuery(name = "ChildGuardian.findByMumContact", query = "SELECT c FROM ChildGuardian c WHERE c.mumContact = :mumContact")
    , @NamedQuery(name = "ChildGuardian.findByMumIdType", query = "SELECT c FROM ChildGuardian c WHERE c.mumIdType = :mumIdType")
    , @NamedQuery(name = "ChildGuardian.findByMumIdNum", query = "SELECT c FROM ChildGuardian c WHERE c.mumIdNum = :mumIdNum")
    , @NamedQuery(name = "ChildGuardian.findByMumResidence", query = "SELECT c FROM ChildGuardian c WHERE c.mumResidence = :mumResidence")
    , @NamedQuery(name = "ChildGuardian.findByDadSurname", query = "SELECT c FROM ChildGuardian c WHERE c.dadSurname = :dadSurname")
    , @NamedQuery(name = "ChildGuardian.findByDadOthername", query = "SELECT c FROM ChildGuardian c WHERE c.dadOthername = :dadOthername")
    , @NamedQuery(name = "ChildGuardian.findByDadFullname", query = "SELECT c FROM ChildGuardian c WHERE c.dadFullname = :dadFullname")
    , @NamedQuery(name = "ChildGuardian.findByDadAge", query = "SELECT c FROM ChildGuardian c WHERE c.dadAge = :dadAge")
    , @NamedQuery(name = "ChildGuardian.findByDadNationality", query = "SELECT c FROM ChildGuardian c WHERE c.dadNationality = :dadNationality")
    , @NamedQuery(name = "ChildGuardian.findByDadIdType", query = "SELECT c FROM ChildGuardian c WHERE c.dadIdType = :dadIdType")
    , @NamedQuery(name = "ChildGuardian.findByDadIdNum", query = "SELECT c FROM ChildGuardian c WHERE c.dadIdNum = :dadIdNum")
    , @NamedQuery(name = "ChildGuardian.findByUpdated", query = "SELECT c FROM ChildGuardian c WHERE c.updated = :updated")
    , @NamedQuery(name = "ChildGuardian.findByDeleted", query = "SELECT c FROM ChildGuardian c WHERE c.deleted = :deleted")})
public class ChildGuardian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "guardian_id")
    private String guardianId;
    @Size(max = 50)
    @Column(name = "mum_surname")
    private String mumSurname;
    @Size(max = 50)
    @Column(name = "mum_othername")
    private String mumOthername;
    @Size(max = 100)
    @Column(name = "mum_fullname")
    private String mumFullname;
    @Column(name = "mum_age")
    private Integer mumAge;
    @Size(max = 50)
    @Column(name = "mum_nationality")
    private String mumNationality;
    @Size(max = 70)
    @Column(name = "mum_contact")
    private String mumContact;
    @Size(max = 50)
    @Column(name = "mum_id_type")
    private String mumIdType;
    @Size(max = 70)
    @Column(name = "mum_id_num")
    private String mumIdNum;
    @Size(max = 255)
    @Column(name = "mum_residence")
    private String mumResidence;
    @Size(max = 50)
    @Column(name = "dad_surname")
    private String dadSurname;
    @Size(max = 50)
    @Column(name = "dad_othername")
    private String dadOthername;
    @Size(max = 100)
    @Column(name = "dad_fullname")
    private String dadFullname;
    @Column(name = "dad_age")
    private Integer dadAge;
    @Size(max = 50)
    @Column(name = "dad_nationality")
    private String dadNationality;
    @Size(max = 50)
    @Column(name = "dad_id_type")
    private String dadIdType;
    @Size(max = 70)
    @Column(name = "dad_id_num")
    private String dadIdNum;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "guardian")
    private List<ChildBirthDetail> childBirthDetailList;

    public ChildGuardian() {
    }

    public ChildGuardian(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(String guardianId) {
        this.guardianId = guardianId;
    }

    public String getMumSurname() {
        return mumSurname;
    }

    public void setMumSurname(String mumSurname) {
        this.mumSurname = mumSurname;
    }

    public String getMumOthername() {
        return mumOthername;
    }

    public void setMumOthername(String mumOthername) {
        this.mumOthername = mumOthername;
    }

    public String getMumFullname() {
        return mumFullname;
    }

    public void setMumFullname(String mumFullname) {
        this.mumFullname = mumFullname;
    }

    public Integer getMumAge() {
        return mumAge;
    }

    public void setMumAge(Integer mumAge) {
        this.mumAge = mumAge;
    }

    public String getMumNationality() {
        return mumNationality;
    }

    public void setMumNationality(String mumNationality) {
        this.mumNationality = mumNationality;
    }

    public String getMumContact() {
        return mumContact;
    }

    public void setMumContact(String mumContact) {
        this.mumContact = mumContact;
    }

    public String getMumIdType() {
        return mumIdType;
    }

    public void setMumIdType(String mumIdType) {
        this.mumIdType = mumIdType;
    }

    public String getMumIdNum() {
        return mumIdNum;
    }

    public void setMumIdNum(String mumIdNum) {
        this.mumIdNum = mumIdNum;
    }

    public String getMumResidence() {
        return mumResidence;
    }

    public void setMumResidence(String mumResidence) {
        this.mumResidence = mumResidence;
    }

    public String getDadSurname() {
        return dadSurname;
    }

    public void setDadSurname(String dadSurname) {
        this.dadSurname = dadSurname;
    }

    public String getDadOthername() {
        return dadOthername;
    }

    public void setDadOthername(String dadOthername) {
        this.dadOthername = dadOthername;
    }

    public String getDadFullname() {
        return dadFullname;
    }

    public void setDadFullname(String dadFullname) {
        this.dadFullname = dadFullname;
    }

    public Integer getDadAge() {
        return dadAge;
    }

    public void setDadAge(Integer dadAge) {
        this.dadAge = dadAge;
    }

    public String getDadNationality() {
        return dadNationality;
    }

    public void setDadNationality(String dadNationality) {
        this.dadNationality = dadNationality;
    }

    public String getDadIdType() {
        return dadIdType;
    }

    public void setDadIdType(String dadIdType) {
        this.dadIdType = dadIdType;
    }

    public String getDadIdNum() {
        return dadIdNum;
    }

    public void setDadIdNum(String dadIdNum) {
        this.dadIdNum = dadIdNum;
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
        hash += (guardianId != null ? guardianId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChildGuardian)) {
            return false;
        }
        ChildGuardian other = (ChildGuardian) object;
        if ((this.guardianId == null && other.guardianId != null) || (this.guardianId != null && !this.guardianId.equals(other.guardianId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.ChildGuardian[ guardianId=" + guardianId + " ]";
    }
    
}
