/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.ChildBirthDetailFacade;
import com.app.birds.ejbSessions.ChildGuardianFacade;
import com.app.birds.ejbSessions.DistrictCenterFacade;
import com.app.birds.ejbSessions.GenIdFacade;
import com.app.birds.ejbSessions.InformantBirthFacade;
import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.ChildGuardian;
import com.app.birds.entities.DistrictCenter;
import com.app.birds.entities.GenId;
import com.app.birds.entities.InformantBirth;
import com.app.birds.web.commons.UserAccessController;
import com.app.birds.web.controllers.qualifiers.Create;
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
import javax.enterprise.event.Event;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "birth_registration_model")
@SessionScoped
public class BirthRegistrationController implements Serializable {

    @Inject
    @Create
    Event<ChildBirthDetail> birthDetailsCreate;

    @Inject
    private ChildBirthDetailFacade birthDetailFacade;
    @Inject
    private ChildGuardianFacade guardianFacade;
    @Inject
    private InformantBirthFacade informantBirthFacade;
    @Inject
    private GenIdFacade genIdFacade;
    @Inject
    private DistrictCenterFacade centerFacade;

    private ChildBirthDetail birthDetail = new ChildBirthDetail();
    private InformantBirth informantBirth = new InformantBirth();
    private ChildGuardian childGuardian = new ChildGuardian();
    private DistrictCenter districtCenter = new DistrictCenter();
    private UserAccessController accessController = new UserAccessController();
    private GenId genId = new GenId();

    private String birthInformantRelation, i_name, id_type, id_num, residence;
    private boolean renderBirthInformant = false;
    private String approved_birth_id;
    private String doB;

    private Calendar calendar = new GregorianCalendar();
    private Date registrationDate = new Date();
    private Date registryTime = new Date();
    private Date dateOfBirth = new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

    public BirthRegistrationController() {
    }

    public String generateBirthRegistryId() {
        System.out.println("District Id: " + accessController.getSystemUser().getDistrict().getDistrictId());
        genId = genIdFacade.findWithDistrictId(accessController.getSystemUser().getDistrict().getDistrictId());
        if (genId != null) {
            System.out.println("LBN = " + genId.getDistLastBirthNum());
            Integer entry_number = genId.getDistLastBirthNum() + 1;
            String entry_year = String.valueOf(calendar.get(Calendar.YEAR));

//        Generated BirthId for the registration being entered
            approved_birth_id = accessController.getSystemUser().getDistrict().getDistrictId() + "-"
                    + entry_number.toString() + "-" + entry_year;
            System.out.println("Approved Birth Id: " + approved_birth_id);

            genId.setDistLastBirthNum(entry_number);
            genIdFacade.edit(genId);
            return approved_birth_id;
        } else {
//        Create a new entry and set last birth number to zero..After wards you can work with it
//        May also be created when creatin a new district so as to make things easier and well organized

        }

        return approved_birth_id;
    }

    public void saveBirthRegistryDetails() {
        districtCenter = centerFacade.districtCenterFind(accessController.getSystemUser().getDistrictCenter().getCenterId());
        System.out.println("Selected Center: " + districtCenter.getCenterId() + " " + districtCenter.getCenterName());

        String birthId = generateBirthRegistryId();

        childGuardian.setGuardianId(CommonUtil.generateID());
        birthDetail.setGuardian(childGuardian);
        childGuardian.setMumFullname(childGuardian.getMumSurname().toUpperCase() + " " + childGuardian.getMumOthername());
        childGuardian.setDadFullname(childGuardian.getDadSurname().toUpperCase() + " " + childGuardian.getDadOthername());

        informantBirth.setInformantBirthId(CommonUtil.generateID());
        birthDetail.setInformantBirth(informantBirth);

        birthDetail.setSystemUser(accessController.getSystemUser());

        birthDetail.setBirthId(birthId);
        birthDetail.setCenterRegistered(districtCenter.getCenterName());
        birthDetail.setFullName(birthDetail.getSurname().toUpperCase() + " " + birthDetail.getOthername());
        birthDetail.setDateOfBirth(new Date(doB));
        birthDetail.setDateOfRegistration(registrationDate);
        birthDetail.setTimeOfRegistration(registryTime);
        birthDetail.setDistrictApproved("NO");
        birthDetail.setRegionalApproved("NO");

        String guardianSaved = guardianFacade.childGuardianCreate(childGuardian);
        String informantSaved = informantBirthFacade.informantBirthCreate(informantBirth);
        String birthDetailSaved = birthDetailFacade.createBirthRegistration(birthDetail);

        if (guardianSaved != null || informantSaved != null || birthDetailSaved != null) {
            JSFUtility.infoMessage("Success: ", "Birth Registration Successfully Saved and sent for further approvals");
            birthDetailsCreate.fire(birthDetail);
            resetButton();
        } else {
            JSFUtility.warnMessage("Error: ", "Registration Failed.Couldn't Save Child Detail.Please Check Your Entries and Try Again");
        }
    }

