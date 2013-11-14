/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jc.se.util.compare;

import java.util.Comparator;

/**
 *
 * @author ruffy
 */
public class StringComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1 == o2
                || (o1 == null && o2 == null)) {
            return 0;
        } else if (o2 == null) {
            return 1;
        } else if (o1 == null) {
            return -1;
        } else {
            for (int i = 0; i < o1.length() && i < o2.length(); i++) {
                if (Character.isDigit(o2.charAt(i)) && Character.isDigit(o1.charAt(i))) {
                   int digitDiff = compareNumbers(o1, o2, i);
                   if(digitDiff != 0) {
                       return digitDiff;
                   }
                } else if(compareChars(o1.charAt(i), o2.charAt(i)) != 0) {
                    return compareChars(o1.charAt(i), o2.charAt(i));
                }
            }
        }
        
        return o1.length() - o2.length();
    }
    
    private int compareNumbers(String string1, String string2, int startIndex) {
        int firstNumber = parseNumber(startIndex, string1);
        
        int secondNumber = parseNumber(startIndex, string2);
        
        return firstNumber - secondNumber;
    }

    protected int parseNumber(int startIndex, String string1) {
        int number = 0;
        for(int i = startIndex; i < string1.length(); i++) {
            number = 10*number + Character.getNumericValue( string1.charAt(i));
        }
        return number;
    }
    
    private int compareChars(Character ch1, Character ch2) {
        return ch1.compareTo(ch2);
    }
}
