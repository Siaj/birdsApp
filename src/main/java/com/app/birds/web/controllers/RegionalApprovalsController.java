/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.ChildBirthDetailFacade;
import com.app.birds.ejbSessions.DeceasedDetailFacade;
import com.app.birds.ejbSessions.SupportBean;
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
import javax.faces.model.ListDataModel;
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
    @Inject
    private SupportBean supportBean;

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
            cancelRequest();
            loadBirths();
            JSFUtility.infoMessage("Success:", "Child Birth Details Has Been Successfully Saved");
        } else {
            JSFUtility.warnMessage("Error:", "An Error Was Encounted During Saving The Child Details.Please Try Again");
        }
    }

    public void approveDeathDetails() {
        deceasedDetail.setRegionalApproved("YES");
        boolean saved = deceasedDetailFacade.deceasedDetailUpdate(deceasedDetail);
        if (saved) {
            cancelRequest();
            loadDeceased();
            JSFUtility.infoMessage("Success:", "Deceased Details Has Been Successfully Approved.");
        } else {
            JSFUtility.warnMessage("Error:", "An Error Was Encounted During Approval.Please Check And Try Again");
        }
    }

    public void fetchBirthDetails() {
        birthDetail = (ChildBirthDetail) birthDetailModel.getRowData();
        guardian = birthDetail.getGuardian();
        if (birthDetail != null || guardian != null) {
            renderApproval = true;
        }
    }

    public void fetchBirthCertRequestDetails() {
        birthCertRequest = (BirthCertRequest) birthCertModel.getRowData();

        birthDetail = birthCertRequest.getBirthApplicantId();
        guardian = birthDetail.getGuardian();
    }

    public void fetchDeathDetails() {
        deceasedDetail = (DeceasedDetail) deceasedDetailModel.getRowData();
        informantDeath = deceasedDetail.getInformantDeath();

        if (deceasedDetail != null || informantDeath != null) {
            renderApproval = true;
        }
    }

    public void fetchDeathCertRequestDetails() {
        deathCertRequest = (DeathCertRequest) deathCertModel.getRowData();

        deceasedDetail = deathCertRequest.getDeceasedDetails();
        informantDeath = deceasedDetail.getInformantDeath();
    }

    public void cancelRequest() {
        renderApproval = false;
    }

