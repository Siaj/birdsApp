/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.ChildBirthDetail;
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
public class ChildBirthDetailFacade extends AbstractFacade<ChildBirthDetail> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChildBirthDetailFacade() {
        super(ChildBirthDetail.class);
    }

    public String createBirthRegistration(ChildBirthDetail birthDetail) {
        try {
            birthDetail.setDeleted("NO");
            birthDetail.setUpdated("NO");
            super.create(birthDetail);

            return birthDetail.getBirthId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteBirthDetail(ChildBirthDetail birthDetail, boolean permanent) {
        try {
            if (permanent == true) {
                super.remove(birthDetail);
            } else if (permanent == false) {
                birthDetail.setDeleted("YES");
                birthDetail.setUpdated("NO");
                super.edit(birthDetail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean updateBirthDetails(ChildBirthDetail birthDetail) {
        try {
            birthDetail.setDeleted("NO");
            birthDetail.setUpdated("NO");
            super.edit(birthDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChildBirthDetail findBirthDetails(String birthId) {
        try {
            return super.find(birthId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChildBirthDetail> birthDetailFindByAttribute(String childBirthDetailAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<ChildBirthDetail> listOfChildBirthDetail = null;

        String qryString;

        qryString = "SELECT e FROM ChildBirthDetail e ";
        qryString += "WHERE e." + childBirthDetailAttribute + " =:childBirthDetailAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).setParameter("childBirthDetailAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM ChildBirthDetail e ";
                qryString += "WHERE e." + childBirthDetailAttribute + " LIKE '%" + attributeValue + "%'";
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).setParameter("childBirthDetailAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfChildBirthDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildBirthDetail> birthDetailFindByAttributeForCert(String childBirthDetailAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<ChildBirthDetail> listOfChildBirthDetail = null;

        String qryString;

        qryString = "SELECT e FROM ChildBirthDetail e ";
        qryString += "WHERE e." + childBirthDetailAttribute + " =:childBirthDetailAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).setParameter("childBirthDetailAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM ChildBirthDetail e ";
                qryString += "WHERE e." + childBirthDetailAttribute + " LIKE '%" + attributeValue + "%' AND e.districtApproved = 'YES' AND e.regionalApproved = 'YES'";
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).setParameter("childBirthDetailAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfChildBirthDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildBirthDetail> birthDetailGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<ChildBirthDetail> listOfChildBirthDetail = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfChildBirthDetail = super.findRange(new int[]{firstResultIndex, lastResultIndex});
//                qryString = "SELECT e FROM ChildBirthDetail e";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM ChildBirthDetail e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfChildBirthDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildBirthDetail> birthDetailGetAll(boolean includeLogicallyDeleted) {
        List<ChildBirthDetail> listOfChildBirthDetail = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfChildBirthDetail = super.findAll();
//                qryString = "SELECT e FROM ChildBirthDetail e";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM ChildBirthDetail e WHERE e.deleted = 'NO'";
                listOfChildBirthDetail = (List<ChildBirthDetail>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfChildBirthDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public String countBirhRegistrations() {
        return String.valueOf(super.count());
    }
}
