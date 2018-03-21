/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.reports;

import java.util.List;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 * @param <T>
 * @param <S>
 * @param <C>
 * @param <F>
 */
public abstract class CertificateTemplate<T, S, C, F> {

    public final void printCertificate(T t, S s, C c, F f) {
        loadPrintDetails(t, s);
        updateDatabase(t, f);
        renderCertificate(c);
    }

    public abstract List loadPrintDetails(T cl, S su);

    public abstract void updateDatabase(T bcr, F f);

    public abstract void renderCertificate(C c);
}
