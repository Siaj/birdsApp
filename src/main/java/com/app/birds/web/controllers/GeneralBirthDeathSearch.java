/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.ChildBirthDetailFacade;
import com.app.birds.ejbSessions.ChildGuardianFacade;
import com.app.birds.ejbSessions.DeceasedDetailFacade;
import com.app.birds.ejbSessions.InformantBirthFacade;
import com.app.birds.ejbSessions.InformantDeathFacade;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.UserAccessController;
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
@Named(value = "generalSearch")
@SessionScoped
public class GeneralBirthDeathSearch implements Serializable {

    @Inject
    ChildBirthDetailFacade birthDetailFacade;
    @Inject
    DeceasedDetailFacade deceasedDetailFacade;
    @Inject
    InformantBirthFacade informantBirthFacade;
    @Inject
    InformantDeathFacade informantDeathFacade;
    @Inject
    ChildGuardianFacade birthGuardianFacade;

    private SystemUser systemUser = new SystemUser();
    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private ChildGuardian guardian = new ChildGuardian();
    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private InformantDeath informantDeath = new InformantDeath();
    private UserAccessController accessController = new UserAccessController();

    private List<ChildBirthDetail> childDetails = new ArrayList<>();
    private transient DataModel<ChildBirthDetail> birthDataModel;
    private List<DeceasedDetail> listOfDeceasd = new ArrayList<>();
    private transient DataModel<DeceasedDetail> deceasedDataModel;

    private String birthSearchCriteria, birthSearchText;
    private String deceasedSearchCriteria, deceasedSearchText;
    private boolean renderBirthDetails = false, renderDeceasedDetails = false;
    private boolean renderBirthDetailsforEdit = false, renderDeceasedDetailsforEdit = false;
    private boolean renderBirthSearchTable = true, renderDeathSearchTable = true;

    public GeneralBirthDeathSearch() {
    }

    //    Birth Details Search Page Functions
    public void searchBirthDetails() {
        if (birthSearchCriteria == null || birthSearchCriteria.equals("null") || birthSearchText.equals("")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            childDetails = getChildDetails();
        } else {
            childDetails = getChildDetails();
        }
    }

    public void resetBirthSearch() {
        birthSearchCriteria = null;
        birthSearchText = "";
        renderBirthDetails = false;
        birthDetail = new ChildBirthDetail();
        childDetails = getChildDetails();
    }

    public void closeBirthSearch() {
        renderBirthDetails = false;
    }

    public void fetchBirthRowData() {
        birthDetail = (ChildBirthDetail) birthDataModel.getRowData();
        guardian = birthDetail.getGuardian();

        if (birthDetail != null && guardian != null) {
            renderBirthDetails = true;
        }
    }

    public void ammendBirthDetails() {
        String fullname = getBirthDetail().getSurname().toUpperCase() + " " + getBirthDetail().getOthername();
        System.out.println(fullname);

        getBirthDetail().setSurname(getBirthDetail().getSurname());
        getBirthDetail().setOthername(getBirthDetail().getOthername());
        getBirthDetail().setFullName(fullname);
        getBirthDetail().setUpdated("YES");

        boolean ammended = birthDetailFacade.updateBirthDetails(getBirthDetail());
        if (ammended) {
            renderBirthDetails = false;
            JSFUtility.infoMessage("Success: ", "Birth Registration Details Succesfully Updated");
        } else {
            JSFUtility.errorMessage("Error: ", "An Error Occurred During Birth Details Ammend. Please Check and Try Again");
        }
    }

//    Deceased Details Search Page Functions
    public void searchDeceasedDetails() {
        if (deceasedSearchCriteria == null || deceasedSearchCriteria.equals("null") || deceasedSearchText.equals("")) {
            JSFUtility.warnMessage("Info: ", "Search Parameters not well defined to complete search, please enter them to find a specific detail");
            listOfDeceasd = getListOfDeceasd();
        } else {
            listOfDeceasd = getListOfDeceasd();
        }
    }

    public void resetDeceasedSearch() {
        deceasedDetail = new DeceasedDetail();
        listOfDeceasd = getListOfDeceasd();
        deceasedSearchCriteria = null;
        deceasedSearchText = "";
        renderDeceasedDetails = false;
    }

    public void closeDeceasedSearch() {
        renderDeceasedDetails = false;
    }

    public void fetchDeceasedRowData() {
        deceasedDetail = (DeceasedDetail) deceasedDataModel.getRowData();
        informantDeath = deceasedDetail.getInformantDeath();

        if (deceasedDetail != null && informantDeath != null) {
            renderDeceasedDetails = true;
        } else {
            System.out.println("Error occurred during table row retreival");
            JSFUtility.errorMessage("Error: ", "An Error occurred during table row retreival");
        }
    }

//<editor-fold defaultstate="collapsed" desc="Getters and Setters Methods">
//Getter and Setter Methods for private declarations
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

    public InformantBirthFacade getInformantBirthFacade() {
        return informantBirthFacade;
    }

    public void setInformantBirthFacade(InformantBirthFacade informantBirthFacade) {
        this.informantBirthFacade = informantBirthFacade;
    }

    public InformantDeathFacade getInformantDeathFacade() {
        return informantDeathFacade;
    }

    public void setInformantDeathFacade(InformantDeathFacade informantDeathFacade) {
        this.informantDeathFacade = informantDeathFacade;
    }

