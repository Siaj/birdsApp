/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class SupportBean {

    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;

    public SystemUser authenticateUser(String username, String password) {
        try {
            return (SystemUser) em.createNamedQuery(SystemUser.FIND_BY_USERNAME_PASSWORD).setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (Exception e) {
            System.out.println("Did not get any Results for entry");
//            e.printStackTrace(); used during developmental testing
            return null;
        }
    }

    public List<ChildBirthDetail> listOfChildrenDetailsForDistApproval(String distId) {
        String qry;
        List<ChildBirthDetail> listOfChild;

        try {
            qry = "SELECT e FROM ChildBirthDetail e WHERE e.districtApproved = 'NO' AND e.systemUser.district.districtId = '" + distId + "'";
            listOfChild = (List<ChildBirthDetail>) em.createQuery(qry).getResultList();
            return listOfChild;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildBirthDetail> listOfChildrenDetailsForRegApproval(String selectedDist) {
        String qry;
        List<ChildBirthDetail> listOfChild;

        try {
            qry = "SELECT e FROM ChildBirthDetail e WHERE e.districtApproved = 'YES' AND e.regionalApproved = 'NO' AND e.systemUser.district.districtId = '" + selectedDist + "'";
            listOfChild = (List<ChildBirthDetail>) em.createQuery(qry).getResultList();
            return listOfChild;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeceasedDetail> listOfDeathDetailsForDistApproval(String distId) {
        String qry;
        List<DeceasedDetail> listOfDeaths;

        try {
            qry = "SELECT e FROM DeceasedDetail e WHERE e.districtApproved = 'NO' AND e.systemUser.district.districtId = '" + distId + "'";
            listOfDeaths = (List<DeceasedDetail>) em.createQuery(qry).getResultList();
            return listOfDeaths;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeceasedDetail> listOfDeathDetailsForRegApproval(String selectedDist) {
        String qry;
        List<DeceasedDetail> listOfDeaths;

        try {
            qry = "SELECT e FROM DeceasedDetail e WHERE e.districtApproved = 'YES' AND e.regionalApproved = 'NO' AND e.systemUser.district.districtId = '" + selectedDist + "'";
            listOfDeaths = (List<DeceasedDetail>) em.createQuery(qry).getResultList();
            return listOfDeaths;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BirthCertRequest> listOfBirthCertForDistApproval(String distId) {
        String qry;
        List<BirthCertRequest> listOfBirthCert;
        try {
            qry = "SELECT e FROM BirthCertRequest e WHERE e.districtApproved = 'NO' AND e.district = '" + distId + "'";
            listOfBirthCert = (List<BirthCertRequest>) em.createQuery(qry).getResultList();
            return listOfBirthCert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<BirthCertRequest> listOfBirthCertForRegApprovalAndPrint(String selectedDistrict) {
        String qry;
        List<BirthCertRequest> listOfBirthCert;
        try {
            qry = "SELECT e FROM BirthCertRequest e WHERE e.districtApproved = 'YES' AND e.regionalApproved = 'NO' AND e.district = '" + selectedDistrict + "'";
            listOfBirthCert = (List<BirthCertRequest>) em.createQuery(qry).getResultList();
            return listOfBirthCert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeathCertRequest> listOfDeathCertForDistApproval(String distId) {
        String qry;
        List<DeathCertRequest> listOfDeathCert;

        try {
            qry = "SELECT e FROM DeathCertRequest e WHERE e.districtApproved = 'NO' AND e.district = '" + distId + "'";
            listOfDeathCert = (List<DeathCertRequest>) em.createQuery(qry).getResultList();
            return listOfDeathCert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeathCertRequest> listOfDeathCertForRegApprovalAndPrint(String selectedDist) {
        String qry;
        List<DeathCertRequest> listOfDeathCert;

        try {
            qry = "SELECT e FROM DeathCertRequest e WHERE e.districtApproved = 'YES' AND e.regionalApproved = 'NO' AND e.district = '" + selectedDist + "'";
            listOfDeathCert = (List<DeathCertRequest>) em.createQuery(qry).getResultList();
            return listOfDeathCert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ChildBirthDetail> regionalBirthDetailNumber(String regionId) {
        List<ChildBirthDetail> listOfChildBirthDetail;

        String qryString;

        try {
            qryString = "SELECT e FROM ChildBirthDetail e WHERE e.districtApproved = 'YES' AND "
                    + "e.regionalApproved = 'NO' AND e.systemUser.district.region.regionId = '" + regionId + "'";
            listOfChildBirthDetail = (List<ChildBirthDetail>) em.createQuery(qryString).getResultList();

            return listOfChildBirthDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<DeceasedDetail> regionalDeceasedDetailNumber(String regionId) {
        List<DeceasedDetail> listOfDeathDetail;

        String qryString;

        try {
            qryString = "SELECT e FROM DeceasedDetail e WHERE e.districtApproved = 'YES' AND "
                    + "e.regionalApproved = 'NO' AND e.systemUser.district.region.regionId = '" + regionId + "'";
            listOfDeathDetail = (List<DeceasedDetail>) em.createQuery(qryString).getResultList();

            return listOfDeathDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
//    public List<ChildBirthDetail> regionalBirthCertNumber(String regionId) {
//        List<ChildBirthDetail> listOfChildBirthDetail;
//
//        String qryString;
//
//        try {
//            qryString = "SELECT e FROM BirthCertRequest e WHERE e.districtApproved = 'YES'"
//                    + " AND e.regionalApproved = 'NO' AND e.SystemUserId.district.region.regionId '" + regionId + "'";
//            listOfChildBirthDetail = (List<ChildBirthDetail>) em.createQuery(qryString).getResultList();
//
//            return listOfChildBirthDetail;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//    
//    public List<ChildBirthDetail> regionalDeathCertNumber(String regionId) {
//        List<ChildBirthDetail> listOfChildBirthDetail;
//
//        String qryString;
//
//        try {
//            qryString = "SELECT e FROM ChildBirthDetail e WHERE e.districtApproved = 'YES' AND "
//                    + "e.regionalApproved = 'NO' AND e.systemUser.district.region.regionId = '" + regionId + "'";
//            listOfChildBirthDetail = (List<ChildBirthDetail>) em.createQuery(qryString).getResultList();
//
//            return listOfChildBirthDetail;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
}
