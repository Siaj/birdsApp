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
import javax.persistence.Lob;
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
@Table(name = "security_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SecurityQuestion.findAll", query = "SELECT s FROM SecurityQuestion s")
    , @NamedQuery(name = "SecurityQuestion.findByQuestionId", query = "SELECT s FROM SecurityQuestion s WHERE s.questionId = :questionId")
    , @NamedQuery(name = "SecurityQuestion.findByUpdated", query = "SELECT s FROM SecurityQuestion s WHERE s.updated = :updated")
    , @NamedQuery(name = "SecurityQuestion.findByDeleted", query = "SELECT s FROM SecurityQuestion s WHERE s.deleted = :deleted")})
public class SecurityQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 70)
    @Column(name = "question_id")
    private String questionId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "question")
    private String question;
    @Size(max = 70)
    @Column(name = "updated")
    private String updated;
    @Size(max = 70)
    @Column(name = "deleted")
    private String deleted;
    @OneToMany(mappedBy = "securityQuestion")
    private List<UserAccount> userAccountList;

    public SecurityQuestion() {
    }

    public SecurityQuestion(String questionId) {
        this.questionId = questionId;
    }

    public SecurityQuestion(String questionId, String question) {
        this.questionId = questionId;
        this.question = question;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SecurityQuestion)) {
            return false;
        }
        SecurityQuestion other = (SecurityQuestion) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.birds.entities.SecurityQuestion[ questionId=" + questionId + " ]";
    }
    
}
