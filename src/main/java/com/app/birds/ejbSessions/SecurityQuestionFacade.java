/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.District;
import com.app.birds.entities.SecurityQuestion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class SecurityQuestionFacade extends AbstractFacade<SecurityQuestion> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SecurityQuestionFacade() {
        super(SecurityQuestion.class);
    }

    public String securityQuestionCreate(SecurityQuestion question) {
        try {

            question.setDeleted("NO");
            question.setUpdated("NO");
            super.create(question);
            return question.getQuestionId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean secQuestionDelete(SecurityQuestion question, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(question);
            } else if (permanent == false) {
                question.setDeleted("YES");
                question.setUpdated("NO");
                super.edit(question);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean secQuestionUpdate(SecurityQuestion question) {
        try {

            question.setDeleted("NO");
            question.setUpdated("NO");
            super.edit(question);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public SecurityQuestion secQuestionFind(String questionId) {
        try {
            return super.find(questionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SecurityQuestion> questionFindByAttribute(String questionAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<SecurityQuestion> listOfQuestions = null;

        String qryString;

        qryString = "SELECT e FROM SecurityQuestion e ";
        qryString += "WHERE e." + questionAttribute + " =:questionAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfQuestions = (List<SecurityQuestion>) getEntityManager().createQuery(qryString).setParameter("questionAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SecurityQuestion e ";
                qryString += "WHERE e." + questionAttribute + " LIKE '%" + attributeValue + "%'";
                listOfQuestions = (List<SecurityQuestion>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfQuestions = (List<SecurityQuestion>) getEntityManager().createQuery(qryString).setParameter("questionAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfQuestions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SecurityQuestion> secQuestionGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<SecurityQuestion> listOfQuestions = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfQuestions = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SecurityQuestion e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfQuestions = (List<SecurityQuestion>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfQuestions;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SecurityQuestion> questionGetAll(boolean includeLogicallyDeleted) {
        List<SecurityQuestion> listOfQuestion = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfQuestion = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SecurityQuestion e WHERE e.deleted = 'NO'";
                listOfQuestion = (List<SecurityQuestion>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfQuestion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
