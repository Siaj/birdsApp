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
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.District;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.controllers.qualifiers.Create;
import com.app.birds.web.utilities.CommonUtil;
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
@Named(value = "certificateRequest")
@SessionScoped
public class CertificateRequestController implements Serializable {
    
    @Inject
    @Create
    Event<BirthCertRequest> createBirthCertRequest;
    
    @Inject
    @Create
    Event<DeathCertRequest> createDeathCertRequest;

    @Inject
    private BirthCertRequestFacade birthCertRequestFacade;
    @Inject
    private DeathCertRequestFacade deathCertRequestFacade;
    @Inject
    private ChildBirthDetailFacade birthDetailFacade;
    @Inject
    private DeceasedDetailFacade deceasedDetailFacade;

    private BirthCertRequest birthCertRequest = new BirthCertRequest();
    private DeathCertRequest deathCertRequest = new DeathCertRequest();
    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private SystemUser systemUser = new SystemUser();
    private District district = new District();
    private ChildGuardian guardian = new ChildGuardian();
    private InformantDeath informantDeath = new InformantDeath();

    private List<ChildBirthDetail> listOfBirths = new ArrayList<>();
    private List<DeceasedDetail> listOfDeaths = new ArrayList<>();
    private transient DataModel<ChildBirthDetail> childDataModel;
    private transient DataModel<DeceasedDetail> deceasedDataModel;

    private String searchText, searchCriteria;
    boolean renderBirthDetails = false, renderDeathDetails = false;

    public CertificateRequestController() {
    }

    public void searchBirthDetails() {
        if (searchCriteria.equals("null") || searchText.equals("")) {
            JSFUtility.warnMessage("Error:", "All Fiels are Required Before Search Can Be Done.Please Enter All Fields");
        } else {
            listOfBirths = birthDetailFacade.birthDetailFindByAttributeForCert(searchCriteria, searchText, "STRING", true);
        }
    }

    public void searchDeathDetails() {
        if (searchCriteria.equals("null") || searchText.equals("")) {
            JSFUtility.warnMessage("Error:", "All Fiels are Required Before Search Can Be Done.Please Enter All Fields");
        } else {
            listOfDeaths = deceasedDetailFacade.deceasedDetailFindByAttributeforCert(searchCriteria, searchText, "STRING", true);
        }
    }

    public void resetEntry() {
        birthCertRequest = new BirthCertRequest();
        deathCertRequest = new DeathCertRequest();
        birthDetail = new ChildBirthDetail();
        deceasedDetail = new DeceasedDetail();
        systemUser = new SystemUser();
        district = new District();
        searchCriteria = null;
        searchText = "";
        listOfBirths = new ArrayList<>();
        listOfDeaths = new ArrayList<>();
        renderBirthDetails = false;
        renderDeathDetails = false;
    }

    public void sendBirthCertRequest() {
        birthCertRequest.setBirthApplicantId(birthDetail);

        birthCertRequest.setBirthCertRequestId(CommonUtil.generateID());

        birthCertRequest.setDistrict(district);
        birthCertRequest.setSystemUserId(systemUser.getSystemUserId());
        birthCertRequest.setDistrictApproved("NO");
        birthCertRequest.setRegionalApproved("NO");
        birthCertRequest.setCertPrinted("NO");

        String saved = birthCertRequestFacade.birthCertRequestCreate(birthCertRequest);
        if (saved != null) {
            createBirthCertRequest.fire(birthCertRequest);
            cancelBirthCertRequest();
            JSFUtility.infoMessage("Success:", "Birth Certificate request Has Been Successfully Sent For Approval and Printing.");
        } else {
            JSFUtility.errorMessage("Error:", "A System Error Occured During Sending Birth Certificate Request, Please Try Sending Again");
        }
    }

    public void cancelBirthCertRequest() {
        resetEntry();
        renderBirthDetails = false;
    }

