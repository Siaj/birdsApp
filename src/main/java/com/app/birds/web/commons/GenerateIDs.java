/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.birds.web.commons;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.UnsupportedEncodingException;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author abdulmumin
 */
public class GenerateIDs {

    private static String convertString, generatedId;

//    public static String generatePassword(String string) {
//
//        try {
//            convertString = string.toUpperCase();
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
//            byte[] code = messageDigest.digest((convertString + CommonUtil.generateID()).getBytes());
//            BASE64Encoder encoder = new BASE64Encoder();
//            String digest = encoder.encode(code);
//            generatedId = digest.substring(0, 3);
//            generatedId = generatedId + digest.substring(8, 12);
//            return generatedId;
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(GenerateIDs.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//
//    }
    
    
/*
    The SHA (Secure Hash Algorithm) is one of the popular cryptographic hash functions. 
A cryptographic hash can be used to make a signature for a text or a data file.

The SHA-256 algorithm generates an almost-unique, fixed-size 256-bit (32-byte) hash. 
This is a one-way function, so the result cannot be decrypted back to the original value.

Currently, SHA-2 hashing is widely used as it is being considered as the most secure hashing
algorithm in the cryptographic arena.
*/
    
    public static String generateHash(String password) {
        String output = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();

        return output;
    }

    
    
//    public static void main(String[] args) {
//        System.out.println(generateHash("amohammed"));
//        System.out.println(generateHash("awiddrisu"));
//        System.out.println(generateHash("snabubakar"));
//        System.out.println(generateHash("jpeter"));
//        System.out.println(generateHash("ssahmed"));
//        System.out.println(generateHash("asulemana"));
//        System.out.println(generateHash("sibdow"));
//        
//    }
    
    
    public static String encodeSHA256(String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest);
    }

    public static String generateUsername(String surname, String othername) {

        String initials = "";

        String[] splitOname = othername.split(" ");

        List<String> splittedName = new ArrayList<>();

        for (String s : splitOname) {
            if (!s.equals("")) {
                splittedName.add(s);
            }
        }

        for (String s : splittedName) {
            initials += s.substring(0, 1).toLowerCase();
        }

        return (initials.toLowerCase() + surname.toLowerCase());

    }

}
