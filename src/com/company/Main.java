package com.company;

import java.util.HashMap;
import java.util.Scanner;

class Converter {
    private final HashMap<String, String> ROMAN_SYMBOLS = new HashMap();
    private int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    private String[] letters = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

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
    }

    public String toArabic(String roman) {
        if (ROMAN_SYMBOLS.containsKey(roman)) {
            return ROMAN_SYMBOLS.get(roman);
        }

        return roman;
    }

    public String toRoman(int arabic) {
        String roman = "";
        int currentNum = arabic;

        for (int i = 0; i < numbers.length; i++) {
            while (currentNum >= numbers[i]) {
                roman += letters[i];
                currentNum -= numbers[i];
            }
        }

        return roman;
    }

    public boolean isRoman(String number) {
        return ROMAN_SYMBOLS.containsKey(number);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        try {
            System.out.println(str + " = " + calc(str));
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

            if (num1 < 1 || num2 < 1 || num1 > 10 || num2 > 0) {
                throw new Exception("invalid range nums");
            }

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

                return converter.toRoman(result);
            }

            return String.valueOf(result);
        }

        throw new Exception("invalid numbers format");
    }
}
