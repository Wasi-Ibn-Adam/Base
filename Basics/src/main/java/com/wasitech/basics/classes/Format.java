package com.wasitech.basics.classes;

import java.util.ArrayList;
import java.util.Calendar;

public class Format {

    public static String longToTime(long time){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime().toString();
    }

    public static String millisToTime(long millis) {
        int sec = (int) millis / 1000;
        return secToTime(sec);
    }

    public static String secToTime(int sec) {
        if (sec <= 0)
            return "0:00";
        if (sec < 60)
            return "0:" + Format.Max2(sec);
        int min = sec / 60;
        if (min < 60)
            return min + ":" + Format.Max2(sec % 60);
        int hor = min / 60;
        return hor + ":" + Format.Max2(min % 60) + ":" + Format.Max2(sec % 3600);
    }

    public static String sizeToMaxGBs(long bytes) {
        if (bytes < 0)
            return "0 bytes";
        if (bytes < 1024)
            return bytes + " bytes";
        long kb = bytes / 1024;
        if (kb < 1024)
            return kb + " KB";
        long mb = kb / 1024;
        if (mb < 1024)
            return mb + "." + Format.Max2(percent(1024, (int) kb % 1024)) + " Mbs";
        long gbs = mb / 1024;
        return gbs + "." + Format.Max2(percent(1024, (int) mb % 1024)) + " Gbs";
    }

    public static int percent(int tot, int cur) {
        int p = cur * 100;
        return p / tot;
    }

    public static String Max2(Object o) {
        if (o.toString().length() == 1)
            return "0" + o;
        else
            return o.toString();
    }

    public static String firebasePath(String s) {
        if (s == null)
            return null;
        return s.replace(".", "")
                .replace("#", "")
                .replace("[", "")
                .replace("]", "");
    }

    public static String firebasePath(String s, String c) {
        if (s == null)
            return null;
        return s.replace(".", c)
                .replace("#", c)
                .replace("[", c)
                .replace("]", c);
    }

    public static ArrayList<String> duplicateRemover(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!newList.contains(list.get(i))) {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    public static String sentenceCase(String str) {
        String[] token = str.split("\\.");
        StringBuilder builder = new StringBuilder();
        for (String s : token) {
            String line = s;
            String fc = line.charAt(0) + "";
            line = fc.toUpperCase() + line.substring(1) + ".";
            builder.append(line);
        }
        return builder.toString();
    }

    public static String titleCase(String str) {
        String[] token = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : token) {
            String line = s;
            String fc = line.charAt(0) + "";
            line = fc.toUpperCase() + line.substring(1) + " ";
            builder.append(line);
        }
        return builder.toString();
    }

    public static String onlyNumbers(String str) {
        if (str.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9')
                continue;
            builder.append(c);
        }
        return builder.toString();
    }

    public static String numberFormat(String num) {
        if(num.length()<=4)
            return num;
        return In_3_Format(num.substring(0,num.length()-4))+"-"+num.substring(num.length()-4);
    }
    public static String number7Format(String num) {
        if(num.length()<=7)
            return num;
        return In_3_Format(num.substring(0,num.length()-7))+"-"+num.substring(num.length()-7);
    }
    public static String In_3_Format(String num) {
        String str=new StringBuilder(num).reverse().toString().trim();
        StringBuilder builder=new StringBuilder();
        for(int i=1;i<=str.length();i++){
            builder.append(str.charAt(i-1));
            if(i==str.length()-1)
                if(str.length()%3==1)
                    continue;
            if(i%3==0&&i!=str.length())
                builder.append("-");
        }





        return builder.reverse().toString();
    }
    /**
     * INPUT ["as","as","asa","ssdd"]<br>
     * OUTPUT "as,as,asa,ssdd"
     */
    public static String toString(ArrayList<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }



}
