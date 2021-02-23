package com.example.rebolectric;

import android.content.SharedPreferences;

/**
 * Created by zhengwei on 2021/2/23.
 */
public class MySharePreference {

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public MySharePreference(SharedPreferences sharedPref) {
        this.sharedPref = sharedPref;
        this.editor = sharedPref.edit();
    }

    public void put(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key) {
        return sharedPref.getString(key, "");
    }
}
