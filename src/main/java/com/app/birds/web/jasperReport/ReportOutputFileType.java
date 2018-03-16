/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.jasperReport;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public enum ReportOutputFileType {
    PDF("application/pdf"), XHTML("text/html"), EXCELL("text/excel");

    private String contentType;

    private ReportOutputFileType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return this.contentType;
    }
}
