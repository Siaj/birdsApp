/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import com.app.birds.ejbSessions.CommonBean;
import com.app.birds.ejbSessions.DistrictAdminBean;
import com.app.birds.ejbSessions.RegionalAdminBean;
import com.app.birds.ejbSessions.RegistrarBean;
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
    private static CommonBean commonBean;
    private static DistrictAdminBean districtAdminBean;
    private static RegionalAdminBean regionalAdminBean;
    private static RegistrarBean registrarBean;

//    static String JNDI_SIRS_SESSION = "java:global/birdsWeb/BirdsSessionBean";
//    static String SIRS_SESSION_BEAN_FULL_PATH = "birds.ejb.sessions.BirdsSessionBean";
    static String JNDI_BIRDS = "java:global/birds-app/";
    static String BIRDS_SESSION_BEANS_FULL_PATH = "com.app.birds.ejbSessions.";

    static String JNDI_SUPPORT_BEAN = "java:global/birds-app/SupportBean";
    static String JNDI_SUPPORT_BEAN_PATH = "com.app.birds.ejbSessions.SupportBean";

    static String JNDI_COMMON_BEAN = JNDI_BIRDS + "CommonBean";
    static String JNDI_COMMON_BEAN_PATH = BIRDS_SESSION_BEANS_FULL_PATH + "CommonBean";

    static String JNDI_REG_ADMIN_BEAN = JNDI_BIRDS + "RegionalAdminBean";
    static String JNDI_REG_ADMIN_BEAN_PATH = BIRDS_SESSION_BEANS_FULL_PATH + "RegionalAdminBean";

    static String JNDI_DIST_ADMIN_BEAN = JNDI_BIRDS + "DistrictAdminBean";
    static String JNDI_DIST_ADMIN_BEAN_PATH = BIRDS_SESSION_BEANS_FULL_PATH + "DistrictAdminBean";

    static String JNDI_REGISTRAR_BEAN = JNDI_BIRDS + "RegistrarBean";
    static String JNDI_REGISTRAR_BEAN_PATH = BIRDS_SESSION_BEANS_FULL_PATH + "RegistrarBean";

    public static SupportBean getSupportSessionBean() {
        try {
            Context context = new InitialContext();
            supportBean = (SupportBean) context.lookup(JNDI_SUPPORT_BEAN + "!" + JNDI_SUPPORT_BEAN_PATH);
            return supportBean;
        } catch (NamingException ne) {
            System.out.println("Error During Invocation " + ne.toString());
            getSupportSessionBean();
            Logger.getLogger(SupportBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
            throw new RuntimeException(ne);
        }
    }

    public static CommonBean getCommonSessionBean() {
        try {
            Context context = new InitialContext();
            commonBean = (CommonBean) context.lookup(JNDI_COMMON_BEAN + "!" + JNDI_COMMON_BEAN_PATH);
            return commonBean;
        } catch (NamingException ne) {
            System.out.println("Error During Invocation " + ne.toString());
            getCommonSessionBean();
            Logger.getLogger(CommonBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
            throw new RuntimeException(ne);
        }
    }

    public static RegionalAdminBean getRegionalAdminSessionBean() {
        try {
            Context context = new InitialContext();
            regionalAdminBean = (RegionalAdminBean) context.lookup(JNDI_REG_ADMIN_BEAN + "!" + JNDI_REG_ADMIN_BEAN_PATH);
            return regionalAdminBean;
        } catch (NamingException ne) {
            System.out.println("Error During Invocation " + ne.toString());
            getRegionalAdminSessionBean();
            Logger.getLogger(SupportBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
            throw new RuntimeException(ne);
        }
    }

    public static DistrictAdminBean getDistrictAdminSessionBean() {
        try {
            Context context = new InitialContext();
            districtAdminBean = (DistrictAdminBean) context.lookup(JNDI_DIST_ADMIN_BEAN + "!" + JNDI_DIST_ADMIN_BEAN_PATH);
            return districtAdminBean;
        } catch (NamingException ne) {
            System.out.println("Error During Invocation " + ne.toString());
            getDistrictAdminSessionBean();
            Logger.getLogger(DistrictAdminBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
            throw new RuntimeException(ne);
        }
    }

    public static RegistrarBean getRegistrarSessionBean() {
        try {
            Context context = new InitialContext();
            registrarBean = (RegistrarBean) context.lookup(JNDI_REGISTRAR_BEAN + "!" + JNDI_REGISTRAR_BEAN_PATH);
            return registrarBean;
        } catch (NamingException ne) {
            System.out.println("Error During Invocation " + ne.toString());
            getRegistrarSessionBean();
            Logger.getLogger(RegistrarBean.class.getName()).log(Level.SEVERE, "exception caught during invocation", ne);
            throw new RuntimeException(ne);
        }
    }
}
