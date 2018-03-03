/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import com.app.birds.entities.Region;
import com.app.birds.web.controllers.Create;
import com.app.birds.web.controllers.Delete;
import com.app.birds.web.controllers.Update;
import com.app.birds.web.utilities.JSFUtility;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringEscapeUtils;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "regionsObserver")
@ApplicationScoped
public class RegionsObserver implements Serializable {

    public RegionsObserver() {
    }

    public void onRegionCreate(@Observes @Create Region region) {
        String regionName = region.getRegionName();
        String CHANNEL = "/regionListEvents";
        String summary = "New Region";
        String details = regionName + " created";
        
        
//        Page on which save Form is affected by this message
        JSFUtility.infoMessage("Region Observer: ", "Successfully saved Region - " + region.getRegionName()
                + ". Update from Observer Class");

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

//        PushContext pushContext = PushContextFactory.getDefault().getPushContext();
//        pushContext.push("/regionListEvents", new FacesMessage(StringEscapeUtils.escapeHtml3("New Region"), StringEscapeUtils.escapeHtml3("Region Created")));
    }

    public void onRegionUpdate(@Observes @Update Region region) {
        String regionName = region.getRegionName();
        String CHANNEL = "/regionListEvents";
        String summary = "Update";
        String details = regionName + " updated";

        System.out.println("Region: " + region.getRegionName() + " with Id: " + region.getRegionId()
                + " has been update successfully");

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));

    }

    public void onRegionDelete(@Observes @Delete Region region) {
        String regionName = region.getRegionName();
        String CHANNEL = "/regionListEvents";
        String summary = "New Region";
        String details = regionName + " deleted - logically";

        System.out.println("Region: " + region.getRegionName() + " with Id: " + region.getRegionId()
                + " has been deleted successfully");

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml3(summary),
                StringEscapeUtils.escapeHtml3(details)));
    }
}
