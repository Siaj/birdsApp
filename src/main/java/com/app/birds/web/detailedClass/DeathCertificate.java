/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.detailedClass;

import com.app.birds.ejbSessions.DeathCertRequestFacade;
import com.app.birds.ejbSessions.SupportBean;
import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.reports.ReportController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named
@SessionScoped
public class DeathCertificate implements Certificate, Serializable {

    @Inject
    private SupportBean supportBean;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private DeathCertRequestFacade deathCertRequestFacade;

    private DeathCertRequest deathCertRequest = new DeathCertRequest();
    private SystemUser systemUser = new SystemUser();
    private List<DeathCertRequest> deathCertRequestsList = new ArrayList<>();
    private transient DataModel<DeathCertRequest> deathCertModel;
    private UserAccessController accessController = new UserAccessController();
    private String regId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();
    private String selectedDistrict;

    public void loadDeathCerts() {
        deathCertRequestsList = supportBean.listOfDeathCertForRegApprovalAndPrint(selectedDistrict);
        deathCertModel = new ListDataModel<>(deathCertRequestsList);
    }

    @Override
    public void generateCertificate() {
        List<DeceasedDetailClass> ddcs;

        deathCertRequest = (DeathCertRequest) deathCertModel.getRowData();
        String user_id = deathCertRequest.getDeceasedDetails().getSystemUser().getSystemUserId();
        systemUser = systemUserFacade.systemUserFind(user_id);

        DeceasedDetailClass deceasedDetailClass = new DeceasedDetailClass();

        ddcs = deceasedDetailClass.loadDeceased(deathCertRequest, systemUser);

        deathCertRequest.setRegionalApproved("YES");
        deathCertRequest.setCertPrinted("YES");
        deathCertRequestFacade.deathCertRequestUpdate(deathCertRequest);

        ReportController.getInstance().loadDefaultParameters();
        ReportController.getInstance().showReport(ddcs, getClass().getResourceAsStream(ReportController.DEATH_CERTIFICATE));
    }

    public SupportBean getSupportBean() {
        return supportBean;
    }

    public void setSupportBean(SupportBean supportBean) {
        this.supportBean = supportBean;
    }

    public SystemUserFacade getSystemUserFacade() {
        return systemUserFacade;
    }

    public void setSystemUserFacade(SystemUserFacade systemUserFacade) {
        this.systemUserFacade = systemUserFacade;
    }

    public DeathCertRequestFacade getDeathCertRequestFacade() {
        return deathCertRequestFacade;
    }

    public void setDeathCertRequestFacade(DeathCertRequestFacade deathCertRequestFacade) {
        this.deathCertRequestFacade = deathCertRequestFacade;
    }

    public DeathCertRequest getDeathCertRequest() {
        return deathCertRequest;
    }

    public void setDeathCertRequest(DeathCertRequest deathCertRequest) {
        this.deathCertRequest = deathCertRequest;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public List<DeathCertRequest> getDeathCertRequestsList() {
        return deathCertRequestsList;
    }

    public void setDeathCertRequestsList(List<DeathCertRequest> deathCertRequestsList) {
        this.deathCertRequestsList = deathCertRequestsList;
    }

    public DataModel<DeathCertRequest> getDeathCertModel() {
        return deathCertModel;
    }

    public void setDeathCertModel(DataModel<DeathCertRequest> deathCertModel) {
        this.deathCertModel = deathCertModel;
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

    public String getSelectedDistrict() {
        return selectedDistrict;
    }

    public void setSelectedDistrict(String selectedDistrict) {
        this.selectedDistrict = selectedDistrict;
    }

}
