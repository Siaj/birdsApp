/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.reports;

import com.app.birds.ejbSessions.DeathCertRequestFacade;
import com.app.birds.entities.DeathCertRequest;
import com.app.birds.entities.SystemUser;
import com.app.birds.web.detailedClass.DeceasedDetailClass;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class GenerateDeathCertificate extends CertificateTemplate<DeathCertRequest, SystemUser, Collection, DeathCertRequestFacade> {

    @Override
    public List loadPrintDetails(DeathCertRequest cl, SystemUser su) {
        List<DeceasedDetailClass> ddcs;
        DeceasedDetailClass deceasedDetailClass = new DeceasedDetailClass();
        ddcs = deceasedDetailClass.loadDeceased(cl, su);
        return ddcs;
    }

    @Override
    public void updateDatabase(DeathCertRequest dcr, DeathCertRequestFacade dcrf) {
        dcr.setRegionalApproved("YES");
        dcr.setCertPrinted("YES");
        dcrf.deathCertRequestUpdate(dcr);
    }

    @Override
    public void renderCertificate(Collection c) {
        ReportController.getInstance().loadDefaultParameters();
        ReportController.getInstance().showReport(c, getClass().getResourceAsStream(ReportController.DEATH_CERTIFICATE));
    }

}
