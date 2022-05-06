package com.wasitech.basics.classes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class MultiFun {
    public static boolean contains(String s1, String s2) {
        return s2.contains(s1) || s1.contains(s2) || s1.equalsIgnoreCase(s2);
    }

    public static boolean containsIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase())
                || s2.toLowerCase().contains(s1.toLowerCase())
                || s1.equalsIgnoreCase(s2);
    }


    public static boolean equals(String s1, String s2) {
        return (s1 == null && s2 == null) || ((s1 != null && s2 != null) && (s1.trim().equals(s2.trim())));
    }
    public static boolean EmailValidator(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public static boolean onlyNumbers(String text) {
        // return numbers
        for (char t : text.toCharArray()) {
            if (t < '0' || t > '9')
                return false;
        }
        return true;
    }
    public static boolean isContactNumber(String str) {
        if (str.isEmpty()) return false;
        int plus = 0, dash = 0;
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9') {
                if (c == '+') {
                    if (plus == 0)
                        plus = 1;
                    else
                        return false;
                }
                else if (c == '-') {
                    if (dash == 0)
                        dash = 1;
                    else
                        return false;
                } else if (c != ' ')
                    return false;
            }

        }
        return true;
    }


    public static ArrayList<String> removeDuplicate(ArrayList<String>list){
        int end = list.size();
        Set<String> set = new HashSet<>();
        for(int i = 0; i < end; i++){
            set.add(list.get(i));
        }
        return new ArrayList<>(set);
    }
}
