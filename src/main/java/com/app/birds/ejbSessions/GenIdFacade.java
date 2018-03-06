/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.GenId;
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
public class GenIdFacade extends AbstractFacade<GenId> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenIdFacade() {
        super(GenId.class);
    }

    public String genIdCreate(GenId genId) {
        try {

            genId.setDeleted("NO");
            genId.setUpdated("NO");
            super.create(genId);
            return genId.getGenTableId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean genIdDelete(GenId genId, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(genId);
            } else if (permanent == false) {
                genId.setDeleted("YES");
                genId.setUpdated("NO");
                super.edit(genId);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean genIdUpdate(GenId genId) {
        try {

            genId.setDeleted("NO");
            genId.setUpdated("NO");
            super.edit(genId);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public GenId genIdFind(String genTableId) {
        try {
            return super.find(genTableId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GenId> genIdFindByAttribute(String genIdAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<GenId> listOfGenId = null;

        String qryString;

        qryString = "SELECT e FROM GenId e ";
        qryString += "WHERE e." + genIdAttribute + " =:genIdAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfGenId = (List<GenId>) getEntityManager().createQuery(qryString).setParameter("genIdAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM GenId e ";
                qryString += "WHERE e." + genIdAttribute + " LIKE '%" + attributeValue + "%'";
                listOfGenId = (List<GenId>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfGenId = (List<GenId>) getEntityManager().createQuery(qryString).setParameter("genIdAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfGenId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<GenId> genIdGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<GenId> listOfGenId = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM GenId e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfGenId = (List<GenId>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfGenId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<GenId> genIdGetAll(boolean includeLogicallyDeleted) {
        List<GenId> listOfGenId = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfGenId = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM GenId e WHERE e.deleted = 'NO'";
                listOfGenId = (List<GenId>) em.createQuery(qryString).getResultList();
            }

            return listOfGenId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public GenId findDistrict(String district_id) {
        String genQry = "SELECT e FROM GenId e WHERE e.districtCode='" + district_id + "'";

        try {
            return (GenId) getEntityManager().createQuery(genQry).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
