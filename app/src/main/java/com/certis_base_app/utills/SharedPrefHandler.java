package com.certis_base_app.utills;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefHandler {

    private static final String PREFS_USERID_KEY = "UserIdKey";
    private static final String PREFS_USEREMAIL_KEY = "UserEmailKey";
    private static final String PREFS_PROFILE_PHOTO_1 = "ProfilePhoto1";
    private static final String PREFS_PROFILE_PHOTO_2 = "ProfilePhoto2";

    private static SharedPrefHandler instance;

    private static SharedPreferences preferences;

    public static void initialize(Context context) {
        if (instance == null)
            instance = new SharedPrefHandler(context);
    }

    private SharedPrefHandler(Context context) {
        instance = this;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * USER ID
     **/
    public static String getUserId() {
        SharedPreferences prefs = preferences;
        return prefs.getString(PREFS_USERID_KEY, null);
    }

    public static void setUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFS_USERID_KEY, userId);
        editor.apply();
    }


    /**
     * USER EMAIL
     **/
    public static void setUserEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFS_USEREMAIL_KEY, email);
        editor.apply();
    }

    public static String getPrefsUserEmail() {
        SharedPreferences prefs = preferences;
        return prefs.getString(PREFS_USEREMAIL_KEY, "");
    }


    /**
     * PROFILE PHOTO 1
     **/
    public static void setProfilePhoto1Verified(Boolean bool) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREFS_PROFILE_PHOTO_1, bool);
        editor.apply();
    }

    public static Boolean isProfilePhoto1Verified() {
        SharedPreferences prefs = preferences;
        return prefs.getBoolean(PREFS_PROFILE_PHOTO_1, false);
    }

    /**
     * PROFILE PHOTO 2
     **/
    public static void setProfilePhoto2Verified(Boolean bool) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PREFS_PROFILE_PHOTO_2, bool);
        editor.apply();
    }

    public static Boolean isProfilePhoto2Verified() {
        SharedPreferences prefs = preferences;
        return prefs.getBoolean(PREFS_PROFILE_PHOTO_2, false);
    }

    public static void clearData() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(PREFS_PROFILE_PHOTO_1).apply();
        editor.remove(PREFS_PROFILE_PHOTO_2).apply();
        editor.clear().commit();
    }
}
