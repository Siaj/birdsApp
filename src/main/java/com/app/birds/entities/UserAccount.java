/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Entity
@Table(name = "user_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u")
    , @NamedQuery(name = "UserAccount.findByUserAccountId", query = "SELECT u FROM UserAccount u WHERE u.userAccountId = :userAccountId")
    , @NamedQuery(name = "UserAccount.findByUsername", query = "SELECT u FROM UserAccount u WHERE u.username = :username")
    , @NamedQuery(name = "UserAccount.findByPassword", query = "SELECT u FROM UserAccount u WHERE u.password = :password")
    , @NamedQuery(name = UserAccount.FIND_BY_USERNAME_PASSWORD, query = "select u from UserAccount u where u.username=:username AND u.password=:password")
    , @NamedQuery(name = "UserAccount.findByPasswordSalt", query = "SELECT u FROM UserAccount u WHERE u.passwordSalt = :passwordSalt")
    , @NamedQuery(name = "UserAccount.findByAccountStatus", query = "SELECT u FROM UserAccount u WHERE u.accountStatus = :accountStatus")
    , @NamedQuery(name = "UserAccount.findBySecurityQuestion", query = "SELECT u FROM UserAccount u WHERE u.securityQuestion = :securityQuestion")
    , @NamedQuery(name = "UserAccount.findBySecurityAnswer", query = "SELECT u FROM UserAccount u WHERE u.securityAnswer = :securityAnswer")
    , @NamedQuery(name = "UserAccount.findByUpdated", query = "SELECT u FROM UserAccount u WHERE u.updated = :updated")
    , @NamedQuery(name = "UserAccount.findByDeleted", query = "SELECT u FROM UserAccount u WHERE u.deleted = :deleted")})
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String FIND_BY_USERNAME_PASSWORD = "UserAccount.FIND_BY_USERNAME_PASSWORD";
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "user_account_id")
    private String userAccountId;
    @Size(max = 50)
    @Column(name = "username")
    private String username;
    @Size(max = 100)
    @Column(name = "password")
    private String password;
    @Size(max = 50)
    @Column(name = "password_salt")
    private String passwordSalt;
    @Size(max = 10)
    @Column(name = "account_status")
    private String accountStatus;
    @Size(max = 70)
    @Column(name = "security_question")
    private String securityQuestion;
    @Size(max = 70)
    @Column(name = "security_answer")
    private String securityAnswer;
    @Size(max = 10)
    @Column(name = "updated")
    private String updated;
    @Size(max = 10)
    @Column(name = "deleted")
    private String deleted;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccount")
    private SystemUser systemUser;

    public UserAccount() {
    }

    public UserAccount(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
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

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAccountId != null ? userAccountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.userAccountId == null && other.userAccountId != null) || (this.userAccountId != null && !this.userAccountId.equals(other.userAccountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.UserAccount[ userAccountId=" + userAccountId + " ]";
    }

}
