package com.nav.ordermanagementsystem.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {

    public static final String PASSWORD = "pwd";
    public static String USER_NAME = "user_name";
    private static String APP_PREFERENCES = "DUC_DS";

    public static SharedPreferences.Editor getShardPreferenceEditor(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        return sharedpreferences.edit();
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
    }


}
