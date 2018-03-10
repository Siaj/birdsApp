/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.DeceasedDetailFacade;
import com.app.birds.ejbSessions.DistrictCenterFacade;
import com.app.birds.ejbSessions.GenIdFacade;
import com.app.birds.ejbSessions.InformantDeathFacade;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.entities.DistrictCenter;
import com.app.birds.entities.GenId;
import com.app.birds.entities.InformantDeath;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.utilities.CommonUtil;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "deceased_registration_model")
@SessionScoped
public class DeceasedRegistrationController implements Serializable {

    @Inject
    private DeceasedDetailFacade deceasedDetailFacade;
    @Inject
    private InformantDeathFacade informantDeathFacade;
    @Inject
    private GenIdFacade genIdFacade;
    @Inject
    private DistrictCenterFacade centerFacade;

    private DeceasedDetail deceasedDetail = new DeceasedDetail();
    private InformantDeath informantDeath = new InformantDeath();
    private DistrictCenter districtCenter = new DistrictCenter();
    private UserAccessController accessController = new UserAccessController();
    private SystemUser systemUser = new SystemUser();
    private GenId genId = new GenId();

    private String deceasedBurialStatus;
    private boolean renderBurriedStatus = false;
    private String approved_deceased_id;

    private Calendar calendar = new GregorianCalendar();
    private Date dateOfRegistry = new Date();
    private Date dateOfDeath = new Date();
    private String doD;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    public DeceasedRegistrationController() {
    }

    public String generateDeceasedRegistryId() {
        System.out.println("District Id: " + accessController.getUserAccount().getSystemUser().getDistrict().getDistrictId());
        genId = genIdFacade.findWithDistrictId(accessController.getUserAccount().getSystemUser().getDistrict().getDistrictId());
        if (genId != null) {
            System.out.println("LDN = " + genId.getDistLastBirthNum());
            Integer entry_number = genId.getDistLastBirthNum() + 1;
            String entry_year = String.valueOf(calendar.get(Calendar.YEAR));

//        Generated BirthId for the registration being entered
            approved_deceased_id = accessController.getUserAccount().getSystemUser().getDistrict().getDistrictId() + "-"
                    + entry_number.toString() + "-" + entry_year;
            System.out.println("Approved Birth Id: " + approved_deceased_id);

            genId.setDistLastDeathNum(entry_number);
            genIdFacade.edit(genId);
            return approved_deceased_id;
        } else {
//        Create a new entry and set last birth number to zero..After wards you can work with it
//        May also be created when creatin a new district so as to make things easier and well organized

        }

        return approved_deceased_id;
    }

    public void deceasedRegistration() {
        districtCenter = centerFacade.districtCenterFind(accessController.getUserAccount().getSystemUser().getDistrictCenter().getCenterId());
        System.out.println("Selected Center: " + districtCenter.getCenterId() + " " + districtCenter.getCenterName());

        String deceasedId = generateDeceasedRegistryId();

        informantDeath.setInformantDeathId(CommonUtil.generateID());
        deceasedDetail.setInformantDeath(informantDeath);

        deceasedDetail.setSystemUser(accessController.getUserAccount().getSystemUser());

        deceasedDetail.setDeceasedId(deceasedId);
        deceasedDetail.setCenterRegistered(districtCenter.getCenterName());
        deceasedDetail.setDeceasedFullname(deceasedDetail.getSurname().toUpperCase() + " " + deceasedDetail.getOthername());
        deceasedDetail.setDateOfDeath(new Date(doD));
        deceasedDetail.setDateOfRegistration(dateOfRegistry);
        deceasedDetail.setDistrictApproved("NO");
        deceasedDetail.setRegionalApproved("NO");

        String informantSaved = informantDeathFacade.informantDeathCreate(informantDeath);
        String deceasedDetailSaved = deceasedDetailFacade.deceasedDetailCreate(deceasedDetail);

        if (informantSaved != null && deceasedDetailSaved != null) {
            JSFUtility.infoMessage("Success:", "Registration Successful.Deceased Details Has Been Successfully Saved, pending updates from Regional Office");
            resetButton();
        } else {
            JSFUtility.warnMessage("Error:", "Registration Failed.Couldn't Save Death Detail.Please Check Your Entries and Try Again");
        }
    }

