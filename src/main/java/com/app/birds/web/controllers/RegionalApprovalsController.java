/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.ChildBirthDetailFacade;
import com.app.birds.ejbSessions.DeceasedDetailFacade;
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.entities.UserAccount;
import com.app.birds.web.commons.BirdsConstant;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "regApprovalsController")
@SessionScoped
public class RegionalApprovalsController implements Serializable {

    @Inject
    private ChildBirthDetailFacade birthDetailFacade;
    @Inject
    private DeceasedDetailFacade deceasedDetailFacade;

    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private BirthCertRequest birthCertRequest = new BirthCertRequest();
    private DeathCertRequest deathCertRequest = new DeathCertRequest();
    private ChildGuardian guardian = new ChildGuardian();
    private InformantDeath informantDeath = new InformantDeath();
    private UserAccount userAccount = new UserAccount();
    private SystemUser systemUser = new SystemUser();
    private List<ChildBirthDetail> birthDetailsList = new ArrayList<>();
    private List<DeceasedDetail> deceasedDetailsList = new ArrayList<>();
    private List<BirthCertRequest> birthCertRequestsList = new ArrayList<>();
    private List<DeathCertRequest> deathCertRequestsList = new ArrayList<>();
    private DataModel<ChildBirthDetail> birthDetailModel;
    private DataModel<DeceasedDetail> deceasedDetailModel;
    private DataModel<BirthCertRequest> birthCertModel;
    private DataModel<DeathCertRequest> deathCertModel;
    private boolean renderApproval = false;
    private int distBirthDetails = 0, distDeathDetails = 0, distBirthCert = 0, distDeathCert = 0;
    private String selectedDistrict;

    public RegionalApprovalsController() {
    }

    public UserAccount getUserAccount() {
        try {
            userAccount = (UserAccount) JSFUtility.getSessionValue(BirdsConstant.LOGIN_USER);
        } catch (Exception e) {
            userAccount = null;
        }
        return userAccount;
    }
    
    public void approveBirthDetails() {
        birthDetail.setRegionalApproved("YES");
        boolean saved = birthDetailFacade.updateBirthDetails(birthDetail);
        if (saved) {
//            cancelRequest();
//            loadBirths();
            JSFUtility.infoMessage("Success:", "Child Birth Details Has Been Successfully Saved");
        } else {
            JSFUtility.warnMessage("Error:", "An Error Was Encounted During Saving The Child Details.Please Try Again");
        }
    }
}
