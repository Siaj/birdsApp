/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.ejbSessions;

import com.app.birds.entities.UserAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
@Stateless
public class SupportBean {
    @PersistenceContext(unitName = "birds-ejbPU")
    private EntityManager em;
    
      public UserAccount authenticateUser(String username, String password) {
        try {
            return (UserAccount) em.createNamedQuery(UserAccount.FIND_BY_USERNAME_PASSWORD).setParameter("username", username).setParameter("password", password).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
