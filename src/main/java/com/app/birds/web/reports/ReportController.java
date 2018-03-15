/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.reports;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "reportController")
@SessionScoped
public class ReportController implements Serializable {

    /**
     * Creates a new instance of ReportController
     */
    public ReportController() {
    }
    
}
