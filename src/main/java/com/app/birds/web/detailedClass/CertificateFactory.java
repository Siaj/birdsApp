/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.detailedClass;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class CertificateFactory {

    public Certificate getCertificateType(String certificateType) {
        if (certificateType == null) {
            return null;
        }

        if (certificateType.equalsIgnoreCase("Birth")) {
            return new BirthCertificate();
        } else if (certificateType.equalsIgnoreCase("Death")) {
            return new DeathCertificate();
        } else {
            return null;
        }
    }
}
