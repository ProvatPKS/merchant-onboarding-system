package com.mc.program.utility;


import com.mc.program.vo.Merchant;

import java.util.Random;

public class CommonsUtility {

    public static char[] generatePassword(int len) {
        // A strong password has Cap_chars, Lower_chars,
        // numeric value and symbols. So we are using all of
        // them to generate our password
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$&";

        String values = Capital_chars + Small_chars + numbers + symbols;
        // Using random method
        Random rand = new Random();
        char[] pwd = new char[len];

        for (int i = 0; i < len; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            pwd[i] = values.charAt(rand.nextInt(values.length()));
        }
        return pwd;
    }

    public static String generateUserIdGeneration(Merchant merchant) {
        // Read only region end
        String firstName = merchant.getLegalName();
        String orgName = merchant.getOrganizationName();
        long pin = merchant.getPostalCode();
        int N = 5;

        String longerName;
        String smallerName;
        StringBuilder userId = new StringBuilder();

        if (firstName.length() > orgName.length()) {
            longerName = firstName;
            smallerName = orgName;
        } else if (firstName.length() < orgName.length()) {
            longerName = orgName;
            smallerName = firstName;
        } else {
            if (firstName.compareTo(orgName) < 1) {
                longerName = orgName;
                smallerName = firstName;
            } else {
                longerName = firstName;
                smallerName = orgName;
            }
        }

        userId.append(smallerName.charAt(smallerName.length() - 1));
        userId.append(longerName);

        for (int i = 0; i < userId.length(); i++) {
            if (Character.isUpperCase(userId.charAt(i)))
                userId.setCharAt(i, Character.toLowerCase(userId.charAt(i)));
            else
                userId.setCharAt(i, Character.toUpperCase(userId.charAt(i)));
        }

        userId.append(String.valueOf(pin).charAt(N - 1));
        userId.append(String.valueOf(pin).charAt(String.valueOf(pin).length() - N));

        return userId.toString();
    }


}
