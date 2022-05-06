package com.wasitech.basics;

import android.content.Context;
import android.os.Environment;

import com.wasitech.basics.app.BaseApp;
import com.wasitech.basics.classes.Issue;

import java.io.File;

public class Storage {
    public static final String DOWN = Environment.DIRECTORY_DOWNLOADS;
    public static final String REC = Environment.DIRECTORY_MUSIC;
    public static final String IMG = Environment.DIRECTORY_DCIM;
    public static final String VID = Environment.DIRECTORY_MOVIES;
    public static final String APP = Environment.DIRECTORY_DOCUMENTS;
    public static final String DB = Environment.DIRECTORY_DOCUMENTS;


    public static File getDirectory(String root) {
        File dir = Environment.getExternalStoragePublicDirectory(root);
        File file = new File(dir, "Assist");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static File CreateBaseFile(Context context, String type) {
        File dir = context.getDir("Database", Context.MODE_PRIVATE);
        File db = new File(dir, type + "_" + fileName() + ".db");
        if (db.exists())
            db.delete();
        return db;
    }

    private static String fileName() {
        return "";
    }

    public static File CreateDataFile(String folder, String postfix) {
        File directory = getDirectory(folder);
        File file = new File(directory, fileName() + postfix);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    public static File CreateDataFile(String folder, String fileName, String postfix) {
        File directory = getDirectory(folder);
        File file = new File(directory, fileName + postfix);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    public static File GetDataFile(String folder, String fileName, String postfix) {
        File directory = getDirectory(folder);
        File file = new File(directory, fileName + postfix);
        if (file.exists()) {
            return file;
        }
        return null;
    }

    public static File CreateDataEncrypt(String fileName) {
        File directory = getDirectory(DB);
        return new File(directory, fileName + ".assist");
    }

    public static File CreateDataDecrypt(Context context, String name) {
        File dir = context.getDir("Database", Context.MODE_PRIVATE);
        return new File(dir, name + ".txt");
    }

    public static boolean clearCache(Context context) {
        try {
            File dir = context.getApplicationContext().getCacheDir();
            BaseApp.deleteDir(dir);
        } catch (Exception e) {
            Issue.print(e, Storage.class.getName());
        }
        return false;
    }
}
