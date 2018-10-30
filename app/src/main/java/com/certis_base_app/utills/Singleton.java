package com.certis_base_app.utills;

import android.util.Patterns;

public class Singleton {

    private static Singleton self;

    public static Singleton getInstance() {
        if (self == null)
            self = new Singleton();

        return self;
    }

    public Singleton()
    {
        self = this;
    }

    /*public static boolean isInvalidPhoneNo(CharSequence target) {
        return target == null || target.length() == 0 || !Patterns.PHONE.matcher(target).matches();
    }*/
}
