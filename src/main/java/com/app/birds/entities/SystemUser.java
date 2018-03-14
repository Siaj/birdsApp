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
@Table(name = "system_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SystemUser.findAll", query = "SELECT s FROM SystemUser s")
    ,@NamedQuery(name = SystemUser.FIND_BY_USERNAME_PASSWORD, query = "SELECT s FROM SystemUser s WHERE s.username = :username AND s.password = :password")
    , @NamedQuery(name = "SystemUser.findBySystemUserId", query = "SELECT s FROM SystemUser s WHERE s.systemUserId = :systemUserId")
    , @NamedQuery(name = "SystemUser.findByLastName", query = "SELECT s FROM SystemUser s WHERE s.lastName = :lastName")
    , @NamedQuery(name = "SystemUser.findByMiddleName", query = "SELECT s FROM SystemUser s WHERE s.middleName = :middleName")
    , @NamedQuery(name = "SystemUser.findByFirstName", query = "SELECT s FROM SystemUser s WHERE s.firstName = :firstName")
    , @NamedQuery(name = "SystemUser.findByUsername", query = "SELECT s FROM SystemUser s WHERE s.username = :username")
    , @NamedQuery(name = "SystemUser.findByPassword", query = "SELECT s FROM SystemUser s WHERE s.password = :password")
    , @NamedQuery(name = "SystemUser.findByAccountStatus", query = "SELECT s FROM SystemUser s WHERE s.accountStatus = :accountStatus")
    , @NamedQuery(name = "SystemUser.findByGender", query = "SELECT s FROM SystemUser s WHERE s.gender = :gender")
    , @NamedQuery(name = "SystemUser.findByPhoneNumber", query = "SELECT s FROM SystemUser s WHERE s.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "SystemUser.findByPlaceOfResidence", query = "SELECT s FROM SystemUser s WHERE s.placeOfResidence = :placeOfResidence")
    , @NamedQuery(name = "SystemUser.findByEmail", query = "SELECT s FROM SystemUser s WHERE s.email = :email")
    , @NamedQuery(name = "SystemUser.findByNextOfKin", query = "SELECT s FROM SystemUser s WHERE s.nextOfKin = :nextOfKin")
    , @NamedQuery(name = "SystemUser.findByNextOfKinGender", query = "SELECT s FROM SystemUser s WHERE s.nextOfKinGender = :nextOfKinGender")
    , @NamedQuery(name = "SystemUser.findByNextOfKinContact", query = "SELECT s FROM SystemUser s WHERE s.nextOfKinContact = :nextOfKinContact")
    , @NamedQuery(name = "SystemUser.findByRelationship", query = "SELECT s FROM SystemUser s WHERE s.relationship = :relationship")
    , @NamedQuery(name = "SystemUser.findByUpdated", query = "SELECT s FROM SystemUser s WHERE s.updated = :updated")
    , @NamedQuery(name = "SystemUser.findByDeleted", query = "SELECT s FROM SystemUser s WHERE s.deleted = :deleted")})
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_BY_USERNAME_PASSWORD = "UserAccount.FIND_BY_USERNAME_PASSWORD";

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "system_user_id")
    private String systemUserId;
    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 50)
    @Column(name = "middle_name")
    private String middleName;
    @Size(max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 70)
    @Column(name = "username")
    private String username;
    @Size(max = 70)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "account_status")
    private String accountStatus;
    @Size(max = 10)
    @Column(name = "gender")
    private String gender;
    @Size(max = 50)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 70)
    @Column(name = "place_of_residence")
    private String placeOfResidence;
    @Lob
    @Size(max = 65535)
    @Column(name = "physical_location")
    private String physicalLocation;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 70)
    @Column(name = "email")
    private String email;
    @Size(max = 70)
    @Column(name = "next_of_kin")
    private String nextOfKin;
    @Size(max = 10)
    @Column(name = "next_of_kin_gender")
    private String nextOfKinGender;
    @Size(max = 50)
    @Column(name = "next_of_kin_contact")
    private String nextOfKinContact;
    @Size(max = 50)
    @Column(name = "relationship")
    private String relationship;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "systemUser")
    private List<DeceasedDetail> deceasedDetailList;
    @OneToMany(mappedBy = "systemUser")
    private List<ChildBirthDetail> childBirthDetailList;
    @JoinColumn(name = "district", referencedColumnName = "district_id")
    @ManyToOne
    private District district;
    @JoinColumn(name = "user_role", referencedColumnName = "role_id")
    @ManyToOne
    private UserRole userRole;
    @JoinColumn(name = "district_center", referencedColumnName = "center_id")
    @ManyToOne
    private DistrictCenter districtCenter;

    public SystemUser() {
    }

    public SystemUser(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(String systemUserId) {
        this.systemUserId = systemUserId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getNextOfKinGender() {
        return nextOfKinGender;
    }

    public void setNextOfKinGender(String nextOfKinGender) {
        this.nextOfKinGender = nextOfKinGender;
    }

    public String getNextOfKinContact() {
        return nextOfKinContact;
    }

    public void setNextOfKinContact(String nextOfKinContact) {
        this.nextOfKinContact = nextOfKinContact;
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

    @XmlTransient
    public List<ChildBirthDetail> getChildBirthDetailList() {
        return childBirthDetailList;
    }

    public void setChildBirthDetailList(List<ChildBirthDetail> childBirthDetailList) {
        this.childBirthDetailList = childBirthDetailList;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public DistrictCenter getDistrictCenter() {
        return districtCenter;
    }

    public void setDistrictCenter(DistrictCenter districtCenter) {
        this.districtCenter = districtCenter;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (systemUserId != null ? systemUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemUser)) {
            return false;
        }
        SystemUser other = (SystemUser) object;
        if ((this.systemUserId == null && other.systemUserId != null) || (this.systemUserId != null && !this.systemUserId.equals(other.systemUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.SystemUser[ systemUserId=" + systemUserId + " ]";
    }

}
