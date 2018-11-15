package com.certis_base_app;


import android.app.Application;
import android.content.SharedPreferences;

import com.certis_base_app.utills.SharedPrefHandler;

public class AppConfig extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        SharedPrefHandler.initialize(this);
        SharedPrefHandler.clearData();
    }
}
