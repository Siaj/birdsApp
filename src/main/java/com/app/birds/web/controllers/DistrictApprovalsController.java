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
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.BirdsConstant;
import com.app.birds.web.controllers.qualifiers.DistAdmin;
import com.app.birds.web.controllers.qualifiers.Update;
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
@Named(value = "distApprovalsController")
@SessionScoped
public class DistrictApprovalsController implements Serializable {

    @Inject
    @Update
    @DistAdmin
    Event<ChildBirthDetail> approveBirthDetails;

    @Inject
    @Update
    @DistAdmin
    Event<DeceasedDetail> approveDeathDetails;

    @Inject
    @Update
    @DistAdmin
    Event<BirthCertRequest> approveBirthCert;

    @Inject
    @Update
    @DistAdmin
    Event<DeathCertRequest> approveDeathCert;

    @Inject
    private ChildBirthDetailFacade birthDetailFacade;
    @Inject
    private BirthCertRequestFacade birthCertRequestFacade;
    @Inject
    private DeceasedDetailFacade deceasedDetailFacade;
    @Inject
    private DeathCertRequestFacade deathCertRequestFacade;
    @Inject
    private SupportBean supportBean;

    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private BirthCertRequest birthCertRequest = new BirthCertRequest();
    private DeathCertRequest deathCertRequest = new DeathCertRequest();
    private ChildGuardian guardian = new ChildGuardian();
    private InformantDeath informantDeath = new InformantDeath();
    private SystemUser systemUser = new SystemUser();
    private List<ChildBirthDetail> birthDetailsList = new ArrayList<>();
    private List<DeceasedDetail> deceasedDetailsList = new ArrayList<>();
    private List<BirthCertRequest> birthCertRequestsList = new ArrayList<>();
    private List<DeathCertRequest> deathCertRequestsList = new ArrayList<>();
    private transient DataModel<ChildBirthDetail> birthDetailModel;
    private transient DataModel<DeceasedDetail> deceasedDetailModel;
    private transient DataModel<BirthCertRequest> birthCertModel;
    private transient DataModel<DeathCertRequest> deathCertModel;
    private boolean renderApproval = false;
    private int countDistBirthDetails = 0, countDistDeathDetails = 0, countDistBirthCert = 0, countDistDeathCert = 0;

    public DistrictApprovalsController() {
    }

    public SystemUser getSystemUser() {
        try {
            systemUser = (SystemUser) JSFUtility.getSessionValue(BirdsConstant.LOGIN_USER);
        } catch (Exception e) {
            systemUser = null;
        }
        return systemUser;
    }

    public void approveBirthDetails() {
        birthDetail.setDistrictApproved("YES");
        boolean saved = birthDetailFacade.updateBirthDetails(birthDetail);
        if (saved) {
            approveBirthDetails.fire(birthDetail);
            cancelRequest();
            JSFUtility.infoMessage("Success:", "Child Birth Details Has Been Successfully Saved");
        } else {
            JSFUtility.warnMessage("Error:", "An Error Was Encounted During Saving The Child Details.Please Try Again");
        }
    }

    public void approveBirthCertRequest() {
        birthCertRequest.setDistrictApproved("YES");
        boolean saved = birthCertRequestFacade.birthCertRequestUpdate(birthCertRequest);
        if (saved) {
            approveBirthCert.fire(birthCertRequest);
            cancelRequest();
            JSFUtility.infoMessage("Success:", "Child Birth Certificate Request Has Been Successfuly Approve And Submited For Printing");
        } else {
            JSFUtility.warnMessage("Error:", "Could Not Submit Birth Certificate Request.Please Try Again");
        }
    }

    public void approveDeathDetails() {
        deceasedDetail.setDistrictApproved("YES");
        boolean saved = deceasedDetailFacade.deceasedDetailUpdate(deceasedDetail);
        if (saved) {
            approveDeathDetails.fire(deceasedDetail);
            cancelRequest();
            JSFUtility.infoMessage("Success:", "Deceased Details Has Been Successfully Approved.");
        } else {
            JSFUtility.warnMessage("Error:", "An Error Was Encounted During Approval.Please Check And Try Again");
        }
    }

