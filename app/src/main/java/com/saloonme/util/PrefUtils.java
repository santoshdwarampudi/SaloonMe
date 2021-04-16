package com.saloonme.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.saloonme.interfaces.StringConstants;

public class PrefUtils {
    private static PrefUtils prefUtils;
    private Context context;

    private PrefUtils() {
    }

    private class PrefKeys {
        static final String BIDBOL_SHARED_PREF = StringConstants.APPNAME;
        static final String ISLOGIN = "isLogin";
        static final String USERID = "userId";
    }

    public static PrefUtils getInstance() {
        if (prefUtils == null) {
            synchronized (PrefUtils.class) {
                prefUtils = new PrefUtils();
            }
        }
        return prefUtils;
    }

    public void setPrefContext(Context context) {
        this.context = context;
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences(PrefKeys.BIDBOL_SHARED_PREF, 0);
    }

    public boolean isLogin() {
        return getPreferences().getBoolean(PrefKeys.ISLOGIN, false);
    }

    public void clearSharedPref() {
        getPreferences().edit().clear().commit();
    }
}
