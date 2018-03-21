/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.detailedClass;

import com.app.birds.ejbSessions.BirthCertRequestFacade;
import com.app.birds.ejbSessions.SupportBean;
import com.app.birds.ejbSessions.SystemUserFacade;
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.ChildGuardian;
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
public class BirthCertificate implements Certificate, Serializable {

    @Inject
    private SupportBean supportBean;
    @Inject
    private SystemUserFacade systemUserFacade;
    @Inject
    private BirthCertRequestFacade birthCertRequestFacade;

    private BirthCertRequest birthCertRequest = new BirthCertRequest();
    private ChildGuardian guardian = new ChildGuardian();
    private SystemUser systemUser = new SystemUser();
    private List<BirthCertRequest> birthCertRequestsList = new ArrayList<>();
    private transient DataModel<BirthCertRequest> birthCertModel;
    private UserAccessController accessController = new UserAccessController();
    private String regId = accessController.getSystemUser().getDistrict().getRegion().getRegionId();
    private String selectedDistrict;

    public void loadBirthCerts() {
        birthCertRequestsList = supportBean.listOfBirthCertForRegApprovalAndPrint(selectedDistrict);
        birthCertModel = new ListDataModel<>(birthCertRequestsList);
    }

    public void resetDistrictSelected() {
        selectedDistrict = "";
        loadBirthCerts();
    }

    @Override
    public void generateCertificate() {
        List<BirthDetailedClass> bdcsList;
        System.out.println("In generateCertificate for Birth Method");
        birthCertRequest = (BirthCertRequest) birthCertModel.getRowData();
        String user_id = birthCertRequest.getBirthApplicantId().getSystemUser().getSystemUserId();
        System.out.println(user_id);
        systemUser = systemUserFacade.systemUserFind(user_id);
        System.out.println(systemUser.getFirstName());
        BirthDetailedClass birthDetailedClass = new BirthDetailedClass();

        bdcsList = birthDetailedClass.loadBirth(birthCertRequest, systemUser);

//        birthCertRequest.setRegionalApproved("YES");
//        birthCertRequest.setCertPrinted("YES");
//        birthCertRequestFacade.birthCertRequestUpdate(birthCertRequest);
        ReportController.getInstance().loadDefaultParameters();
        ReportController.getInstance().showReport(bdcsList, getClass().getResourceAsStream(ReportController.BIRTH_CERTIFICATE));
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

    public BirthCertRequestFacade getBirthCertRequestFacade() {
        return birthCertRequestFacade;
    }

    public void setBirthCertRequestFacade(BirthCertRequestFacade birthCertRequestFacade) {
        this.birthCertRequestFacade = birthCertRequestFacade;
    }

    public BirthCertRequest getBirthCertRequest() {
        return birthCertRequest;
    }

    public void setBirthCertRequest(BirthCertRequest birthCertRequest) {
        this.birthCertRequest = birthCertRequest;
    }

    public ChildGuardian getGuardian() {
        return guardian;
    }

    public void setGuardian(ChildGuardian guardian) {
        this.guardian = guardian;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public List<BirthCertRequest> getBirthCertRequestsList() {
        return birthCertRequestsList;
    }

    public void setBirthCertRequestsList(List<BirthCertRequest> birthCertRequestsList) {
        this.birthCertRequestsList = birthCertRequestsList;
    }

    public DataModel<BirthCertRequest> getBirthCertModel() {
        return birthCertModel;
    }

    public void setBirthCertModel(DataModel<BirthCertRequest> birthCertModel) {
        this.birthCertModel = birthCertModel;
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