    public void approveDeathCertRequest() {
        deathCertRequest.setDistrictApproved("YES");
        boolean saved = deathCertRequestFacade.deathCertRequestUpdate(deathCertRequest);
        if (saved) {
            approveDeathCert.fire(deathCertRequest);
            cancelRequest();
            JSFUtility.infoMessage("Success:", "Deceased Certificate Request Has Been Successfully Approved And Submitted For Printing");
        } else {
            JSFUtility.warnMessage("Error:", "Could Not Submit Death Certificate Request.Please Try Again");
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

        renderApproval = true;
    }

    public void fetchDeathDetails() {
        deceasedDetail = (DeceasedDetail) deceasedDetailModel.getRowData();
        informantDeath = deceasedDetail.getInformantDeath();

        renderApproval = true;
    }

    public void fetchDeathCertRequestDetails() {
        deathCertRequest = (DeathCertRequest) deathCertModel.getRowData();

        deceasedDetail = deathCertRequest.getDeceasedDetails();
        informantDeath = deceasedDetail.getInformantDeath();
        renderApproval = true;
    }

    public void cancelRequest() {
        renderApproval = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public ChildBirthDetailFacade getBirthDetailFacade() {
        return birthDetailFacade;
    }

    public void setBirthDetailFacade(ChildBirthDetailFacade birthDetailFacade) {
        this.birthDetailFacade = birthDetailFacade;
    }

    public BirthCertRequestFacade getBirthCertRequestFacade() {
        return birthCertRequestFacade;
    }

    public void setBirthCertRequestFacade(BirthCertRequestFacade birthCertRequestFacade) {
        this.birthCertRequestFacade = birthCertRequestFacade;
    }

    public DeceasedDetailFacade getDeceasedDetailFacade() {
        return deceasedDetailFacade;
    }

    public void setDeceasedDetailFacade(DeceasedDetailFacade deceasedDetailFacade) {
        this.deceasedDetailFacade = deceasedDetailFacade;
    }

    public DeathCertRequestFacade getDeathCertRequestFacade() {
        return deathCertRequestFacade;
    }

    public void setDeathCertRequestFacade(DeathCertRequestFacade deathCertRequestFacade) {
        this.deathCertRequestFacade = deathCertRequestFacade;
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

    public List<ChildBirthDetail> getBirthDetailsList() {
        birthDetailsList = supportBean.listOfChildrenDetailsForDistApproval(getSystemUser().getDistrict().getDistrictId());
        return birthDetailsList;
    }

    public void setBirthDetailsList(List<ChildBirthDetail> birthDetailsList) {
        this.birthDetailsList = birthDetailsList;
    }

    public List<DeceasedDetail> getDeceasedDetailsList() {
        deceasedDetailsList = supportBean.listOfDeathDetailsForDistApproval(getSystemUser().getDistrict().getDistrictId());
        return deceasedDetailsList;
    }

    public void setDeceasedDetailsList(List<DeceasedDetail> deceasedDetailsList) {
        this.deceasedDetailsList = deceasedDetailsList;
    }

    public List<BirthCertRequest> getBirthCertRequestsList() {
        birthCertRequestsList = supportBean.listOfBirthCertForDistApproval(getSystemUser().getDistrict().getDistrictId());
        return birthCertRequestsList;
    }

    public void setBirthCertRequestsList(List<BirthCertRequest> birthCertRequestsList) {
        this.birthCertRequestsList = birthCertRequestsList;
    }

    public List<DeathCertRequest> getDeathCertRequestsList() {
        deathCertRequestsList = supportBean.listOfDeathCertForDistApproval(getSystemUser().getDistrict().getDistrictId());;
        return deathCertRequestsList;
    }

    public void setDeathCertRequestsList(List<DeathCertRequest> deathCertRequestsList) {
        this.deathCertRequestsList = deathCertRequestsList;
    }

    public DataModel<ChildBirthDetail> getBirthDetailModel() {
        birthDetailModel = new ListDataModel<>(getBirthDetailsList());
        return birthDetailModel;
    }

    public void setBirthDetailModel(DataModel<ChildBirthDetail> birthDetailModel) {
        this.birthDetailModel = birthDetailModel;
    }

    public DataModel<DeceasedDetail> getDeceasedDetailModel() {
        deceasedDetailModel = new ListDataModel<>(getDeceasedDetailsList());
        return deceasedDetailModel;
    }

    public void setDeceasedDetailModel(DataModel<DeceasedDetail> deceasedDetailModel) {
        this.deceasedDetailModel = deceasedDetailModel;
    }

    public DataModel<BirthCertRequest> getBirthCertModel() {
        birthCertModel = new ListDataModel<>(getBirthCertRequestsList());
        return birthCertModel;
    }

    public void setBirthCertModel(DataModel<BirthCertRequest> birthCertModel) {
        this.birthCertModel = birthCertModel;
    }

    public DataModel<DeathCertRequest> getDeathCertModel() {
        deathCertModel = new ListDataModel<>(getDeathCertRequestsList());
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

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public int getCountDistBirthDetails() {
        countDistBirthDetails = getBirthDetailModel().getRowCount();
        return countDistBirthDetails;
    }

    public void setCountDistBirthDetails(int countDistBirthDetails) {
        this.countDistBirthDetails = countDistBirthDetails;
    }

    public int getCountDistDeathDetails() {
        countDistDeathDetails = getDeceasedDetailModel().getRowCount();
        return countDistDeathDetails;
    }

    public void setCountDistDeathDetails(int countDistDeathDetails) {
        this.countDistDeathDetails = countDistDeathDetails;
    }

    public int getCountDistBirthCert() {
        countDistBirthCert = getBirthCertModel().getRowCount();
        return countDistBirthCert;
    }

    public void setCountDistBirthCert(int countDistBirthCert) {
        this.countDistBirthCert = countDistBirthCert;
    }

    public int getCountDistDeathCert() {
        countDistDeathCert = getDeathCertModel().getRowCount();
        return countDistDeathCert;
    }

    public void setCountDistDeathCert(int countDistDeathCert) {
        this.countDistDeathCert = countDistDeathCert;
    }
//</editor-fold>

}
