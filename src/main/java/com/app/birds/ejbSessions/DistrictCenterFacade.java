/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.DistrictCenter;
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
public class DistrictCenterFacade extends AbstractFacade<DistrictCenter> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistrictCenterFacade() {
        super(DistrictCenter.class);
    }

    public String districtCenterCreate(DistrictCenter districtCenter) {
        try {

            districtCenter.setDeleted("NO");
            districtCenter.setUpdated("NO");
            em.persist(districtCenter);
            em.flush();
            return districtCenter.getCenterId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean districtCenterDelete(DistrictCenter districtCenter, boolean permanent) {
        try {

            if (permanent == true) {
                em.remove(em.merge(districtCenter));
            } else if (permanent == false) {
                districtCenter.setDeleted("YES");
                districtCenter.setUpdated("NO");
                em.merge(districtCenter);
            }
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean districtCenterUpdate(DistrictCenter districtCenter) {
        try {

            districtCenter.setDeleted("NO");
            districtCenter.setUpdated("NO");
            em.merge(districtCenter);
            em.flush();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public DistrictCenter districtCenterFind(String centerId) {
        try {

            DistrictCenter districtCenter = em.find(DistrictCenter.class, centerId);
            return districtCenter;
        } catch (Exception e) {
            return null;
        }
    }

    public List<DistrictCenter> districtCenterFindByAttribute(String districtCenterAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<DistrictCenter> listOfDistrictCenter = null;

        String qryString = "";

        qryString = "SELECT e FROM DistrictCenter e ";
        qryString += "WHERE e." + districtCenterAttribute + " =:districtCenterAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfDistrictCenter = (List<DistrictCenter>) em.createQuery(qryString).setParameter("districtCenterAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM DistrictCenter e ";
                qryString += "WHERE e." + districtCenterAttribute + " LIKE '%" + attributeValue + "%'";
                listOfDistrictCenter = (List<DistrictCenter>) em.createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfDistrictCenter = (List<DistrictCenter>) em.createQuery(qryString).setParameter("districtCenterAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfDistrictCenter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DistrictCenter>();
    }

    public List<DistrictCenter> districtCenterGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<DistrictCenter> listOfDistrictCenter = null;

        String qryString = "";

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM DistrictCenter e";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DistrictCenter e WHERE e.deleted = 'NO'";
            }

            qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
            listOfDistrictCenter = (List<DistrictCenter>) em.createQuery(qryString).getResultList();

            return listOfDistrictCenter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DistrictCenter>();
    }

    public List<DistrictCenter> districtCenterGetAll(boolean includeLogicallyDeleted) {
        List<DistrictCenter> listOfDistrictCenter = null;

        String qryString = "";

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM DistrictCenter e";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DistrictCenter e WHERE e.deleted = 'NO'";
            }

            listOfDistrictCenter = (List<DistrictCenter>) em.createQuery(qryString).getResultList();

            return listOfDistrictCenter;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DistrictCenter>();
    }

    public List<DistrictCenter> distictCenterFindById(boolean includeLogicallyDeleted, String districtId) {
        List<DistrictCenter> listOfCenterById = null;

        String qryString = "";
        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FORM DistrictCenter e WHERE e.districtUnder.districtId = '" + districtId + "'";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM DistrictCenter e WHERE e.districtUnder.districtId = '" + districtId + "' AND e.deleted = 'NO'";
            }

            listOfCenterById = (List<DistrictCenter>) em.createQuery(qryString).getResultList();

            return listOfCenterById;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<DistrictCenter>();
    }
}
