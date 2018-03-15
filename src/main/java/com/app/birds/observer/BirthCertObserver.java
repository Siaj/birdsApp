/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.observer;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Named(value = "birthCertObserver")
@ApplicationScoped
public class BirthCertObserver {

    /**
     * Creates a new instance of BirthCertObserver
     */
    public BirthCertObserver() {
    }
    
}
