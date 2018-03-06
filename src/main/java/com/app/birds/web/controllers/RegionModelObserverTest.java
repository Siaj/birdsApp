/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.ejbSessions.RegionFacade;
import com.app.birds.web.controllers.qualifiers.Create;
import com.app.birds.web.controllers.qualifiers.Update;
import com.app.birds.web.controllers.qualifiers.Delete;
import com.app.birds.entities.Region;
import com.app.birds.web.commons.BirdsSingletonDataSource;
import com.app.birds.web.utilities.JSFUtility;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "region_observer_test")
@SessionScoped
public class RegionModelObserverTest implements Serializable {

    @Inject
    @Create
    Event<Region> regionSaveEvent;

    @Inject
    @Update
    Event<Region> regionUpdateEvent;

    @Inject
    @Delete
    Event<Region> regionDeleteEvent;

    @Inject
    RegionFacade regionFacade;

    private Region region = new Region();
    private List<Region> listRegion = new ArrayList<>();
    private transient DataModel<Region> regDataModel;
    private boolean renderEdit = false;
    private String reg_Id, reg_Name;
    private String deleteTxt = "Delete", cssfordelete = "";
    private String restoreTxt = "Restore", cssRestore = "";

    public RegionModelObserverTest() {
    }

    public void fetchRow() {
        region = (Region) regDataModel.getRowData();

        if (region != null) {
            reg_Id = region.getRegionId();
            reg_Name = region.getRegionName();
            renderEdit = true;
        }
    }

    public void closeSearch() {
        renderEdit = false;
    }

    public void saveNewRegion() {

        region.setRegionId(region.getRegionId());
        region.setRegionName(region.getRegionName());

        String singletonSave = regionFacade.regionCreate(region);

//        String savedNewRegion = BirdsSingletonDataSource.getCommonSessionBean().createNewRegion(region);
        if (singletonSave != null) {
            System.out.println("Saved Region Id: " + singletonSave);
            region = regionFacade.regionFind(singletonSave);
            regionSaveEvent.fire(region);

            region = new Region();
        } else {
            JSFUtility.errorMessage("Error: ", "Something went wrong during creating of region");
            System.out.println("Not Saved");
        }

    }

    public void saveEdit() {
        if (region.getRegionId() == null || region.getRegionName() == null) {
            JSFUtility.warnMessage("Required Fields: ", "None of the form fields can be empty!!!");
        } else {
//            region = BirdsSingletonDataSource.getCommonSessionBean().regionFind(reg_Id);
//            region.setRegionId(reg_Id);
//            region.setRegionName(reg_Name);

            Boolean saved = regionFacade.regionUpdate(region);
            System.out.println(saved);
            if (saved) {
                regionUpdateEvent.fire(region);
                renderEdit = false;

                region = new Region();
                System.out.println("Successfully Updated");
            } else {
                JSFUtility.errorMessage("Error: ", "Something went wrong during update");
                System.out.println("Not Saved");
            }
        }

    }

    public void restoreDeletedRegion() {
        region = regDataModel.getRowData();
        region.setDeleted("NO");
        Boolean restore = regionFacade.regionUpdate(region);
        if (restore) {
            regionUpdateEvent.fire(region);
            region = new Region();
            JSFUtility.infoMessage("Success: ", "Region Successfully Restored from Logical Delete");
        } else {
            JSFUtility.warnMessage("Error: ", "An unknown error occurred while restoring region");
        }
    }

    public void deleteRegion() {
        region = regDataModel.getRowData();
        Boolean regionDeleted = regionFacade.regionDelete(region, false);
        if (regionDeleted) {
            regionDeleteEvent.fire(region);
            region = new Region();
        } else {
            JSFUtility.warnMessage("Error: ", "An unknown error occurred during process!!!");
            System.out.println("Not Deleted");
        }
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Region> getListRegion() {
        listRegion = regionFacade.regionGetAll(true);
        return listRegion;
    }

    public void setListRegion(List<Region> listRegion) {
        this.listRegion = listRegion;
    }

    public DataModel<Region> getRegDataModel() {
        regDataModel = new ListDataModel<>(getListRegion());
        return regDataModel;
    }

    public void setRegDataModel(DataModel<Region> regDataModel) {
        this.regDataModel = regDataModel;
    }

    public boolean isRenderEdit() {
        return renderEdit;
    }

    public void setRenderEdit(boolean renderEdit) {
        this.renderEdit = renderEdit;
    }

    public String getReg_Id() {
        return reg_Id;
    }

    public void setReg_Id(String reg_Id) {
        this.reg_Id = reg_Id;
    }

    public String getReg_Name() {
        return reg_Name;
    }

    public void setReg_Name(String reg_Name) {
        this.reg_Name = reg_Name;
    }

    public String getDeleteTxt() {
        return deleteTxt;
    }

    public void setDeleteTxt(String deleteTxt) {
        this.deleteTxt = deleteTxt;
    }

    public String getCssfordelete() {
        return cssfordelete;
    }

    public void setCssfordelete(String cssfordelete) {
        this.cssfordelete = cssfordelete;
    }

    public String getRestoreTxt() {
        return restoreTxt;
    }

    public void setRestoreTxt(String restoreTxt) {
        this.restoreTxt = restoreTxt;
    }

    public String getCssRestore() {
        return cssRestore;
    }

    public void setCssRestore(String cssRestore) {
        this.cssRestore = cssRestore;
    }

}
