/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import com.app.birds.entities.BirthCertRequest;
import com.app.birds.web.controllers.qualifiers.Create;
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
@Named(value = "birthCertObserver")
@ApplicationScoped
public class BirthCertObserver {

    String DISTRICT_ADMIN_CHANNEL = "/districtAdmin";
    String REGIONAL_ADMIN_CHANNEL = "/regionalAdmin";

    public BirthCertObserver() {
    }

    public void onBirthCertRequestCreate(@Observes @Create BirthCertRequest birthCertRequest) {
        String summary = "New Request";
        String details = "A new birth cert request has been registered";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(DISTRICT_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }

    public void onBirthCertApprovePrint(@Observes @Update @RegAdmin BirthCertRequest birthCertRequest) {
        String summary = "Approved";
        String details = "Birth Cert Approved and Printed";

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(REGIONAL_ADMIN_CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
