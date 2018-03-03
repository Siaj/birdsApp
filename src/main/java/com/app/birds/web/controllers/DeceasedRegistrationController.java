/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "deceased_registration_model")
@SessionScoped
public class DeceasedRegistrationController implements Serializable {

    private String deceasedBurialStatus;
    private boolean renderBurriedStatus = false;

    public DeceasedRegistrationController() {
    }

    public void resetButton() {
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

    public boolean isRenderBurriedStatus() {
        return renderBurriedStatus;
    }

    public void setRenderBurriedStatus(boolean renderBurriedStatus) {
        this.renderBurriedStatus = renderBurriedStatus;
    }

}