    public void checkBeforeSave() {
        if (new Date(doD).after(calendar.getTime())) {
            JSFUtility.warnMessage("Error: ", "Date of Death cannot be greater than today's date, Please check and update");
        } else {
            /*Go ahead and save details*/

            deceasedRegistration();
            JSFUtility.infoMessage("Success: ", "Deceased Details successfully registered into system, pending approvals from Regionl Office");
        }
    }

    public void saveButton() {
        if (deceasedBurialStatus.equals("null")) {
            JSFUtility.warnMessage("Alert:", "Deceased Burial Status Is Required For Further Processing");
        } else {
            if (deceasedBurialStatus.equals("Not Buried")) {
                checkBeforeSave();
            } else if (deceasedBurialStatus.equals("Buried")) {
                if (deceasedDetail.getPlaceOfBurial().equals("") || deceasedDetail.getAddressOfBurialPlace().equals("")) {
                    JSFUtility.warnMessage("Error:", "Place of Burial and Address are Required");
                } else {
                    checkBeforeSave();
                }
            }
        }
    }

    public void resetButton() {
        deceasedDetail = new DeceasedDetail();
        informantDeath = new InformantDeath();
        doD = "";
        deceasedBurialStatus = null;
        renderBurriedStatus = false;
    }

    public void setViewOnDeceasedBurialStatus(ValueChangeEvent event) throws ParseException {
        try {
            renderBurriedStatus = event.getNewValue().equals("Buried");
        } catch (Exception e) {
            renderBurriedStatus = false;
            e.printStackTrace();
        }
    }

    public String getDeceasedBurialStatus() {
        return deceasedBurialStatus;
    }

    public void setDeceasedBurialStatus(String deceasedBurialStatus) {
        this.deceasedBurialStatus = deceasedBurialStatus;
    }

    public String getDoD() {
        return doD;
    }

    public void setDoD(String doD) {
        this.doD = doD;
    }

    public String getApproved_deceased_id() {
        return approved_deceased_id;
    }

    public void setApproved_deceased_id(String approved_deceased_id) {
        this.approved_deceased_id = approved_deceased_id;
    }

    public boolean isRenderBurriedStatus() {
        return renderBurriedStatus;
    }

    public void setRenderBurriedStatus(boolean renderBurriedStatus) {
        this.renderBurriedStatus = renderBurriedStatus;
    }

    public DeceasedDetailFacade getDeceasedDetailFacade() {
        return deceasedDetailFacade;
    }

    public void setDeceasedDetailFacade(DeceasedDetailFacade deceasedDetailFacade) {
        this.deceasedDetailFacade = deceasedDetailFacade;
    }

    public InformantDeathFacade getInformantDeathFacade() {
        return informantDeathFacade;
    }

    public void setInformantDeathFacade(InformantDeathFacade informantDeathFacade) {
        this.informantDeathFacade = informantDeathFacade;
    }

    public GenIdFacade getGenIdFacade() {
        return genIdFacade;
    }

    public void setGenIdFacade(GenIdFacade genIdFacade) {
        this.genIdFacade = genIdFacade;
    }

    public DistrictCenterFacade getCenterFacade() {
        return centerFacade;
    }

    public void setCenterFacade(DistrictCenterFacade centerFacade) {
        this.centerFacade = centerFacade;
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

    public DistrictCenter getDistrictCenter() {
        return districtCenter;
    }

    public void setDistrictCenter(DistrictCenter districtCenter) {
        this.districtCenter = districtCenter;
    }

    public UserAccessController getAccessController() {
        return accessController;
    }

    public void setAccessController(UserAccessController accessController) {
        this.accessController = accessController;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public GenId getGenId() {
        return genId;
    }

    public void setGenId(GenId genId) {
        this.genId = genId;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDateOfRegistry() {
        return dateOfRegistry;
    }

    public void setDateOfRegistry(Date dateOfRegistry) {
        this.dateOfRegistry = dateOfRegistry;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

}
