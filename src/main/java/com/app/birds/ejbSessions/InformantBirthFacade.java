/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.InformantBirth;
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
public class InformantBirthFacade extends AbstractFacade<InformantBirth> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformantBirthFacade() {
        super(InformantBirth.class);
    }

    public String informantBirthCreate(InformantBirth informantBirth) {
        try {

            informantBirth.setDeleted("NO");
            informantBirth.setUpdated("NO");
            super.create(informantBirth);
            return informantBirth.getInformantBirthId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean informantBirthDelete(InformantBirth informantBirth, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(informantBirth);
            } else if (permanent == false) {
                informantBirth.setDeleted("YES");
                informantBirth.setUpdated("NO");
                super.edit(informantBirth);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean informantBirthUpdate(InformantBirth informantBirth) {
        try {

            informantBirth.setDeleted("NO");
            informantBirth.setUpdated("NO");
            super.edit(informantBirth);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public InformantBirth informantBirthFind(String informantBirthId) {
        try {

            return super.find(informantBirthId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<InformantBirth> informantBirthFindByAttribute(String informantBirthAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<InformantBirth> listOfInformantBirth = null;

        String qryString;

        qryString = "SELECT e FROM InformantBirth e ";
        qryString += "WHERE e." + informantBirthAttribute + " =:informantBirthAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfInformantBirth = (List<InformantBirth>) getEntityManager().createQuery(qryString).setParameter("informantBirthAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM InformantBirth e ";
                qryString += "WHERE e." + informantBirthAttribute + " LIKE '%" + attributeValue + "%'";
                listOfInformantBirth = (List<InformantBirth>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfInformantBirth = (List<InformantBirth>) getEntityManager().createQuery(qryString).setParameter("informantBirthAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfInformantBirth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<InformantBirth> informantBirthGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<InformantBirth> listOfInformantBirth = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfInformantBirth = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM InformantBirth e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfInformantBirth = (List<InformantBirth>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfInformantBirth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<InformantBirth> informantBirthGetAll(boolean includeLogicallyDeleted) {
        List<InformantBirth> listOfInformantBirth = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfInformantBirth = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM InformantBirth e WHERE e.deleted = 'NO'";
                listOfInformantBirth = (List<InformantBirth>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfInformantBirth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
