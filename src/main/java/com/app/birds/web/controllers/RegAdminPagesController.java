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
@Named(value = "reg_admin_pages")
@SessionScoped
public class RegAdminPagesController implements Serializable {

    private boolean renderWelcome = true;
    private boolean renderRegistreUser = false;
    private boolean renderManageUser = false;
    private boolean renderRegDistOffice = false;
    private boolean renderCenterReg = false;
    private boolean renderBirthApproval = false;
    private boolean renderDeathApproval = false;
    private boolean renderBirthCertPrint = false;
    private boolean renderDeathCertPrint = false;
    private boolean renderBirthStats = false;
    private boolean renderDeathStat = false;
    private boolean renderBirthSearch = false;
    private boolean renderDeathSearch = false;
    private boolean renderChangePassword = false;

    public RegAdminPagesController() {
    }

    public void changePassword() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = true;
    }

    public void newUserCreate() {
        renderWelcome = false;
        renderRegistreUser = true;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void manageUsers() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = true;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void registerDistrictOffice() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = true;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void registerCenterOffice() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = true;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void approveBirths() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = true;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void approveDeaths() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = true;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void birthCertApprovePrint() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = true;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void deathCertApprovePrint() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = true;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void birthStatsReport() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = true;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void deathStatsReport() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = true;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void birthGeneralSearch() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = true;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public void deathGeneralSearch() {
        renderWelcome = false;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = true;
        renderChangePassword = false;
    }

    public void welcomePage() {
        renderWelcome = true;
        renderRegistreUser = false;
        renderManageUser = false;
        renderRegDistOffice = false;
        renderCenterReg = false;
        renderBirthApproval = false;
        renderDeathApproval = false;
        renderBirthCertPrint = false;
        renderDeathCertPrint = false;
        renderBirthStats = false;
        renderDeathStat = false;
        renderBirthSearch = false;
        renderDeathSearch = false;
        renderChangePassword = false;
    }

    public boolean isRenderWelcome() {
        return renderWelcome;
    }

    public void setRenderWelcome(boolean renderWelcome) {
        this.renderWelcome = renderWelcome;
    }

    public boolean isRenderRegistreUser() {
        return renderRegistreUser;
    }

    public void setRenderRegistreUser(boolean renderRegistreUser) {
        this.renderRegistreUser = renderRegistreUser;
    }

    public boolean isRenderManageUser() {
        return renderManageUser;
    }

    public void setRenderManageUser(boolean renderManageUser) {
        this.renderManageUser = renderManageUser;
    }

    public boolean isRenderRegDistOffice() {
        return renderRegDistOffice;
    }

    public void setRenderRegDistOffice(boolean renderRegDistOffice) {
        this.renderRegDistOffice = renderRegDistOffice;
    }

    public boolean isRenderCenterReg() {
        return renderCenterReg;
    }

    public void setRenderCenterReg(boolean renderCenterReg) {
        this.renderCenterReg = renderCenterReg;
    }

    public boolean isRenderBirthApproval() {
        return renderBirthApproval;
    }

    public void setRenderBirthApproval(boolean renderBirthApproval) {
        this.renderBirthApproval = renderBirthApproval;
    }

    public boolean isRenderDeathApproval() {
        return renderDeathApproval;
    }

    public void setRenderDeathApproval(boolean renderDeathApproval) {
        this.renderDeathApproval = renderDeathApproval;
    }

    public boolean isRenderBirthCertPrint() {
        return renderBirthCertPrint;
    }

    public void setRenderBirthCertPrint(boolean renderBirthCertPrint) {
        this.renderBirthCertPrint = renderBirthCertPrint;
    }

    public boolean isRenderDeathCertPrint() {
        return renderDeathCertPrint;
    }

    public void setRenderDeathCertPrint(boolean renderDeathCertPrint) {
        this.renderDeathCertPrint = renderDeathCertPrint;
    }

    public boolean isRenderBirthStats() {
        return renderBirthStats;
    }

    public void setRenderBirthStats(boolean renderBirthStats) {
        this.renderBirthStats = renderBirthStats;
    }

    public boolean isRenderDeathStat() {
        return renderDeathStat;
    }

    public void setRenderDeathStat(boolean renderDeathStat) {
        this.renderDeathStat = renderDeathStat;
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