    public void checkBeforeSave() {
        if (new Date(doB).after(calendar.getTime())) {
            JSFUtility.warnMessage("Error: ", "Date of Birth cannot be greater than today's date, Please check and update");
        } else {
            /*Go ahead and save details*/

            saveBirthRegistryDetails();
            JSFUtility.infoMessage("Success: ", "Birth Details successfully registered into system, pending approvals from Regionl Office");
        }
    }

    public void saveBirthRegistrationBtn() {
        if (birthInformantRelation.equals("null")) {
            JSFUtility.warnMessage("Error:", "Informant Relationship is Required for Save");
        } else {
            if (birthInformantRelation.equals("Self") || birthInformantRelation.equals("Mother") || birthInformantRelation.equals("Father")) {
                informantBirth.setRelationship(birthInformantRelation);
                switch (birthInformantRelation) {
                    case "Self":
                        informantBirth.setInformantName(accessController.getSystemUser().getFirstName());
                        break;
                    case "Mother":
                        String mumName = childGuardian.getMumSurname().toUpperCase() + " " + childGuardian.getMumOthername();
                        informantBirth.setInformantName(mumName);
                        break;
                    case "Father":
                        String dadName = childGuardian.getDadSurname().toUpperCase() + " " + childGuardian.getDadOthername();
                        informantBirth.setInformantName(dadName);
                        break;
                    default:
                        break;
                }
                checkBeforeSave();
            } else if (birthInformantRelation.equals("Other")) {
                if (i_name.equals("") || id_num.equals("") || id_type.equals("") || residence.equals("")) {
                    JSFUtility.warnMessage("Alert: ", "All Informant Fields are Required before Saving can Be Done For Relation Others");
                } else {
                    informantBirth.setIdNum(id_num);
                    informantBirth.setIdType(id_type);
                    informantBirth.setInformantName(i_name);
                    informantBirth.setRelationship(birthInformantRelation);
                    informantBirth.setResidence(residence);
                    checkBeforeSave();
                }
            }
        }
    }

    public void resetButton() {
        birthDetail = new ChildBirthDetail();
        childGuardian = new ChildGuardian();
        informantBirth = new InformantBirth();
        dateOfBirth = new Date();
        doB = "";
        birthInformantRelation = null;
        i_name = "";
        id_num = "";
        id_type = "";
        residence = "";
        renderBirthInformant = false;
    }

    public void setViewOnInformantRelation(ValueChangeEvent event) throws ParseException {
        try {
            renderBirthInformant = event.getNewValue().equals("Other");
        } catch (Exception e) {
            renderBirthInformant = false;
            e.printStackTrace();
        }
    }

    public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
        System.out.println(event);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter and Setter Methods">
    public String getBirthInformantRelation() {
        return birthInformantRelation;
    }

    public void setBirthInformantRelation(String birthInformantRelation) {
        this.birthInformantRelation = birthInformantRelation;
    }

    public GenIdFacade getGenIdFacade() {
        return genIdFacade;
    }

    public void setGenIdFacade(GenIdFacade genIdFacade) {
        this.genIdFacade = genIdFacade;
    }

    public boolean isRenderBirthInformant() {
        return renderBirthInformant;
    }

    public void setRenderBirthInformant(boolean renderBirthInformant) {
        this.renderBirthInformant = renderBirthInformant;
    }

    public ChildBirthDetailFacade getBirthDetailFacade() {
        return birthDetailFacade;
    }

    public void setBirthDetailFacade(ChildBirthDetailFacade birthDetailFacade) {
        this.birthDetailFacade = birthDetailFacade;
    }

    public ChildGuardianFacade getGuardianFacade() {
        return guardianFacade;
    }

    public void setGuardianFacade(ChildGuardianFacade guardianFacade) {
        this.guardianFacade = guardianFacade;
    }

    public InformantBirthFacade getInformantBirthFacade() {
        return informantBirthFacade;
    }

    public void setInformantBirthFacade(InformantBirthFacade informantBirthFacade) {
        this.informantBirthFacade = informantBirthFacade;
    }

    public ChildBirthDetail getBirthDetail() {
        return birthDetail;
    }

    public void setBirthDetail(ChildBirthDetail birthDetail) {
        this.birthDetail = birthDetail;
    }

    public InformantBirth getInformantBirth() {
        return informantBirth;
    }

    public void setInformantBirth(InformantBirth informantBirth) {
        this.informantBirth = informantBirth;
    }

    public ChildGuardian getChildGuardian() {
        return childGuardian;
    }

    public void setChildGuardian(ChildGuardian childGuardian) {
        this.childGuardian = childGuardian;
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

    public GenId getGenId() {
        return genId;
    }

    public void setGenId(GenId genId) {
        this.genId = genId;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getApproved_birth_id() {
        return approved_birth_id;
    }

    public void setApproved_birth_id(String approved_birth_id) {
        this.approved_birth_id = approved_birth_id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getRegistryTime() {
        return registryTime;
    }

    public void setRegistryTime(Date registryTime) {
        this.registryTime = registryTime;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public DistrictCenterFacade getCenterFacade() {
        return centerFacade;
    }

    public void setCenterFacade(DistrictCenterFacade centerFacade) {
        this.centerFacade = centerFacade;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
//</editor-fold>

}