    public ChildGuardianFacade getBirthGuardianFacade() {
        return birthGuardianFacade;
    }

    public void setBirthGuardianFacade(ChildGuardianFacade birthGuardianFacade) {
        this.birthGuardianFacade = birthGuardianFacade;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public ChildBirthDetail getBirthDetail() {
        return birthDetail;
    }

    public void setBirthDetail(ChildBirthDetail birthDetail) {
        this.birthDetail = birthDetail;
    }

    public ChildGuardian getGuardian() {
        return guardian;
    }

    public void setGuardian(ChildGuardian guardian) {
        this.guardian = guardian;
    }

    public DeceasedDetail getDeceasedDetail() {
        return deceasedDetail;
    }

    public void setDeceasedDetail(DeceasedDetail deceasedDetail) {
        this.deceasedDetail = deceasedDetail;
    }

    public InformantDeath getInformantDeath() {
        return informantDeath;
    }

    public void setInformantDeath(InformantDeath informantDeath) {
        this.informantDeath = informantDeath;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public List<ChildBirthDetail> getChildDetails() {
        if (birthSearchCriteria == null || birthSearchCriteria.equals("null") || birthSearchText.equals("")) {
            childDetails = birthDetailFacade.birthDetailGetAll(true);
        } else {
            childDetails = birthDetailFacade.birthDetailFindByAttribute(getBirthSearchCriteria(), getBirthSearchText(), "STRING", true);
        }
        return childDetails;
    }

    public void setChildDetails(List<ChildBirthDetail> childDetails) {
        this.childDetails = childDetails;
    }

    public DataModel<ChildBirthDetail> getBirthDataModel() {
        birthDataModel = new ListDataModel<>(getChildDetails());
        return birthDataModel;
    }

    public void setBirthDataModel(DataModel<ChildBirthDetail> birthDataModel) {
        this.birthDataModel = birthDataModel;
    }

    public List<DeceasedDetail> getListOfDeceasd() {
        if (deceasedSearchCriteria == null || deceasedSearchCriteria.equals("null") || deceasedSearchText.equals("")) {
            listOfDeceasd = deceasedDetailFacade.deceasedDetailGetAll(true);
        } else {
            listOfDeceasd = deceasedDetailFacade.deceasedDetailFindByAttribute(deceasedSearchCriteria, deceasedSearchText, "STRING", true);
        }
        return listOfDeceasd;
    }

    public void setListOfDeceasd(List<DeceasedDetail> listOfDeceasd) {
        this.listOfDeceasd = listOfDeceasd;
    }

    public DataModel<DeceasedDetail> getDeceasedDataModel() {
        deceasedDataModel = new ListDataModel<>(getListOfDeceasd());
        return deceasedDataModel;
    }

    public void setDeceasedDataModel(DataModel<DeceasedDetail> deceasedDataModel) {
        this.deceasedDataModel = deceasedDataModel;
    }

    public String getBirthSearchCriteria() {
        return birthSearchCriteria;
    }

    public void setBirthSearchCriteria(String birthSearchCriteria) {
        this.birthSearchCriteria = birthSearchCriteria;
    }

    public String getBirthSearchText() {
        return birthSearchText;
    }

    public void setBirthSearchText(String birthSearchText) {
        this.birthSearchText = birthSearchText;
    }

    public String getDeceasedSearchCriteria() {
        return deceasedSearchCriteria;
    }

    public void setDeceasedSearchCriteria(String deceasedSearchCriteria) {
        this.deceasedSearchCriteria = deceasedSearchCriteria;
    }

    public String getDeceasedSearchText() {
        return deceasedSearchText;
    }

    public void setDeceasedSearchText(String deceasedSearchText) {
        this.deceasedSearchText = deceasedSearchText;
    }

    public boolean isRenderBirthDetails() {
        return renderBirthDetails;
    }

    public void setRenderBirthDetails(boolean renderBirthDetails) {
        this.renderBirthDetails = renderBirthDetails;
    }

    public boolean isRenderDeceasedDetails() {
        return renderDeceasedDetails;
    }

    public void setRenderDeceasedDetails(boolean renderDeceasedDetails) {
        this.renderDeceasedDetails = renderDeceasedDetails;
    }

    public boolean isRenderDeceasedDetailsforEdit() {
        return renderDeceasedDetailsforEdit;
    }

    public void setRenderDeceasedDetailsforEdit(boolean renderDeceasedDetailsforEdit) {
        this.renderDeceasedDetailsforEdit = renderDeceasedDetailsforEdit;
    }

    public boolean isRenderBirthSearchTable() {
        return renderBirthSearchTable;
    }

    public void setRenderBirthSearchTable(boolean renderBirthSearchTable) {
        this.renderBirthSearchTable = renderBirthSearchTable;
    }

    public boolean isRenderDeathSearchTable() {
        return renderDeathSearchTable;
    }

    public void setRenderDeathSearchTable(boolean renderDeathSearchTable) {
        this.renderDeathSearchTable = renderDeathSearchTable;
    }

    public boolean isRenderBirthDetailsforEdit() {
        return renderBirthDetailsforEdit;
    }

    public void setRenderBirthDetailsforEdit(boolean renderBirthDetailsforEdit) {
        this.renderBirthDetailsforEdit = renderBirthDetailsforEdit;
    }
//</editor-fold>

}
