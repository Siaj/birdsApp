/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.web.controllers.qualifiers.Create;
import com.app.birds.web.controllers.qualifiers.DistAdmin;
import com.app.birds.web.controllers.qualifiers.RegAdmin;
import com.app.birds.web.controllers.qualifiers.Registrar;
import com.app.birds.web.controllers.qualifiers.Update;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "birthRegistrationObserver")
@ApplicationScoped
public class BirthRegistrationObserver {

    String GENERAL_BIRTH_SEARCH_CHANNEL = "/birthDetails";
    String DISTRICT_ADMIN_CHANNEL = "/districtAdmin";
    String REGIONAL_ADMIN_CHANNEL = "/regionalAdmin";

    public BirthRegistrationObserver() {
    }

    public void onBirthDetailsCreate(@Observes @Create ChildBirthDetail birthDetail) {
        String summary = "New Birth Details";
        String details = "A new birth has been registered";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_BIRTH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

        eventBus.publish(DISTRICT_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }

    public void onRegistrarBirthDetailsUpdate(@Observes @Update @Registrar ChildBirthDetail birthDetail) {
        String summary = "Updates";
        String details = "Birth details has been updated by Registrar at "
                + birthDetail.getSystemUser().getDistrictCenter().getCenterName();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_BIRTH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

        eventBus.publish(DISTRICT_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }

    public void onDistrictAdminBirthRegApproval(@Observes @Update @DistAdmin ChildBirthDetail birthDetail) {
        String summary = "District Birth Approval";
        String details = "Birth Details Approved in " + birthDetail.getSystemUser().getDistrict().getDistrictName()
                + " for " + birthDetail.getOthername();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_BIRTH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

    }

    public void onRegionalAdminBirthRegApproval(@Observes @Update @RegAdmin ChildBirthDetail birthDetail) {
        String summary = "Regional Birth Approval";
        String details = "Birth Details Approved for " + birthDetail.getOthername();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_BIRTH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
