/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.controllers;

import com.app.birds.entities.Region;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class MessageObserver {

    public void trace(@Observes Region region) {
        System.out.println(region);
    }

}
