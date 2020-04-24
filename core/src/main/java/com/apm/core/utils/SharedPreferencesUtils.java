package com.apm.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ing. Oscar G. Medina Cruz on 11/08/17.
 *
 * Handle shared preferences actions and functions
 */

public class SharedPreferencesUtils {

    /**
     * Returns a value of user shared preference
     *
     * @param context current application context
     * @param sharedPreferenceName shared preference name
     * @return shared preference value
     */
    public static String GetSharedPreferenceString(Context context, String sharedPreferenceName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(sharedPreferenceName, "");
    }

    /**
     * Save a value into user shared preference
     *
     * @param context current application context
     * @param sharedPreferenceName shared preference name
     * @param sharedPreferenceValue shared preference value to insert
     */
    public static void PutSharedPreferenceString(Context context, String sharedPreferenceName, String sharedPreferenceValue){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(sharedPreferenceName, sharedPreferenceValue);
        editor.apply();
    }

    /**
     *
     * @param context
     * @param sharedPreferenceName
     */
    public static void RemoveSharedPreferenceString(Context context, String sharedPreferenceName){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(sharedPreferenceName);
        editor.apply();
    }
}
