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
@Named(value = "birth_registration_model")
@SessionScoped
public class BirthRegistrationController implements Serializable {

    private String birthInformantRelation;
    private boolean renderBirthInformant = false;

    public BirthRegistrationController() {
    }

    public void resetButton() {
        birthInformantRelation = null;
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

    public String getBirthInformantRelation() {
        return birthInformantRelation;
    }

    public void setBirthInformantRelation(String birthInformantRelation) {
        this.birthInformantRelation = birthInformantRelation;
    }

    public boolean isRenderBirthInformant() {
        return renderBirthInformant;
    }

    public void setRenderBirthInformant(boolean renderBirthInformant) {
        this.renderBirthInformant = renderBirthInformant;
    }

}
