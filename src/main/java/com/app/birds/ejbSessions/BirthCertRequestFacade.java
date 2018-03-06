/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.BirthCertRequest;
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
public class BirthCertRequestFacade extends AbstractFacade<BirthCertRequest> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BirthCertRequestFacade() {
        super(BirthCertRequest.class);
    }

    public String birthCertRequestCreate(BirthCertRequest birthCertRequest) {
        try {

            birthCertRequest.setDeleted("NO");
            birthCertRequest.setUpdated("NO");
            super.create(birthCertRequest);
            return birthCertRequest.getBirthCertRequestId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean birthCertRequestDelete(BirthCertRequest birthCertRequest, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(birthCertRequest);
            } else if (permanent == false) {
                birthCertRequest.setDeleted("YES");
                birthCertRequest.setUpdated("NO");
                super.edit(birthCertRequest);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean birthCertRequestUpdate(BirthCertRequest birthCertRequest) {
        try {

            birthCertRequest.setDeleted("NO");
            birthCertRequest.setUpdated("NO");
            super.edit(birthCertRequest);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public BirthCertRequest birthCertRequestFind(String birthCertRequestId) {
        try {

            return super.find(birthCertRequestId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BirthCertRequest> birthCertRequestFindByAttribute(String birthCertRequestAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<BirthCertRequest> listOfBirthCertRequest = null;

        String qryString;

        qryString = "SELECT e FROM BirthCertRequest e ";
        qryString += "WHERE e." + birthCertRequestAttribute + " =:birthCertRequestAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfBirthCertRequest = (List<BirthCertRequest>) getEntityManager().createQuery(qryString).setParameter("birthCertRequestAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM BirthCertRequest e ";
                qryString += "WHERE e." + birthCertRequestAttribute + " LIKE '%" + attributeValue + "%'";
                listOfBirthCertRequest = (List<BirthCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfBirthCertRequest = (List<BirthCertRequest>) getEntityManager().createQuery(qryString).setParameter("birthCertRequestAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfBirthCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BirthCertRequest> birthCertRequestGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<BirthCertRequest> listOfBirthCertRequest = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfBirthCertRequest = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM BirthCertRequest e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfBirthCertRequest = (List<BirthCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfBirthCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BirthCertRequest> birthCertRequestGetAll(boolean includeLogicallyDeleted) {
        List<BirthCertRequest> listOfBirthCertRequest = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfBirthCertRequest = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM BirthCertRequest e WHERE e.deleted = 'NO'";
                listOfBirthCertRequest = (List<BirthCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfBirthCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
