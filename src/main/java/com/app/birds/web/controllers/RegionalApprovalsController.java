/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.BirthCertRequestFacade;
import com.app.birds.ejbSessions.ChildBirthDetailFacade;
import com.app.birds.ejbSessions.DeathCertRequestFacade;
import com.app.birds.ejbSessions.DeceasedDetailFacade;
import com.app.birds.ejbSessions.SupportBean;
import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.controllers.qualifiers.RegAdmin;
import com.app.birds.web.controllers.qualifiers.Update;
import com.app.birds.web.detailedClass.BirthDetailedClass;
import com.app.birds.web.reports.ReportController;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.event.Event;
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
    @Update
    @RegAdmin
    Event<ChildBirthDetail> birthDetailsApprove;
    
    @Inject 
    @Update
    @RegAdmin
    Event<DeceasedDetail> deceasedDetailsApprove;

    @Inject
    private ChildBirthDetailFacade birthDetailFacade;
    @Inject
    private DeceasedDetailFacade deceasedDetailFacade;
    @Inject
    private SupportBean supportBean;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private BirthCertRequestFacade birthCertRequestFacade;
    @Inject
    private DeathCertRequestFacade deathCertRequestFacade;

    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private BirthCertRequest birthCertRequest = new BirthCertRequest();
    private DeathCertRequest deathCertRequest = new DeathCertRequest();
    private ChildGuardian guardian = new ChildGuardian();
    private InformantDeath informantDeath = new InformantDeath();
    private SystemUser systemUser = new SystemUser();
    private UserAccessController accessController = new UserAccessController();
    private List<ChildBirthDetail> birthDetailsList = new ArrayList<>();
    private List<DeceasedDetail> deceasedDetailsList = new ArrayList<>();
    private List<BirthCertRequest> birthCertRequestsList = new ArrayList<>();
    private List<DeathCertRequest> deathCertRequestsList = new ArrayList<>();
    private DataModel<ChildBirthDetail> birthDetailModel;
    private DataModel<DeceasedDetail> deceasedDetailModel;
    private DataModel<BirthCertRequest> birthCertModel;
    private DataModel<DeathCertRequest> deathCertModel;
    private boolean renderApproval = false;
    private String regId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();
    private int countRegBirthDetails = 0, countRegDeathDetails = 0, countRegBirthCert = 0, countRegDeathCert = 0;
    private String selectedDistrict;

    public RegionalApprovalsController() {
    }

//    public SystemUser getSystemUser() {
//        try {
//            systemUser = (SystemUser) JSFUtility.getSessionValue(BirdsConstant.LOGIN_USER);
//        } catch (Exception e) {
//            systemUser = null;
//        }
//        return systemUser;
//    }
    public void approveBirthDetails() {
        birthDetail.setRegionalApproved("YES");
        boolean saved = birthDetailFacade.updateBirthDetails(birthDetail);
        if (saved) {
            birthDetailsApprove.fire(birthDetail);
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
            deceasedDetailsApprove.fire(deceasedDetail);
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

    public int noOfRegBirths() {
        List<ChildBirthDetail> listOfChild;
        DataModel<ChildBirthDetail> childModel;

        listOfChild = new ArrayList<>(supportBean.regionalBirthDetailNumber(regId));
        childModel = new ListDataModel<>(listOfChild);
        countRegBirthDetails = childModel.getRowCount();
        return countRegBirthDetails;

    }

    public int noOfRegDeaths() {
        List<DeceasedDetail> listOfDeath;
        DataModel<DeceasedDetail> deathModel;

        listOfDeath = new ArrayList<>(supportBean.regionalDeceasedDetailNumber(regId));
        deathModel = new ListDataModel<>(listOfDeath);
        countRegDeathDetails = deathModel.getRowCount();
        return countRegDeathDetails;
    }

    public int noOfRegBirthCertRequests() {
        List<BirthCertRequest> listOfBirthCertReq;
        DataModel<BirthCertRequest> birthCertRequestsModel;

        listOfBirthCertReq = new ArrayList<>(supportBean.regionalBirthCertNumber(regId));
        birthCertRequestsModel = new ListDataModel<>(listOfBirthCertReq);
        countRegBirthCert = birthCertRequestsModel.getRowCount();

        return countRegBirthCert;
    }

    public int noOfRegDeathCertRequests() {
        List<DeathCertRequest> listOfDeathCertsReq;
        DataModel<DeathCertRequest> deathCertRequestModel;

        listOfDeathCertsReq = new ArrayList<>(supportBean.regionalDeathCertNumber(regId));
        deathCertRequestModel = new ListDataModel<>(listOfDeathCertsReq);
        countRegDeathCert = deathCertRequestModel.getRowCount();

        return countRegDeathCert;
    }

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

    public void testPrint() {
        List<BirthDetailedClass> bdcsList = null;

        ReportController.getInstance().loadDefaultParameters();
        ReportController.getInstance().showReport(bdcsList, getClass().getResourceAsStream(ReportController.BIRTH_CERTIFICATE));
    }

    public void printBirthCert() {
        List<BirthDetailedClass> bdcsList = null;

        birthCertRequest = (BirthCertRequest) birthCertModel.getRowData();
        String user_id = birthCertRequest.getBirthApplicantId().getSystemUser().getSystemUserId();
        System.out.println(user_id);
        systemUser = systemUserFacade.systemUserFind(user_id);
        System.out.println(systemUser.getFirstName());
        BirthDetailedClass birthDetailedClass = new BirthDetailedClass();

        bdcsList = birthDetailedClass.loadBirth(birthCertRequest, systemUser);

        System.out.println(bdcsList);

//        birthCertRequest.setRegionalApproved("YES");
//        birthCertRequest.setCertPrinted("YES");
//        birthCertRequestFacade.birthCertRequestUpdate(birthCertRequest);
        ReportController.getInstance().loadDefaultParameters();
//        ReportController.getInstance().showReport(bdcsList, getClass().getResourceAsStream(ReportController.BIRTH_CERTIFICATE));
        ReportController.getInstance().showReport(bdcsList, ReportController.BIRTH_CERTIFICATE);
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

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public int getCountRegBirthDetails() {
        return countRegBirthDetails;
    }

    public void setCountRegBirthDetails(int countRegBirthDetails) {
        this.countRegBirthDetails = countRegBirthDetails;
    }

    public int getCountRegDeathDetails() {
        return countRegDeathDetails;
    }

    public void setCountRegDeathDetails(int countRegDeathDetails) {
        this.countRegDeathDetails = countRegDeathDetails;
    }

    public int getCountRegBirthCert() {
        return countRegBirthCert;
    }

    public void setCountRegBirthCert(int countRegBirthCert) {
        this.countRegBirthCert = countRegBirthCert;
    }

    public int getCountRegDeathCert() {
        return countRegDeathCert;
    }

    public void setCountRegDeathCert(int countRegDeathCert) {
        this.countRegDeathCert = countRegDeathCert;
    }

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

//</editor-fold>
}
