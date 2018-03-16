/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.jasperReport;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class JasperReportManager implements Serializable {

    private Map<String, Object> defaultParamenters = new HashMap();
    private Map<String, Object> reportParamenters = new HashMap();
    private Map<String, JasperReportManager> reportInstances = new HashMap();
    private ReportOutputFileType reportOutputFileType;
    private ReportDesignFileType reportFileType;
    private ReportOutputEnvironment reportOutputEnvironment;
    private JasperPrint jasperPrint;
    private JRBeanCollectionDataSource jrCollectionDataSource = null;
    private String jasperFile;
    private String reportOutputDirectory;
    private Collection reportData;
    private static final Logger LOGGER = Logger.getLogger(JasperReportManager.class.getName());
    String msg = "";

    public void showReport(Collection reportData, InputStream inputStream) {
        this.reportData = reportData;
  //      System.out.println("Report Data Passed Success: " + reportData.size());
//        System.out.println(inputStream);
        createJasperPrint(inputStream);
        outputReport();
    }

    public void printReportDirectToPrinter(Collection reportData, InputStream inputStream) {
        this.reportData = reportData;
        createJasperPrint(inputStream, Boolean.valueOf(true));
    }

    public void showReport(Collection reportData, String jasperFile) {
        this.reportData = reportData;
        this.jasperFile = jasperFile;

        createJasperPrint(null);

        outputReport();
    }

    public void showReport(Object reportData, String jasperFile) {
        List dataList = new ArrayList(1);
        dataList.add(reportData);
        showReport(dataList, jasperFile);
    }

    public void showReport(Object reportData, InputStream reportInputStrem) {
        List dataList = new ArrayList(1);
        dataList.add(reportData);
        showReport(dataList, reportInputStrem);
    }

    private void webenviro() {
        HttpServletResponse response = getServeltResponse();
        HttpServletRequest request = getServeltRequest();
        try {
            switch (this.reportOutputFileType) {
                case PDF:
                    JRPdfExporter pdfExporter = new JRPdfExporter();

                    pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, this.jasperPrint);
                    pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

                    pdfExporter.exportReport();

                    break;
                case XHTML:
                    JRHtmlExporter htmlExporter = new JRHtmlExporter();
                    request.getSession().setAttribute("net.sf.jasperreports.j2ee.jasper_print", this.jasperPrint);
                    htmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "image?image=");

                    htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, this.jasperPrint);
                    htmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

                    htmlExporter.exportReport();
            }
        } catch (Exception e) {
            Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, e.toString());
        }
        try {
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void desktopEnviroment() {
        JasperViewer jasperViewer = new JasperViewer(this.jasperPrint, false);
        jasperViewer.setTitle("");
        jasperViewer.setVisible(true);
    }

    public void writeToFile(Collection reportData, InputStream inputStream, String reportDirectory, String fileName) {
        if ((fileName == null) || (reportDirectory == null)) {
            this.msg = ("Please report directory and file name can not be NULL \nREPORT_DIRECTORY = " + reportDirectory + "\n" + "FILE_NAME = " + fileName);

            Logger.getLogger(JasperReportManager.class.getName()).log(Level.INFO, this.msg);

            return;
        }
        if (fileName.contains("/")) {
            String oldFilename = fileName;
            fileName = fileName.replaceAll("/", "-");

            this.msg = ("Report Output File Name (" + oldFilename + ") contains escape or invalid charaters. " + "Will be replaced with (-) to " + fileName);

            Logger.getLogger(JasperReportManager.class.getName()).log(Level.INFO, this.msg);
        }
        try {
            new File(reportDirectory).mkdirs();
        } catch (Exception e) {
            this.msg = ("Unable to create or find Report Output directory (" + reportDirectory + ")");
            Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, this.msg + "\n" + e.getMessage(), e);

            return;
        }
        File file = new File(reportDirectory, fileName);

        createJasperPrint(inputStream);

        System.out.println("Print .....  " + this.jasperPrint);

        this.msg = ("Report will be written to " + file.getAbsolutePath());
        Logger.getLogger(JasperReportManager.class.getName()).log(Level.INFO, this.msg);

        System.out.println("report output file type = " + this.reportOutputFileType);
        switch (this.reportOutputFileType) {
            case PDF:
                try {
                    System.out.println("about to produce pdf file " + file.getAbsolutePath());
                    JasperExportManager.exportReportToPdfFile(this.jasperPrint, file.getAbsolutePath());

                    LOGGER.fine("Report exported to pdf completed - " + file.getAbsolutePath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
        }
    }

    public void addParam(String paramKey, Object paramValue) {
        this.reportParamenters.put(paramKey, paramValue);
    }

    public static HttpServletResponse getServeltResponse() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        return response;
    }

    public static HttpServletRequest getServeltRequest() {
        HttpServletRequest response = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return response;
    }

    private static void forceDownload() {
        HttpServletResponse hsr = getServeltResponse();
        hsr.setHeader("Content-type", "application/force-download");
        hsr.setHeader("Content-Transfer-Encoding", "Binary");
        hsr.setHeader("Content-length", null);
    }

    private void outputReport() {
        if (this.jasperPrint == null) {
            this.msg = "Could not create Jasper Print so Report Process will be abborted";
            Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, this.msg);

            return;
        }
        switch (this.reportOutputEnvironment) {
            case WEB_APPLICATION:
                webenviro();
                break;
            case DESKTOP_APPLICATION:
                desktopEnviroment();
        }
    }

    private void createJasperPrint(InputStream rptIns) {
        try {
            this.jrCollectionDataSource = new JRBeanCollectionDataSource(this.reportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.reportFileType == ReportDesignFileType.INPUTSTREAM) {
            InputStream inputStream = null;
            System.out.println("Jasper: " + rptIns);
            if (rptIns != null) {
                inputStream = rptIns;
            } else {
                try {
                    System.out.println("about to load input stream with " + this.jasperFile);
                    if (!this.jasperFile.endsWith(".jasper")) {
                        this.jasperFile += ".jasper";
                    }
                    inputStream = JasperReportManager.class.getResourceAsStream(this.jasperFile);
                    System.out.println(inputStream + " result of searchin.... " + this.jasperFile);
                } catch (Exception e) {
                    this.msg = ("Unable to load Input Stream for " + this.jasperFile);
                    Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, this.msg + " \n " + e.toString());

                    this.jasperPrint = null;

                    return;
                }
            }
            try {
                this.msg = ("Creating JasperPrint with inputstream: " + inputStream + " and bean collection datasource " + this.jrCollectionDataSource);

                Logger.getLogger(JasperReportManager.class.getName()).log(Level.INFO, this.msg);

                this.jasperPrint = JasperFillManager.fillReport(inputStream, this.reportParamenters, this.jrCollectionDataSource);
                JasperPrintManager.printReport(this.jasperPrint, false);
            } catch (Exception e) {
                Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, "Error Creating JasperPrint for " + this.jasperFile + "\n" + e.toString(), e);
            }
        } else if (this.reportFileType != ReportDesignFileType.STRING_FILE) {
        }
    }

    private void createJasperPrint(InputStream rptIns, Boolean directPrinting) {
        try {
            this.jrCollectionDataSource = new JRBeanCollectionDataSource(this.reportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.reportFileType == ReportDesignFileType.INPUTSTREAM) {
            InputStream inputStream = null;
            if (rptIns != null) {
                inputStream = rptIns;
            } else {
                try {
                    System.out.println("about to load input stream with " + this.jasperFile);
                    if (!this.jasperFile.endsWith(".jasper")) {
                        this.jasperFile += ".jasper";
                    }
                    inputStream = JasperReportManager.class.getResourceAsStream(this.jasperFile);
                    System.out.println(inputStream + " result of searchin.... " + this.jasperFile);
                } catch (Exception e) {
                    this.msg = ("Unable to load Input Stream for " + this.jasperFile);
                    Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, this.msg + " \n " + e.toString());

                    this.jasperPrint = null;

                    return;
                }
            }
            try {
                this.msg = ("Creating JasperPrint with inputstream: " + inputStream + " and bean collection datasource " + this.jrCollectionDataSource);

                Logger.getLogger(JasperReportManager.class.getName()).log(Level.INFO, this.msg);

                this.jasperPrint = JasperFillManager.fillReport(inputStream, this.reportParamenters, this.jrCollectionDataSource);
                if (directPrinting.booleanValue() == true) {
                    JasperPrintManager.printReport(this.jasperPrint, false);
                }
            } catch (Exception e) {
                Logger.getLogger(JasperReportManager.class.getName()).log(Level.SEVERE, "Error Creating JasperPrint for " + this.jasperFile + "\n" + e.toString(), e);
            }
        } else if (this.reportFileType != ReportDesignFileType.STRING_FILE) {
        }
    }

    public void addReportInstance(String name, JasperReportManager reportManager) {
        this.reportInstances.put(name, reportManager);
    }

    public void addToDefaultParameters(String paramKey, Object paramValue) {
        this.defaultParamenters.put(paramKey, paramValue);
        this.reportParamenters.put(paramKey, paramValue);
    }

    public void resetReportParametersToDefault() {
        this.reportParamenters.clear();
        this.reportParamenters.putAll(this.defaultParamenters);
    }

    public ReportOutputEnvironment getReportEnvironment() {
        return this.reportOutputEnvironment;
    }

    public void setReportEnvironment(ReportOutputEnvironment reportEnvironment) {
        this.reportOutputEnvironment = reportEnvironment;
    }

    public ReportDesignFileType getReportFileType() {
        return this.reportFileType;
    }

    public void setReportFileType(ReportDesignFileType reportFileType) {
        this.reportFileType = reportFileType;
    }

    public ReportOutputFileType getReportOutput() {
        return this.reportOutputFileType;
    }

    public void setReportOutput(ReportOutputFileType reportOutput) {
        this.reportOutputFileType = reportOutput;
    }

    private void create(String files) {
        File pa = new File(files);
        try {
            pa.mkdirs();
        } catch (Exception e) {
        }
    }
}
