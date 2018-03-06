/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.District;
import com.app.birds.entities.Region;
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
public class RegionFacade extends AbstractFacade<Region> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegionFacade() {
        super(Region.class);
    }

    public String regionCreate(Region region) {
        try {

            region.setDeleted("NO");
            region.setUpdated("NO");
            super.create(region);
            return region.getRegionId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean regionDelete(Region region, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(region);
            } else if (permanent == false) {
                region.setDeleted("YES");
                region.setUpdated("NO");
                super.edit(region);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean districtUpdate(Region region) {
        try {

            region.setDeleted("NO");
            region.setUpdated("NO");
            super.edit(region);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public Region districtFind(String regionId) {
        try {
            return super.find(regionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Region> districtFindByAttribute(String regionAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<Region> listOfRegion = null;

        String qryString;

        qryString = "SELECT e FROM Region e ";
        qryString += "WHERE e." + regionAttribute + " =:regionAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfRegion = (List<Region>) getEntityManager().createQuery(qryString).setParameter("regionAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM Region e ";
                qryString += "WHERE e." + regionAttribute + " LIKE '%" + attributeValue + "%'";
                listOfRegion = (List<Region>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfRegion = (List<Region>) getEntityManager().createQuery(qryString).setParameter("regionAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfRegion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Region> regionGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<Region> listOfRegion = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfRegion = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM Region e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfRegion = (List<Region>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfRegion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Region> districtGetAll(boolean includeLogicallyDeleted) {
        List<Region> listOfRegion = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfRegion = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM Region e WHERE e.deleted = 'NO'";
                listOfRegion = (List<Region>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfRegion;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
