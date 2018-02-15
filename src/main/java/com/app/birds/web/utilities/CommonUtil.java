/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.birds.web.utilities;

import java.util.Iterator;
import java.util.UUID;

/**
 *
 * @author ad
 */
public class CommonUtil {

    public static String firstLettersOfSentence(String sentence){
    String firstLetters="";
        String [] words = sentence.split(" ");
        for (String word : words) {
            firstLetters+=word.substring(0, 1);
        }
        return firstLetters;
    }

    
     public static String generateID()
    {
        return UUID.randomUUID().toString();

    }
}