//    public int noOfBirths() {
//        List<ChildBirthDetail> listOfChild;
//        DataModel<ChildBirthDetail> childModel;
//
//        listOfChild = new ArrayList<ChildBirthDetail>(birdsSessionBean.childBirthDetailNumber());
//        childModel = new ListDataModel<>(listOfChild);
//        distBirthDetails = childModel.getRowCount();
//        return distBirthDetails;
//
//    }
//    public int noOfDeaths() {
//        List<DeceasedDetail> listOfDeath = null;
//        DataModel<DeceasedDetail> deathModel = null;
//
//        listOfDeath = new ArrayList<DeceasedDetail>(birdsSessionBean.deceasedDetailNumber());
//        deathModel = new ListDataModel<DeceasedDetail>(listOfDeath);
//        distDeathDetails = deathModel.getRowCount();
//        return distDeathDetails;
//    }
    public void loadBirths() {
        birthDetailsList = supportBean.listOfChildrenDetailsForRegApproval(selectedDistrict);
        birthDetailModel = new ListDataModel<>(birthDetailsList);
    }

    public void loadDeceased() {
        deceasedDetailsList = supportBean.listOfDeathDetailsForRegApproval(selectedDistrict);
        deceasedDetailModel = new ListDataModel<>(deceasedDetailsList);
    }

    public void loadBirthCerts() {
        birthCertRequestsList = supportBean.listOfBirthCertForRegApprovalAndPrint(selectedDistrict);
        birthCertModel = new ListDataModel<>(birthCertRequestsList);
    }

    public void loadDeathCerts() {
        deathCertRequestsList = supportBean.listOfDeathCertForRegApprovalAndPrint(selectedDistrict);
        deathCertModel = new ListDataModel<>(deathCertRequestsList);
    }

    public void printBirthCert() {

    }

    public void printDeathCert() {

    }

    public void resetDistrictSelected() {
        selectedDistrict = "";
        loadBirths();
        loadDeceased();
        loadBirthCerts();
        loadDeathCerts();
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public ChildBirthDetailFacade getBirthDetailFacade() {
        return birthDetailFacade;
    }

    public void setBirthDetailFacade(ChildBirthDetailFacade birthDetailFacade) {
        this.birthDetailFacade = birthDetailFacade;
    }

    public DeceasedDetailFacade getDeceasedDetailFacade() {
        return deceasedDetailFacade;
    }

    public void setDeceasedDetailFacade(DeceasedDetailFacade deceasedDetailFacade) {
        this.deceasedDetailFacade = deceasedDetailFacade;
    }

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public ChildBirthDetail getBirthDetail() {
        return birthDetail;
    }

    public void setBirthDetail(ChildBirthDetail birthDetail) {
        this.birthDetail = birthDetail;
    }

    public DeceasedDetail getDeceasedDetail() {
        return deceasedDetail;
    }

    public void setDeceasedDetail(DeceasedDetail deceasedDetail) {
        this.deceasedDetail = deceasedDetail;
    }

    public BirthCertRequest getBirthCertRequest() {
        return birthCertRequest;
    }

    public void setBirthCertRequest(BirthCertRequest birthCertRequest) {
        this.birthCertRequest = birthCertRequest;
    }

    public DeathCertRequest getDeathCertRequest() {
        return deathCertRequest;
    }

    public void setDeathCertRequest(DeathCertRequest deathCertRequest) {
        this.deathCertRequest = deathCertRequest;
    }

    public ChildGuardian getGuardian() {
        return guardian;
    }

    public void setGuardian(ChildGuardian guardian) {
        this.guardian = guardian;
    }

    public InformantDeath getInformantDeath() {
        return informantDeath;
    }

    public void setInformantDeath(InformantDeath informantDeath) {
        this.informantDeath = informantDeath;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public List<ChildBirthDetail> getBirthDetailsList() {
        return birthDetailsList;
    }

    public void setBirthDetailsList(List<ChildBirthDetail> birthDetailsList) {
        this.birthDetailsList = birthDetailsList;
    }

    public List<DeceasedDetail> getDeceasedDetailsList() {
        return deceasedDetailsList;
    }

    public void setDeceasedDetailsList(List<DeceasedDetail> deceasedDetailsList) {
        this.deceasedDetailsList = deceasedDetailsList;
    }

    public List<BirthCertRequest> getBirthCertRequestsList() {
        return birthCertRequestsList;
    }

    public void setBirthCertRequestsList(List<BirthCertRequest> birthCertRequestsList) {
        this.birthCertRequestsList = birthCertRequestsList;
    }

    public List<DeathCertRequest> getDeathCertRequestsList() {
        return deathCertRequestsList;
    }

    public void setDeathCertRequestsList(List<DeathCertRequest> deathCertRequestsList) {
        this.deathCertRequestsList = deathCertRequestsList;
    }

    public DataModel<ChildBirthDetail> getBirthDetailModel() {
        return birthDetailModel;
    }

    public void setBirthDetailModel(DataModel<ChildBirthDetail> birthDetailModel) {
        this.birthDetailModel = birthDetailModel;
    }

    public DataModel<DeceasedDetail> getDeceasedDetailModel() {
        return deceasedDetailModel;
    }

    public void setDeceasedDetailModel(DataModel<DeceasedDetail> deceasedDetailModel) {
        this.deceasedDetailModel = deceasedDetailModel;
    }

    public DataModel<BirthCertRequest> getBirthCertModel() {
        return birthCertModel;
    }

    public void setBirthCertModel(DataModel<BirthCertRequest> birthCertModel) {
        this.birthCertModel = birthCertModel;
    }

    public DataModel<DeathCertRequest> getDeathCertModel() {
        return deathCertModel;
    }

    public void setDeathCertModel(DataModel<DeathCertRequest> deathCertModel) {
        this.deathCertModel = deathCertModel;
    }

    public boolean isRenderApproval() {
        return renderApproval;
    }

    public void setRenderApproval(boolean renderApproval) {
        this.renderApproval = renderApproval;
    }

    public int getDistBirthDetails() {
        return distBirthDetails;
    }

    public void setDistBirthDetails(int distBirthDetails) {
        this.distBirthDetails = distBirthDetails;
    }

    public int getDistDeathDetails() {
        return distDeathDetails;
    }

    public void setDistDeathDetails(int distDeathDetails) {
        this.distDeathDetails = distDeathDetails;
    }

    public int getDistBirthCert() {
        return distBirthCert;
    }

    public void setDistBirthCert(int distBirthCert) {
        this.distBirthCert = distBirthCert;
    }

    public int getDistDeathCert() {
        return distDeathCert;
    }

    public void setDistDeathCert(int distDeathCert) {
        this.distDeathCert = distDeathCert;
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

//</editor-fold>
}
