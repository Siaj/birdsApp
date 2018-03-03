/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import java.util.Observable;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class RegionDetails extends Observable {

    private String regionId;
    private String name;
    private String deleted, updated;

    public RegionDetails(String regionId, String name) {
        this.regionId = regionId;
        this.name = name;
        System.out.println("Concrete Subject Created: " + name + " at " + regionId);
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
        setChanged();
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
        notifyObservers();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
        setChanged();
        notifyObservers();
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
        setChanged();
        notifyObservers();
    }

}
