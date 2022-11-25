package com.company;

import java.util.HashMap;

class Converter {
    private final HashMap<String, String> ROMAN_SYMBOLS = new HashMap();
    private final HashMap<String, String> ARABIC_SYMBOLS = new HashMap();

    Converter() {
        ROMAN_SYMBOLS.put("I", "1");
        ROMAN_SYMBOLS.put("II", "2");
        ROMAN_SYMBOLS.put("III", "3");
        ROMAN_SYMBOLS.put("IV", "4");
        ROMAN_SYMBOLS.put("V", "5");
        ROMAN_SYMBOLS.put("VI", "6");
        ROMAN_SYMBOLS.put("VII", "7");
        ROMAN_SYMBOLS.put("VIII", "8");
        ROMAN_SYMBOLS.put("IX", "9");

        ARABIC_SYMBOLS.put("1", "I");
        ARABIC_SYMBOLS.put("2", "II");
        ARABIC_SYMBOLS.put("3", "III");
        ARABIC_SYMBOLS.put("4", "IV");
        ARABIC_SYMBOLS.put("5", "V");
        ARABIC_SYMBOLS.put("6", "VI");
        ARABIC_SYMBOLS.put("7", "VII");
        ARABIC_SYMBOLS.put("8", "VIII");
        ARABIC_SYMBOLS.put("9", "IX");
        ARABIC_SYMBOLS.put("10", "X");

        ARABIC_SYMBOLS.put("11", "XI");
        ARABIC_SYMBOLS.put("12", "XII");
        ARABIC_SYMBOLS.put("13", "XIII");
        ARABIC_SYMBOLS.put("14", "XIV");
        ARABIC_SYMBOLS.put("15", "XV");
        ARABIC_SYMBOLS.put("16", "XVI");
        ARABIC_SYMBOLS.put("17", "XVII");
        ARABIC_SYMBOLS.put("18", "XVIII");
        ARABIC_SYMBOLS.put("19", "XIX");
        ARABIC_SYMBOLS.put("20", "XX");
    }

    public String toArabic(String roman) {
        if (ROMAN_SYMBOLS.containsKey(roman)) {
            return ROMAN_SYMBOLS.get(roman);
        }
        return roman;
    }

    public String toRoman(String arabic) {
        return ARABIC_SYMBOLS.get(arabic);
    }

    public boolean isRoman(String number) {
        return ROMAN_SYMBOLS.containsKey(number);
    }
}

public class Main {

    public static void main(String[] args) {
        String str1 = "1 + 2";
        String str2 = "VI / III";
        String str3 = "I - II";
        String str4 = "I + 1";
        String str5 = "1";
        String str6 = "1 + 2 + 3";

        try {
            System.out.println(str1 + " = " + calc(str1));
            System.out.println(str2 + " = " + calc(str2));
            // System.out.println(str3 + " = " + calc(str3));
            // System.out.println(str4 + " = " + calc(str4));
            // System.out.println(str5 + " = " + calc(str5));
            // System.out.println(str6 + " = " + calc(str6));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String calc(String input) throws Exception {
        final Converter converter = new Converter();
        final String[] operators = input.split(" ");

        if (operators.length != 3) {
            throw new Exception("invalid math statement");
        }

        if (converter.isRoman(operators[0]) == converter.isRoman(operators[2])) {
            final int num1 = Integer.parseInt(converter.toArabic(operators[0]));
            final int num2 = Integer.parseInt(converter.toArabic(operators[2]));
            int result;

            switch (operators[1]) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new Exception("invalid operation");
            }

            if (converter.isRoman(operators[0])) {
                if (result < 1) {
                    throw new Exception("invalid result statement");
                }

                return converter.toRoman(String.valueOf(result));
            }

            return String.valueOf(result);
        }

        throw new Exception("invalid numbers format");
    }
}
