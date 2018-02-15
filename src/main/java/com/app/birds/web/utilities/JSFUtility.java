/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.utilities;

import java.util.List;
import java.util.ResourceBundle;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sabonay
 */
public class JSFUtility {

    public JSFUtility() {
    }
    int counter = 0;

    public static Object getManageBean(String beanName, FacesContext fc) {
        if (fc == null) {
        } else {
            fc = getCurrentContext();
        }

        return fc.getApplication().getELResolver().getValue(fc.getELContext(), null, beanName);
    }

    public static FacesContext getCurrentContext() {
        return FacesContext.getCurrentInstance();
    }

    public static FacesContext currentContext() {
        return FacesContext.getCurrentInstance();
    }

    public static ExternalContext externalContext() {
        return currentContext().getExternalContext();
    }

    public static ValueExpression createValueExpression(String elExpression, Class type) {
        return currentContext().getApplication().getExpressionFactory().createValueExpression(currentContext().getELContext(), "#{" + elExpression + "}", type);
    }

    public SelectItem[] createMenuItems(List<Object> objList, boolean needInitial) {
        counter = 0;
        SelectItem[] items;

        if (needInitial) {
            items = new SelectItem[objList.size() + 1];
            items[counter] = new SelectItem("", "-- Please Select--");
            counter++;
        } else {
            items = new SelectItem[objList.size()];
        }

        for (Object slist : objList) {
            items[counter] = new SelectItem(slist, slist.toString());
            counter++;
        }
        return items;
    }

    public static void addMessage(FacesMessage.Severity severity, String messageTitle, String message) {
        FacesMessage fm = new FacesMessage(severity, messageTitle, message);
        getCurrentContext().addMessage(null, fm);
    }

    public static void warnMessage(String messageTitle, String message) {
        addMessage(FacesMessage.SEVERITY_WARN, messageTitle, message);
    }

    public static void infoMessage(String messageTitle, String message) {
        addMessage(FacesMessage.SEVERITY_INFO, messageTitle, message);
    }

    public static void errorMessage(String messageTitle, String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, messageTitle, message);
    }

    public static void fatalMessage(String messageTitle, String message) {
        addMessage(FacesMessage.SEVERITY_FATAL, messageTitle, message); 
    }

    public static HttpSession getSession() {

        HttpSession session = (HttpSession) externalContext().getSession(true);
        return session;
    }

    public static void putSessionValue(String name, Object val) {
        //System.out.println(name + " : " + val);
        getSession().setAttribute(name, val);

    }

    public static Object getSessionValue(String name) {
        return getSession().getAttribute(name);

    }

    public static void destroySession() {
        getSession().invalidate();
    }

    public static void redirectPage(FacesContext context, String formOutcome) {
        context.responseComplete();
        //System.out.println(context + " REdirecting to " + formOutcome);
        try {
            context.getExternalContext().redirect("/main.xhtml");
        } catch (Exception e) {
        }

    }


    private static ResourceBundle bundle = null;

    public static ResourceBundle getBundle() {
        if (bundle == null) {
            try {
                System.out.println("Searhicn bundle");
                bundle = currentContext().getApplication().getResourceBundle(currentContext(), "msg");

            } catch (Exception e) {
                System.out.println("Bundle Not Found : " + e.toString());
                e.printStackTrace();
            }
        }

        return bundle;
    }

    public static String getResourceBundleKeyValue(String key) {

        String result = "";
        try {
            System.out.println("Key : " + key);


            result = getBundle().getString(key);
        } catch (Exception e) {
            System.out.println("Resource Not Found : " + key);
        }


        return result;
    }

    public static String pageFullPath() {

        return externalContext().getRequestPathInfo();
    }

    public static String pageRelativePath() {

        String path = externalContext().getRequestPathInfo();

        return path.substring(path.lastIndexOf("/") + 1);
    }

    public static boolean createCookie(String cookieName, String cookieValue) {
        try {
            Cookie cookie = new Cookie(cookieName, cookieValue);
            HttpServletResponse res = (HttpServletResponse) externalContext().getResponse();
            res.addCookie(cookie);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static String getCookie(String cookieName) {
        HttpServletRequest request = (HttpServletRequest) externalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        boolean cookieFound = false;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
//doSomethingWith(cookie.getValue());
                    System.out.println("cookie : " + cookie.getValue());
                    try {

                        return cookie.getValue();
                    } catch (Exception e) {
                    }

                    cookieFound = true;
                }
            }
        }

        return "";

    }
}
