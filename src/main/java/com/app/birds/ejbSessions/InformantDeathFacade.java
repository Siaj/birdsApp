/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.InformantDeath;
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
public class InformantDeathFacade extends AbstractFacade<InformantDeath> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformantDeathFacade() {
        super(InformantDeath.class);
    }

    public String informantDeathCreate(InformantDeath informantDeath) {
        try {

            informantDeath.setDeleted("NO");
            informantDeath.setUpdated("NO");
            super.create(informantDeath);
            return informantDeath.getInformantDeathId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean informantDeathDelete(InformantDeath informantDeath, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(informantDeath);
            } else if (permanent == false) {
                informantDeath.setDeleted("YES");
                informantDeath.setUpdated("NO");
                super.edit(informantDeath);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean informantDeathUpdate(InformantDeath informantDeath) {
        try {

            informantDeath.setDeleted("NO");
            informantDeath.setUpdated("NO");
            super.edit(informantDeath);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public InformantDeath informantDeathFind(String informantDeathId) {
        try {
            return super.find(informantDeathId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<InformantDeath> informantDeathFindByAttribute(String informantDeathAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<InformantDeath> listOfInformantDeath = null;

        String qryString;

        qryString = "SELECT e FROM InformantDeath e ";
        qryString += "WHERE e." + informantDeathAttribute + " =:informantDeathAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfInformantDeath = (List<InformantDeath>) getEntityManager().createQuery(qryString).setParameter("informantDeathAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM InformantDeath e ";
                qryString += "WHERE e." + informantDeathAttribute + " LIKE '%" + attributeValue + "%'";
                listOfInformantDeath = (List<InformantDeath>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfInformantDeath = (List<InformantDeath>) getEntityManager().createQuery(qryString).setParameter("informantDeathAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfInformantDeath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<InformantDeath> informantDeathGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<InformantDeath> listOfInformantDeath = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfInformantDeath = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM InformantDeath e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfInformantDeath = (List<InformantDeath>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfInformantDeath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<InformantDeath> informantDeathGetAll(boolean includeLogicallyDeleted) {
        List<InformantDeath> listOfInformantDeath = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfInformantDeath = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM InformantDeath e WHERE e.deleted = 'NO'";
                listOfInformantDeath = (List<InformantDeath>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfInformantDeath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
