package com.veirn.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

public class pref {
    Context c;
    public static final String nameKey = "nameKey";
    public static final String Metric = "Metric";
    public static final String Imperial= "Imperial";
    public static final String ENABLED = "Enabled";
    public static final String Temp  = "Temp";


    public pref(Context c) {
        this.c = c;
    }

    public static SharedPreferences getsharedPref(Context c) {
        SharedPreferences pref = c.getSharedPreferences("prefID", Context.MODE_PRIVATE);

        return pref;
    }

}
