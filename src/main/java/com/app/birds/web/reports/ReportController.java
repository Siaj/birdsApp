/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.reports;

import com.app.birds.web.jasperReport.JasperReportManager;
import com.app.birds.web.jasperReport.ReportDesignFileType;
import com.app.birds.web.jasperReport.ReportOutputEnvironment;
import com.app.birds.web.jasperReport.ReportOutputFileType;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "reportController")
@SessionScoped
public class ReportController extends JasperReportManager implements Serializable {

    public static Map<String, Object> rptParam = new HashMap<String, Object>();
    String msg = "";

    private static final String REPORT_BASE_DIR = "\\com\\app\\birds\\web\\reports\\";
    private static final ReportController reportManager = new ReportController();
    public static final String BIRTH_CERTIFICATE = "birth_certificate.jasper";
    public static final String DEATH_CERTIFICATE = REPORT_BASE_DIR + "death_certificate.jasper";

    static {
        reportManager.setReportOutput(ReportOutputFileType.PDF);
        reportManager.setReportEnvironment(ReportOutputEnvironment.WEB_APPLICATION);
        reportManager.setReportFileType(ReportDesignFileType.INPUTSTREAM);
    }

    public ReportController() {
    }

    public static ReportController getInstance() {
        return reportManager;
    }

    public void loadDefaultParameters() {
        reportManager.resetReportParametersToDefault();
        reportManager.addToDefaultParameters("title", "CERTIFIED COPY OF ENTRY IN THE REGISTER OF BIRTHS");
        reportManager.addToDefaultParameters("deathTitle", "CERTIFIED COPY OF ENTRY IN THE REGISTER OF DEATHS");
//        reportManager.addToDefaultParameters("registry", "Kumasi");
    }

}
