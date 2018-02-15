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
@Table(name = "gen_id")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GenId.findAll", query = "SELECT g FROM GenId g")
    , @NamedQuery(name = "GenId.findByGenTableId", query = "SELECT g FROM GenId g WHERE g.genTableId = :genTableId")
    , @NamedQuery(name = "GenId.findByDistrictCode", query = "SELECT g FROM GenId g WHERE g.districtCode = :districtCode")
    , @NamedQuery(name = "GenId.findByDistLastBirthNum", query = "SELECT g FROM GenId g WHERE g.distLastBirthNum = :distLastBirthNum")
    , @NamedQuery(name = "GenId.findByDistLastDeathNum", query = "SELECT g FROM GenId g WHERE g.distLastDeathNum = :distLastDeathNum")
    , @NamedQuery(name = "GenId.findByUpdated", query = "SELECT g FROM GenId g WHERE g.updated = :updated")
    , @NamedQuery(name = "GenId.findByDeleted", query = "SELECT g FROM GenId g WHERE g.deleted = :deleted")})
public class GenId implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "gen_table_id")
    private String genTableId;
    @Size(max = 70)
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "dist_last_birth_num")
    private Integer distLastBirthNum;
    @Column(name = "dist_last_death_num")
    private Integer distLastDeathNum;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;

    public GenId() {
    }

    public GenId(String genTableId) {
        this.genTableId = genTableId;
    }

    public String getGenTableId() {
        return genTableId;
    }

    public void setGenTableId(String genTableId) {
        this.genTableId = genTableId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getDistLastBirthNum() {
        return distLastBirthNum;
    }

    public void setDistLastBirthNum(Integer distLastBirthNum) {
        this.distLastBirthNum = distLastBirthNum;
    }

    public Integer getDistLastDeathNum() {
        return distLastDeathNum;
    }

    public void setDistLastDeathNum(Integer distLastDeathNum) {
        this.distLastDeathNum = distLastDeathNum;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (genTableId != null ? genTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GenId)) {
            return false;
        }
        GenId other = (GenId) object;
        if ((this.genTableId == null && other.genTableId != null) || (this.genTableId != null && !this.genTableId.equals(other.genTableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.GenId[ genTableId=" + genTableId + " ]";
    }
    
}
