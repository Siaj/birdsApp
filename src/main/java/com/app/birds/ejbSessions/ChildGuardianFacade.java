/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.ChildGuardian;
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
public class ChildGuardianFacade extends AbstractFacade<ChildGuardian> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChildGuardianFacade() {
        super(ChildGuardian.class);
    }

    public String childGuardianCreate(ChildGuardian childGuardian) {
        try {
            childGuardian.setDeleted("NO");
            childGuardian.setUpdated("NO");
            super.create(childGuardian);
            return childGuardian.getGuardianId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean childGuardianDelete(ChildGuardian childGuardian, boolean permanent) {
        try {
            if (permanent == true) {
                super.remove(childGuardian);
            } else if (permanent == false) {
                childGuardian.setDeleted("YES");
                childGuardian.setUpdated("NO");
                super.edit(childGuardian);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean childGuardianUpdate(ChildGuardian childGuardian) {
        try {

            childGuardian.setDeleted("NO");
            childGuardian.setUpdated("NO");
            super.edit(childGuardian);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChildGuardian childGuardianFind(String guardianId) {
        try {
            return super.find(guardianId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ChildGuardian> childGuardianFindByAttribute(String childGuardianAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<ChildGuardian> listOfChildGuardian = null;

        String qryString;

        qryString = "SELECT e FROM ChildGuardian e ";
        qryString += "WHERE e." + childGuardianAttribute + " =:childGuardianAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfChildGuardian = (List<ChildGuardian>) getEntityManager().createQuery(qryString).setParameter("childGuardianAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM ChildGuardian e ";
                qryString += "WHERE e." + childGuardianAttribute + " LIKE '%" + attributeValue + "%'";
                listOfChildGuardian = (List<ChildGuardian>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfChildGuardian = (List<ChildGuardian>) getEntityManager().createQuery(qryString).setParameter("childGuardianAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfChildGuardian;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildGuardian> childGuardianGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<ChildGuardian> listOfChildGuardian = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfChildGuardian = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM ChildGuardian e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfChildGuardian = (List<ChildGuardian>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfChildGuardian;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildGuardian> childGuardianGetAll(boolean includeLogicallyDeleted) {
        List<ChildGuardian> listOfChildGuardian = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfChildGuardian = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM ChildGuardian e WHERE e.deleted = 'NO'";
                listOfChildGuardian = (List<ChildGuardian>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfChildGuardian;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
