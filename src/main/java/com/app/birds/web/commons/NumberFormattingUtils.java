/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.app.birds.web.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Opanin
 */
public class NumberFormattingUtils {

    private static String formattedString = "";


    private static NumberFormat nf = NumberFormat.getInstance();
    private static DecimalFormat decimalFormat = new DecimalFormat();

    private static String DECIMAL_PATTERN = "###.##";
//    public static

    public static String formatNumber(int number, int numofdigs)
    {
        nf.setMinimumIntegerDigits(numofdigs);
        return nf.format(number);
    }

    public static String formatNumber(String number, int numofdigs)
    {
        nf.setMinimumIntegerDigits(numofdigs);
        return nf.format(Integer.parseInt(number));
    }

    public static String getFormatedAmount(double number)
    {
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        return nf.format(number);
    }

    public static String formatDouble(double number, int numberOfDigit)
    {
        nf.setMaximumFractionDigits(numberOfDigit);
        nf.setMinimumFractionDigits(numberOfDigit);
        return nf.format(number);

    }

    public static Double formatDoubleNum(double number, int numberOfDigit)
    {
        nf.setMaximumFractionDigits(numberOfDigit);
        nf.setMinimumFractionDigits(numberOfDigit);
        
        String formatedNumber =  nf.format(number);

        return Double.parseDouble(formatedNumber);

    }

    public static Double formatDecimalNumberTo_2(double number)
    {
        decimalFormat.applyPattern(DECIMAL_PATTERN);
        
        String numberString =  decimalFormat.format(number);

        return Double.parseDouble(numberString);
    }

    public static Double formatDecimalNumberTo_2(double number, int numberOfDecimalPoint)
    {
        decimalFormat.applyPattern(DECIMAL_PATTERN);

        String numberString =  decimalFormat.format(number);

        return Double.parseDouble(numberString);
    }


//    public static String formatDecimalNumberTo_2(double number)
//    {
//        decimalFormat.applyPattern(DECIMAL_PATTERN);
//
//        String numberString =  decimalFormat.format(number);
//
//        return Double.parseDouble(numberString);
//    }

    public static String formatNumberAsPosition(int number)
    {

        if(number == 0)
            return "";

        String numberString = Integer.toString(number);

        if(numberString.equalsIgnoreCase("11"))
        {
            formattedString = number + "th";
        }
        else if(numberString.equalsIgnoreCase("12"))
        {
            formattedString = number + "th";
        }
        else if(numberString.equalsIgnoreCase("13"))
        {
            formattedString = number + "th";
        }
        else if(numberString.endsWith("1"))
        {
            formattedString = number + "st";
        }
        else if(numberString.endsWith("2"))
        {
            formattedString = number + "nd";
        }
        else if(numberString.endsWith("3"))
        {
            formattedString = number + "rd";
        }
        else
            formattedString = number + "th";
       
        return formattedString;
    }

    public static double ObjectToDouble(Object value)
    {

        if(value == null)
            return 0;

        double d = 0;

        String doubleValueString = value.toString();

        try {
            d = Double.parseDouble(doubleValueString);
        } catch (Exception e)
        {
            return 0;
        }

        return d;
    }
}
