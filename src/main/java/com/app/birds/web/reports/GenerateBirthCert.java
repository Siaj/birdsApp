/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.reports;

import com.app.birds.ejbSessions.BirthCertRequestFacade;
import com.app.birds.entities.BirthCertRequest;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.detailedClass.BirthDetailedClass;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class GenerateBirthCert extends CertificateTemplate<BirthCertRequest, SystemUser, Collection, BirthCertRequestFacade> {

    @Override
    public List loadPrintDetails(BirthCertRequest cl, SystemUser su) {
        List<BirthDetailedClass> bdcs;
        BirthDetailedClass birthDetailedClass = new BirthDetailedClass();
        bdcs = birthDetailedClass.loadBirth(cl, su);
        return bdcs;
    }

    @Override
    public void updateDatabase(BirthCertRequest bcr, BirthCertRequestFacade bcrf) {
        bcr.setRegionalApproved("YES");
        bcr.setCertPrinted("YES");
        bcrf.birthCertRequestUpdate(bcr);
    }

    @Override
    public void renderCertificate(Collection c) {
        ReportController.getInstance().loadDefaultParameters();
        ReportController.getInstance().showReport(c, getClass().getResourceAsStream(ReportController.BIRTH_CERTIFICATE));
    }

}
