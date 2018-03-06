/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.District;
import com.app.birds.entities.UserRole;
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
public class UserRoleFacade extends AbstractFacade<UserRole> {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRoleFacade() {
        super(UserRole.class);
    }

    public String roleCreate(UserRole userRole) {
        try {

            userRole.setDeleted("NO");
            userRole.setUpdated("NO");
            super.create(userRole);
            return userRole.getRoleId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public boolean roleDelete(UserRole userRole, boolean permanent) {
        try {

            if (permanent == true) {
                super.remove(userRole);
            } else if (permanent == false) {
                userRole.setDeleted("YES");
                userRole.setUpdated("NO");
                super.edit(userRole);
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public boolean roleUpdate(UserRole userRole) {
        try {

            userRole.setDeleted("NO");
            userRole.setUpdated("NO");
            super.edit(userRole);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public UserRole roleFind(String roleID) {
        try {
            return super.find(roleID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserRole> roleFindByAttribute(String roleAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
        List<UserRole> listOfRoles = null;

        String qryString;

        qryString = "SELECT e FROM UserRole e ";
        qryString += "WHERE e." + roleAttribute + " =:roleAttribute ";

        try {
            if (includeLogicallyDeleted == true) {
            } else if (includeLogicallyDeleted == false) {
                qryString += " AND e.deleted = 'NO'";
            }

            if (fieldType.equalsIgnoreCase("NUMBER")) {
                listOfRoles = (List<UserRole>) getEntityManager().createQuery(qryString).setParameter("roleAttribute", attributeValue).getResultList();
            } else if (fieldType.equalsIgnoreCase("STRING")) {
                qryString = "SELECT e FROM District e ";
                qryString += "WHERE e." + roleAttribute + " LIKE '%" + attributeValue + "%'";
                listOfRoles = (List<UserRole>) getEntityManager().createQuery(qryString).getResultList();
            } else if (fieldType.equalsIgnoreCase("DATE")) {
                listOfRoles = (List<UserRole>) getEntityManager().createQuery(qryString).setParameter("roleAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
            }

            return listOfRoles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<UserRole> roleGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
        List<UserRole> listOfRoles = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfRoles = super.findRange(new int[]{firstResultIndex, lastResultIndex});
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM UserRole e WHERE e.deleted = 'NO'";
                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
                listOfRoles = (List<UserRole>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfRoles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<UserRole> roleGetAll(boolean includeLogicallyDeleted) {
        List<UserRole> listOfRole = null;

        String qryString;

        try {
            if (includeLogicallyDeleted == true) {
                listOfRole = super.findAll();
            } else if (includeLogicallyDeleted == false) {
                qryString = "SELECT e FROM UserRole e WHERE e.deleted = 'NO'";
                listOfRole = (List<UserRole>) getEntityManager().createQuery(qryString).getResultList();
            }

            return listOfRole;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
