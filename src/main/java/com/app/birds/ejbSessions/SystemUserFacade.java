/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.SystemUser;
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
public class SystemUserFacade extends AbstractFacade<SystemUser> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SystemUserFacade() {
        super(SystemUser.class);
    }

    public String systemUserCreate(SystemUser systemUser) {
        try {

            systemUser.setDeleted("NO");
            systemUser.setUpdated("NO");
            super.create(systemUser);
            return systemUser.getSystemUserId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean systemUserDelete(SystemUser systemUser, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(systemUser);
            } else if (permanent == false) {
                systemUser.setDeleted("YES");
                systemUser.setUpdated("NO");
                super.edit(systemUser);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean systemUserUpdate(SystemUser systemUser) {
        try {

            systemUser.setDeleted("NO");
            systemUser.setUpdated("NO");
            super.edit(systemUser);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public SystemUser systemUserFind(String systemUserId) {
        try {
            return super.find(systemUserId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<SystemUser> systemUserFindByAttribute(String systemUserAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        qryString = "SELECT e FROM SystemUser e ";
        qryString += "WHERE e." + systemUserAttribute + " =:systemUserAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SystemUser e ";
                qryString += "WHERE e." + systemUserAttribute + " LIKE '%" + attributeValue + "%'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserFindByAttributeByDistrict(String systemUserAttribute, Object attributeValue, String fieldType, String districtId, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        qryString = "SELECT e FROM SystemUser e ";
        qryString += "WHERE e." + systemUserAttribute + " =:systemUserAttribute AND e.district.districtId = '" + districtId + "'";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SystemUser e ";
                qryString += "WHERE e." + systemUserAttribute + " LIKE '%" + attributeValue + "%' AND e.district.districtId = '" + districtId + "'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfSystemUser = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserGetAll(boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfSystemUser = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.deleted = 'NO'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<SystemUser> systemUserGetByDistrict(boolean includeLogicallyDeleted, String districtId) {
        List<SystemUser> listOfUsers;

        String qryString = "";

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM SystemUser e WHERE e.district.districtId = '" + districtId + "'";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.district.districtId = '" + districtId + "' AND e.deleted = 'NO'";
            }

            listOfUsers = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            return listOfUsers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public List<SystemUser> systemUserGetByRegion(boolean includeLogicallyDeleted, String regionId) {
        List<SystemUser> listOfUsers;

        String qryString = "";

        try {
            if (includeLogicallyDeleted == true) {
                qryString = "SELECT e FROM SystemUser e WHERE e.district.region.regionId = '" + regionId + "'";
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM SystemUser e WHERE e.district.region.regionId = '" + regionId + "' AND e.deleted = 'NO'";
            }

            listOfUsers = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            return listOfUsers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public List<SystemUser> systemUserFindByAttributeByRegion(String systemUserAttribute, Object attributeValue, String fieldType, String regionId, boolean includeLogicallyDeleted) {
        List<SystemUser> listOfSystemUser = null;

        String qryString;

        qryString = "SELECT e FROM SystemUser e ";
        qryString += "WHERE e." + systemUserAttribute + " =:systemUserAttribute AND e.district.region.regionId = '" + regionId + "'";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM SystemUser e ";
                qryString += "WHERE e." + systemUserAttribute + " LIKE '%" + attributeValue + "%' AND e.district.region.regionId = '" + regionId + "'";
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfSystemUser = (List<SystemUser>) getEntityManager().createQuery(qryString).setParameter("systemUserAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfSystemUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
