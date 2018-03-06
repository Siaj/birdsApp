/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.DeceasedDetail;
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
public class DeceasedDetailFacade extends AbstractFacade<DeceasedDetail> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeceasedDetailFacade() {
        super(DeceasedDetail.class);
    }

    public String deceasedDetailCreate(DeceasedDetail deceasedDetail) {
        try {

            deceasedDetail.setDeleted("NO");
            deceasedDetail.setUpdated("NO");
            super.create(deceasedDetail);
            return deceasedDetail.getDeceasedId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean deceasedDetailDelete(DeceasedDetail deceasedDetail, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(deceasedDetail);
            } else if (permanent == false) {
                deceasedDetail.setDeleted("YES");
                deceasedDetail.setUpdated("NO");
                super.edit(deceasedDetail);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean deceasedDetailUpdate(DeceasedDetail deceasedDetail) {
        try {

            deceasedDetail.setDeleted("NO");
            deceasedDetail.setUpdated("NO");
            super.edit(deceasedDetail);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public DeceasedDetail deceasedDetailFind(String deceasedId) {
        try {

            return super.find(deceasedId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DeceasedDetail> deceasedDetailFindByAttribute(String deceasedDetailAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<DeceasedDetail> listOfDeceasedDetail = null;

        String qryString;

        qryString = "SELECT e FROM DeceasedDetail e ";
        qryString += "WHERE e." + deceasedDetailAttribute + " =:deceasedDetailAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfDeceasedDetail = (List<DeceasedDetail>) getEntityManager().createQuery(qryString).setParameter("deceasedDetailAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM DeceasedDetail e ";
                qryString += "WHERE e." + deceasedDetailAttribute + " LIKE '%" + attributeValue + "%' AND e.districtApproved = 'YES' AND e.regionalApproved = 'YES'";
                listOfDeceasedDetail = (List<DeceasedDetail>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfDeceasedDetail = (List<DeceasedDetail>) getEntityManager().createQuery(qryString).setParameter("deceasedDetailAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfDeceasedDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeceasedDetail> deceasedDetailGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<DeceasedDetail> listOfDeceasedDetail = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfDeceasedDetail = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DeceasedDetail e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfDeceasedDetail = (List<DeceasedDetail>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDeceasedDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeceasedDetail> deceasedDetailGetAll(boolean includeLogicallyDeleted) {
        List<DeceasedDetail> listOfDeceasedDetail = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfDeceasedDetail = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DeceasedDetail e WHERE e.deleted = 'NO'";
                listOfDeceasedDetail = (List<DeceasedDetail>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDeceasedDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