    public void sendDeathCertRequest() {
        deathCertRequest.setDeceasedDetails(deceasedDetail);

        deathCertRequest.setDeathCertRequestId(CommonUtil.generateID());

        deathCertRequest.setDistrict(district);
        deathCertRequest.setSystemUser(systemUser.getSystemUserId());
        deathCertRequest.setDistrictApproved("NO");
        deathCertRequest.setRegionalApproved("NO");
        deathCertRequest.setCertPrinted("NO");

        String saved = deathCertRequestFacade.deathCertRequestCreate(deathCertRequest);
        if (saved != null) {
            createDeathCertRequest.fire(deathCertRequest);
            cancelDeathCertRequest();
            JSFUtility.infoMessage("Success:", "Death Certificate request Has Been Successfully Sent For Approval and Printing.");
        } else {
            JSFUtility.errorMessage("Error:", "A System Error Occured During Sending Death Certificate Request, Please Try Sending Again");
        }
    }

    public void cancelDeathCertRequest() {
        resetEntry();
        renderDeathDetails = false;
    }

    public void fetchBirthDetails() {
        birthDetail = (ChildBirthDetail) childDataModel.getRowData();
        guardian = birthDetail.getGuardian();
        systemUser = birthDetail.getSystemUser();
        district = systemUser.getDistrict();

        if (birthDetail != null || systemUser != null || district != null || guardian != null) {
            renderBirthDetails = true;
        }
    }

    public void fetchDeathDetails() {
        deceasedDetail = (DeceasedDetail) deceasedDataModel.getRowData();
        informantDeath = deceasedDetail.getInformantDeath();
        systemUser = deceasedDetail.getSystemUser();
        district = systemUser.getDistrict();

        if (birthDetail != null || systemUser != null || district != null || informantDeath != null) {
            renderDeathDetails = true;
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public BirthCertRequestFacade getBirthCertRequestFacade() {
        return birthCertRequestFacade;
    }

    public void setBirthCertRequestFacade(BirthCertRequestFacade birthCertRequestFacade) {
        this.birthCertRequestFacade = birthCertRequestFacade;
    }

    public DeathCertRequestFacade getDeathCertRequestFacade() {
        return deathCertRequestFacade;
    }

    public void setDeathCertRequestFacade(DeathCertRequestFacade deathCertRequestFacade) {
        this.deathCertRequestFacade = deathCertRequestFacade;
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

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
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

    public List<ChildBirthDetail> getListOfBirths() {
        return listOfBirths;
    }

    public void setListOfBirths(List<ChildBirthDetail> listOfBirths) {
        this.listOfBirths = listOfBirths;
    }

    public List<DeceasedDetail> getListOfDeaths() {
        return listOfDeaths;
    }

    public void setListOfDeaths(List<DeceasedDetail> listOfDeaths) {
        this.listOfDeaths = listOfDeaths;
    }

    public DataModel<ChildBirthDetail> getChildDataModel() {
        childDataModel = new ListDataModel<>(getListOfBirths());
        return childDataModel;
    }

    public void setChildDataModel(DataModel<ChildBirthDetail> childDataModel) {
        this.childDataModel = childDataModel;
    }

    public DataModel<DeceasedDetail> getDeceasedDataModel() {
        deceasedDataModel = new ListDataModel<>(getListOfDeaths());
        return deceasedDataModel;
    }

    public void setDeceasedDataModel(DataModel<DeceasedDetail> deceasedDataModel) {
        this.deceasedDataModel = deceasedDataModel;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public boolean isRenderBirthDetails() {
        return renderBirthDetails;
    }

    public void setRenderBirthDetails(boolean renderBirthDetails) {
        this.renderBirthDetails = renderBirthDetails;
    }

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

    public boolean isRenderDeathDetails() {
        return renderDeathDetails;
    }

    public void setRenderDeathDetails(boolean renderDeathDetails) {
        this.renderDeathDetails = renderDeathDetails;
    }
//</editor-fold>
}
