package com.example.wyj.smartbulter.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {
//    private void test(Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        sharedPreferences.getString("key", "未获取到");
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("key", "value");
//        editor.commit();
//    }
    public static final String NAME = "config";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void deleteShare(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    public static void deleteAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
