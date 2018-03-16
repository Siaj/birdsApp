/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import com.app.birds.entities.DeathCertRequest;
import com.app.birds.web.controllers.qualifiers.Create;
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
@Named(value = "deathCertObserver")
@ApplicationScoped
public class DeathCertObserver {

    String GENERAL_BIRTH_SEARCH_CHANNEL = "/birthDetails";
    String DISTRICT_ADMIN_CHANNEL = "/districtAdmin";
    String REGIONAL_ADMIN_CHANNEL = "/regionalAdmin";

    public DeathCertObserver() {
    }

    public void onDeathCertRequestCreate(@Observes @Create DeathCertRequest deathCertRequest) {
        String summary = "New Request";
        String details = "A new death cert request has been registered";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(DISTRICT_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
