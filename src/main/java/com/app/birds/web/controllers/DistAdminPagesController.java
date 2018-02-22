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
@Named(value = "district_admin_pages")
@SessionScoped
public class DistAdminPagesController implements Serializable {

    private boolean renderDistWelcome = true;
    private boolean renderDistManageUsers = false;
    private boolean renderAssignOfficer = false;
    private boolean renderDistBirthApproval = false;
    private boolean renderDistDeathApproval = false;
    private boolean renderDistBirthCertApproval = false;
    private boolean renderDistDeathCertApproval = false;
    private boolean renderBirthSearch = false;
    private boolean renderDeathSearch = false;
    private boolean renderChangePassword = false;

    public DistAdminPagesController() {
    }

    public void changePassword() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = true;
    }

    public void manageDistrictUser() {
        renderDistWelcome = false;
        renderDistManageUsers = true;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void assignOfficer() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = true;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void distBirthApproval() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = true;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void distDeathApproval() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = true;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void distBirthCertApproval() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = true;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void distDeathCertApproval() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = true;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void birthSearch() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = true;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void deathSearch() {
        renderDistWelcome = false;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = true;
        renderChangePassword = false;
    }

    public void welcomePage() {
        renderDistWelcome = true;
        renderDistManageUsers = false;
        renderAssignOfficer = false;
        renderDistBirthApproval = false;
        renderDistDeathApproval = false;
        renderDistBirthCertApproval = false;
        renderDistDeathCertApproval = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public boolean isRenderDistWelcome() {
        return renderDistWelcome;
    }

    public void setRenderDistWelcome(boolean renderDistWelcome) {
        this.renderDistWelcome = renderDistWelcome;
    }

    public boolean isRenderDistManageUsers() {
        return renderDistManageUsers;
    }

    public void setRenderDistManageUsers(boolean renderDistManageUsers) {
        this.renderDistManageUsers = renderDistManageUsers;
    }

    public boolean isRenderAssignOfficer() {
        return renderAssignOfficer;
    }

    public void setRenderAssignOfficer(boolean renderAssignOfficer) {
        this.renderAssignOfficer = renderAssignOfficer;
    }

    public boolean isRenderDistBirthApproval() {
        return renderDistBirthApproval;
    }

    public void setRenderDistBirthApproval(boolean renderDistBirthApproval) {
        this.renderDistBirthApproval = renderDistBirthApproval;
    }

    public boolean isRenderDistDeathApproval() {
        return renderDistDeathApproval;
    }

    public void setRenderDistDeathApproval(boolean renderDistDeathApproval) {
        this.renderDistDeathApproval = renderDistDeathApproval;
    }

    public boolean isRenderDistBirthCertApproval() {
        return renderDistBirthCertApproval;
    }

    public void setRenderDistBirthCertApproval(boolean renderDistBirthCertApproval) {
        this.renderDistBirthCertApproval = renderDistBirthCertApproval;
    }

    public boolean isRenderDistDeathCertApproval() {
        return renderDistDeathCertApproval;
    }

    public void setRenderDistDeathCertApproval(boolean renderDistDeathCertApproval) {
        this.renderDistDeathCertApproval = renderDistDeathCertApproval;
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

    public boolean isRenderChangePassword() {
        return renderChangePassword;
    }

    public void setRenderChangePassword(boolean renderChangePassword) {
        this.renderChangePassword = renderChangePassword;
    }

}
