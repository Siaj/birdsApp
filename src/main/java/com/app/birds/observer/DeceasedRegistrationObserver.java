/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import com.app.birds.entities.ChildBirthDetail;
import com.app.birds.entities.DeceasedDetail;
import com.app.birds.web.controllers.qualifiers.Create;
import com.app.birds.web.controllers.qualifiers.DistAdmin;
import com.app.birds.web.controllers.qualifiers.RegAdmin;
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
@Named(value = "deceasedRegistrationObserver")
@ApplicationScoped
public class DeceasedRegistrationObserver {

    String GENERAL_DEATH_SEARCH_CHANNEL = "/deceasedDetails";
    String DISTRICT_ADMIN_CHANNEL = "/districtAdmin";
    String REGIONAL_ADMIN_CHANNEL = "/regionalAdmin";

    public DeceasedRegistrationObserver() {
    }

    public void onDeceasedDetailsCreate(@Observes @Create DeceasedDetail deceasedDetail) {
        String summary = "New Deceased Details";
        String details = "A new deceased details has been registered";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_DEATH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

        eventBus.publish(DISTRICT_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }

    public void onDistrictAdminBirthRegApproval(@Observes @Update @DistAdmin DeceasedDetail deceasedDetail) {
        String summary = "District Deceased Approval";
        String details = "Deceased Details Approved in " + deceasedDetail.getSystemUser().getDistrict().getDistrictName()
                + " for " + deceasedDetail.getOthername();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_DEATH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }

    public void onRegionalAdminBirthRegApproval(@Observes @Update @RegAdmin DeceasedDetail deceasedDetail) {
        String summary = "Regional Deceased Approval";
        String details = "Deceased Details Approved for " + deceasedDetail.getOthername();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(GENERAL_DEATH_SEARCH_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
