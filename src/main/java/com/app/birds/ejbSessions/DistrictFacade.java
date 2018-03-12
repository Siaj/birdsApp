/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.District;
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
public class DistrictFacade extends AbstractFacade<District> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistrictFacade() {
        super(District.class);
    }

    public String districtCreate(District district) {
        try {

            district.setDeleted("NO");
            district.setUpdated("NO");
            super.create(district);
            return district.getDistrictId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean districtDelete(District district, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(district);
            } else if (permanent == false) {
                district.setDeleted("YES");
                district.setUpdated("NO");
                super.edit(district);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean districtUpdate(District district) {
        try {

//            district.setDeleted("NO");
            district.setUpdated("NO");
            super.edit(district);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public District districtFind(String districtId) {
        try {
            return super.find(districtId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<District> districtFindByAttribute(String districtAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<District> listOfDistrict = null;

        String qryString;

        qryString = "SELECT e FROM District e ";
        qryString += "WHERE e." + districtAttribute + " =:districtAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).setParameter("districtAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM District e ";
                qryString += "WHERE e." + districtAttribute + " LIKE '%" + attributeValue + "%'";
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).setParameter("districtAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfDistrict;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<District> districtFindByAttributeforRegion(String districtAttribute, Object attributeValue, String fieldType, String regionId, boolean includeLogicallyDeleted) {
        List<District> listOfDistrict = null;

        String qryString;

        qryString = "SELECT e FROM District e ";
        qryString += "WHERE e." + districtAttribute + " =:districtAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).setParameter("districtAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM District e ";
                qryString += "WHERE e." + districtAttribute + " LIKE '%" + attributeValue + "%' AND e.region.regionId = '" + regionId + "'";
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).setParameter("districtAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfDistrict;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<District> districtGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<District> listOfDistrict = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfDistrict = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM District e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDistrict;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<District> districtGetAll(boolean includeLogicallyDeleted) {
        List<District> listOfDistrict = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfDistrict = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM District e WHERE e.deleted = 'NO'";
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDistrict;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<District> districtFindByRegion(String regionId, boolean includeLogicallyDeleted) {
        List<District> listOfDistrict = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM District e WHERE e.region.regionId = '" + regionId + "'";
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM District e WHERE e.deleted = 'NO' AND e.region.regionId = '" + regionId + "'";
                listOfDistrict = (List<District>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfDistrict;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
