/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.utilities;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USER
 */
@ManagedBean(name = "birdsmnuconf")
@RequestScoped
public class BirdsMenuConfiguration implements Serializable {

    private Boolean render_reg_admin_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    private Boolean render_dist_admin_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    private Boolean registrar_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    private Boolean render_reg_admin_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    private Boolean render_dist_admin_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    private Boolean render_registrar_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;

    public BirdsMenuConfiguration() {
    }

    public void renderAllRegAdmin() {
        render_reg_admin_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_reg_admin_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
    }

    public void renderAllDistAdmin() {
        render_dist_admin_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_dist_admin_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
    }

    public void renderRegistrar() {
        registrar_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_registrar_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
    }

    public void enableAllMenu() {
        render_reg_admin_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_dist_admin_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        registrar_tabs = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_reg_admin_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_dist_admin_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
        render_registrar_content = BirdsMenuStatus.BIRDS_MNU_ENABLE;
    }

    public void disableAllMenu() {
        render_reg_admin_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
        render_dist_admin_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
        registrar_tabs = BirdsMenuStatus.BIRDS_MNU_DISABLE;
        render_reg_admin_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;
        render_dist_admin_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;
        render_registrar_content = BirdsMenuStatus.BIRDS_MNU_DISABLE;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public Boolean getRegistrar_tabs() {
        return registrar_tabs;
    }

    public void setRegistrar_tabs(Boolean registrar_tabs) {
        this.registrar_tabs = registrar_tabs;
    }

    public Boolean getRender_dist_admin_tabs() {
        return render_dist_admin_tabs;
    }

    public void setRender_dist_admin_tabs(Boolean render_dist_admin_tabs) {
        this.render_dist_admin_tabs = render_dist_admin_tabs;
    }

    public Boolean getRender_reg_admin_tabs() {
        return render_reg_admin_tabs;
    }

    public void setRender_reg_admin_tabs(Boolean render_reg_admin_tabs) {
        this.render_reg_admin_tabs = render_reg_admin_tabs;
    }

    public Boolean getRender_reg_admin_content() {
        return render_reg_admin_content;
    }

    public void setRender_reg_admin_content(Boolean render_reg_admin_content) {
        this.render_reg_admin_content = render_reg_admin_content;
    }

    public Boolean getRender_dist_admin_content() {
        return render_dist_admin_content;
    }

    public void setRender_dist_admin_content(Boolean render_dist_admin_content) {
        this.render_dist_admin_content = render_dist_admin_content;
    }

    public Boolean getRender_registrar_content() {
        return render_registrar_content;
    }

    public void setRender_registrar_content(Boolean render_registrar_content) {
        this.render_registrar_content = render_registrar_content;
    }
    //</editor-fold>
}
