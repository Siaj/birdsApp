/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.DeathCertRequest;
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
public class DeathCertRequestFacade extends AbstractFacade<DeathCertRequest> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeathCertRequestFacade() {
        super(DeathCertRequest.class);
    }

    public String deathCertRequestCreate(DeathCertRequest deathCertRequest) {
        try {

            deathCertRequest.setDeleted("NO");
            deathCertRequest.setUpdated("NO");
            super.create(deathCertRequest);
            return deathCertRequest.getDeathCertRequestId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean deathCertRequestDelete(DeathCertRequest deathCertRequest, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(deathCertRequest);
            } else if (permanent == false) {
                deathCertRequest.setDeleted("YES");
                deathCertRequest.setUpdated("NO");
                super.edit(deathCertRequest);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean deathCertRequestUpdate(DeathCertRequest deathCertRequest) {
        try {

            deathCertRequest.setDeleted("NO");
            deathCertRequest.setUpdated("NO");
            super.edit(deathCertRequest);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public DeathCertRequest deathCertRequestFind(String deathCertRequestId) {
        try {

            return super.find(deathCertRequestId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DeathCertRequest> deathCertRequestFindByAttribute(String deathCertRequestAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<DeathCertRequest> listOfDeathCertRequest = null;

        String qryString;

        qryString = "SELECT e FROM DeathCertRequest e ";
        qryString += "WHERE e." + deathCertRequestAttribute + " =:deathCertRequestAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfDeathCertRequest = (List<DeathCertRequest>) getEntityManager().createQuery(qryString).setParameter("deathCertRequestAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM DeathCertRequest e ";
                qryString += "WHERE e." + deathCertRequestAttribute + " LIKE '%" + attributeValue + "%'";
                listOfDeathCertRequest = (List<DeathCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfDeathCertRequest = (List<DeathCertRequest>) getEntityManager().createQuery(qryString).setParameter("deathCertRequestAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfDeathCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeathCertRequest> deathCertRequestGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<DeathCertRequest> listOfDeathCertRequest = null;

        String qryString = "";

        try {
            if (includeLogicallyDeleted == true) {
                listOfDeathCertRequest = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DeathCertRequest e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfDeathCertRequest = (List<DeathCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDeathCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeathCertRequest> deathCertRequestGetAll(boolean includeLogicallyDeleted) {
        List<DeathCertRequest> listOfDeathCertRequest = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfDeathCertRequest = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DeathCertRequest e WHERE e.deleted = 'NO'";
                listOfDeathCertRequest = (List<DeathCertRequest>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDeathCertRequest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
