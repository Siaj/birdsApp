///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.app.birds.ejbSessions;
//
//import com.app.birds.entities.UserAccount;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TemporalType;
//import org.w3c.dom.ls.LSInput;
//
///**
// *
// * @author Iddrisu Sibdow SIAJ
// */
//@Stateless
//public class UserAccountFacade extends AbstractFacade<UserAccount> {
//
//    @PersistenceContext(unitName = "birds-ejbPU")
//    private EntityManager em;
//
//    @Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
//
//    public UserAccountFacade() {
//        super(UserAccount.class);
//    }
//
//    public String userAccountCreate(UserAccount userAccount) {
//        try {
//
//            userAccount.setDeleted("NO");
//            userAccount.setUpdated("NO");
//            super.create(userAccount);
//            return userAccount.getUserAccountId();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//
//        }
//    }
//
//    public boolean userAccountDelete(UserAccount userAccount, boolean permanent) {
//        try {
//
//            if (permanent == true) {
//                super.remove(userAccount);
//            } else if (permanent == false) {
//                userAccount.setDeleted("YES");
//                userAccount.setUpdated("NO");
//                super.edit(userAccount);
//            }
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//
//    public boolean userAccountUpdate(UserAccount userAccount) {
//        try {
//
//            userAccount.setDeleted("NO");
//            userAccount.setUpdated("NO");
//            super.edit(userAccount);
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//
//        }
//    }
//
//    public UserAccount userAccountFind(String userAccountId) {
//        try {
//
//            return super.find(userAccountId);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public List<UserAccount> userAccountFindByAttribute(String userAccountAttribute, Object attributeValue, String fieldType, boolean includeLogicallyDeleted) {
//        List<UserAccount> listOfUserAccount = null;
//
//        String qryString;
//
//        qryString = "SELECT e FROM UserAccount e ";
//        qryString += "WHERE e." + userAccountAttribute + " =:userAccountAttribute ";
//
//        try {
//            if (includeLogicallyDeleted == true) {
//            } else if (includeLogicallyDeleted == false) {
//                qryString += " AND e.deleted = 'NO'";
//            }
//
//            if (fieldType.equalsIgnoreCase("NUMBER")) {
//                listOfUserAccount = (List<UserAccount>) getEntityManager().createQuery(qryString).setParameter("userAccountAttribute", attributeValue).getResultList();
//            } else if (fieldType.equalsIgnoreCase("STRING")) {
//                qryString = "SELECT e FROM UserAccount e ";
//                qryString += "WHERE e." + userAccountAttribute + " LIKE '%" + attributeValue + "%'";
//                listOfUserAccount = (List<UserAccount>) getEntityManager().createQuery(qryString).getResultList();
//            } else if (fieldType.equalsIgnoreCase("DATE")) {
//                listOfUserAccount = (List<UserAccount>) getEntityManager().createQuery(qryString).setParameter("userAccountAttribute", (Date) attributeValue, TemporalType.DATE).getResultList();
//            }
//
//            return listOfUserAccount;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//
//    public List<UserAccount> userAccountGetRange(int firstResultIndex, int lastResultIndex, boolean includeLogicallyDeleted) {
//        List<UserAccount> listOfUserAccount = null;
//
//        String qryString;
//
//        try {
//            if (includeLogicallyDeleted == true) {
//                listOfUserAccount = super.findRange(new int[]{firstResultIndex, lastResultIndex});
//            } else if (includeLogicallyDeleted == false) {
//                qryString = "SELECT e FROM UserAccount e WHERE e.deleted = 'NO'";
//                qryString += "LIMIT " + firstResultIndex + "," + lastResultIndex;
//                listOfUserAccount = (List<UserAccount>) getEntityManager().createQuery(qryString).getResultList();
//            }
//
//            return listOfUserAccount;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//
//    public List<UserAccount> userAccountGetAll(boolean includeLogicallyDeleted) {
//        List<UserAccount> listOfUserAccount = null;
//
//        String qryString;
//
//        try {
//            if (includeLogicallyDeleted == true) {
//                listOfUserAccount = super.findAll();
//            } else if (includeLogicallyDeleted == false) {
//                qryString = "SELECT e FROM UserAccount e WHERE e.deleted = 'NO'";
//                listOfUserAccount = (List<UserAccount>) em.createQuery(qryString).getResultList();
//            }
//
//            return listOfUserAccount;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//
//    public UserAccount findSystemUser(String username, String password) {
//
//        String qrt = "SELECT u FROM UserAccount u WHERE u.username='" + username + "'"
//                + "AND u.password='" + password + "' AND u.deleted='NO' AND u.accountStatus='Active'";
//
//        try {
//            return (UserAccount) getEntityManager().createQuery(qrt).getSingleResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//}
