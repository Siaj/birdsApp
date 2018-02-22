/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "registrar_pages")
@SessionScoped
public class RegistrarPagesController implements Serializable {

    private boolean renderWelcome = true;
    private boolean renderEditAcc = false;
    private boolean renderBirthRegistration = false;
    private boolean renderDeathRegistration = false;
    private boolean renderBirthCertRequest = false;
    private boolean renderDeathCertRequest = false;
    private boolean renderBirthSearch = false;
    private boolean renderDeathSearch = false;

    public RegistrarPagesController() {
    }

    public void showDashboard() {
        renderWelcome = true;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void changePassword() {
        renderWelcome = false;
        renderEditAcc = true;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void showBirthRegForm() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = true;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void showDeathRegForm() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = true;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void showBirthCertRequestForm() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = true;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void showDeathCertRequestForm() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = true;
        renderBirthSearch = false;
        renderDeathSearch = false;
    }

    public void birthSearch() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = true;
        renderDeathSearch = false;
    }

    public void deathSearch() {
        renderWelcome = false;
        renderEditAcc = false;
        renderBirthRegistration = false;
        renderDeathRegistration = false;
        renderBirthCertRequest = false;
        renderDeathCertRequest = false;
        renderBirthSearch = false;
        renderDeathSearch = true;
    }

    public boolean isRenderWelcome() {
        return renderWelcome;
    }

    public void setRenderWelcome(boolean renderWelcome) {
        this.renderWelcome = renderWelcome;
    }

    public boolean isRenderEditAcc() {
        return renderEditAcc;
    }

    public void setRenderEditAcc(boolean renderEditAcc) {
        this.renderEditAcc = renderEditAcc;
    }

    public boolean isRenderBirthRegistration() {
        return renderBirthRegistration;
    }

    public void setRenderBirthRegistration(boolean renderBirthRegistration) {
        this.renderBirthRegistration = renderBirthRegistration;
    }

    public boolean isRenderDeathRegistration() {
        return renderDeathRegistration;
    }

    public void setRenderDeathRegistration(boolean renderDeathRegistration) {
        this.renderDeathRegistration = renderDeathRegistration;
    }

    public boolean isRenderBirthCertRequest() {
        return renderBirthCertRequest;
    }

    public void setRenderBirthCertRequest(boolean renderBirthCertRequest) {
        this.renderBirthCertRequest = renderBirthCertRequest;
    }

    public boolean isRenderDeathCertRequest() {
        return renderDeathCertRequest;
    }

    public void setRenderDeathCertRequest(boolean renderDeathCertRequest) {
        this.renderDeathCertRequest = renderDeathCertRequest;
    }

    public boolean isRenderBirthSearch() {
        return renderBirthSearch;
    }

    public void setRenderBirthSearch(boolean renderBirthSearch) {
        this.renderBirthSearch = renderBirthSearch;
    }

    public boolean isRenderDeathSearch() {
        return renderDeathSearch;
    }

    public void setRenderDeathSearch(boolean renderDeathSearch) {
        this.renderDeathSearch = renderDeathSearch;
    }

}
