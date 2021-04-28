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
        static final String TOKEN = "tokem";
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

    public void saveIsLogin(boolean isLogin) {
        getPreferences().edit().putBoolean(PrefKeys.ISLOGIN, isLogin).apply();
    }

    public void saveUserId(String userId) {
        getPreferences().edit().putString(PrefKeys.USERID, userId).apply();
    }

    public String  getUserId() {
        return getPreferences().getString(PrefKeys.USERID, "");
    }

    public void saveToken(String token) {
        getPreferences().edit().putString(PrefKeys.TOKEN, token).apply();
    }

    public String geToken() {
        return getPreferences().getString(PrefKeys.TOKEN, "");
    }

    public void clearSharedPref() {
        getPreferences().edit().clear().commit();
    }
}
