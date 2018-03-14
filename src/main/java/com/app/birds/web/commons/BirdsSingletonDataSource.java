/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import com.app.birds.ejbSessions.SupportBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class BirdsSingletonDataSource {

    private static SupportBean supportBean;

    static String JNDI_SUPPORT_BEAN = "java:global/birds-app/SupportBean";
    static String JNDI_SUPPORT_BEAN_PATH = "com.app.birds.ejbSessions.SupportBean";

//    public static SupportBean getSupportSessionBean() {
//        try {
//            Context context = new InitialContext();
//            supportBean = (SupportBean) context.lookup(JNDI_SUPPORT_BEAN + "!" + JNDI_SUPPORT_BEAN_PATH);
//            return supportBean;
//        } catch (NamingException ne) {
//            System.out.println("Error During Invocation " + ne.toString());
//            getSupportSessionBean();
//            Logger.getLogger(SupportBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
//            throw new RuntimeException(ne);
//        }
//    }

}
