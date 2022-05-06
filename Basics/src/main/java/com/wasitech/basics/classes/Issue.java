package com.wasitech.basics.classes;

import com.android.volley.VolleyError;

import java.util.Arrays;

public class Issue {

    public static void print(Exception e, String c) {
        e.printStackTrace();
    }

    public static void Internet(VolleyError volleyError) {
        Basics.Log("volley error");
        volleyError.printStackTrace();
    }

    public static void Log(Exception e, String function) {
        Basics.Log("<<---------------------------------------------------->>");
        Basics.Log("BASIC-FUN-ERROR->> " + function);
        Basics.Log(Arrays.toString(e.getStackTrace()));
        Basics.Log("<<--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!->>");
    }

}
