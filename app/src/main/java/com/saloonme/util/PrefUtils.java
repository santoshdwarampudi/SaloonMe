package com.saloonme.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.saloonme.interfaces.StringConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrefUtils {
    private static PrefUtils prefUtils;
    private Context context;

    private PrefUtils() {
    }

    private class PrefKeys {
        static final String BIDBOL_SHARED_PREF = StringConstants.APPNAME;
        static final String ISLOGIN = "isLogin";
        static final String USERID = "userId";
        static final String USER_NAME = "userName";
        static final String TOKEN = "tokem";
        static final String LAT = "lat";
        static final String LONG = "long";
        static final String CART_DETAILS = "cart_details";
        static final String EMAIL = "email";
        static final String PHONE_NUMBER = "mobileNumber";
        static final String DATE = "date";
        static final String TIME = "time";
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

    public void saveMobileNumber(String mobileNumber) {
        getPreferences().edit().putString(PrefKeys.PHONE_NUMBER, mobileNumber).apply();
    }

    public void saveEmail(String emailId) {
        getPreferences().edit().putString(PrefKeys.EMAIL, emailId).apply();
    }

    public String getUserId() {
        return getPreferences().getString(PrefKeys.USERID, "");
    }

    public void saveUserName(String userId) {
        getPreferences().edit().putString(PrefKeys.USER_NAME, userId).apply();
    }

    public String getUserName() {
        return getPreferences().getString(PrefKeys.USER_NAME, "");
    }

    public String getMobileNumber() {
        return getPreferences().getString(PrefKeys.PHONE_NUMBER, "");
    }

    public String geEmailId() {
        return getPreferences().getString(PrefKeys.EMAIL, "");
    }

    public void saveToken(String token) {
        getPreferences().edit().putString(PrefKeys.TOKEN, token).apply();
    }

    public String geToken() {
        return getPreferences().getString(PrefKeys.TOKEN, "");
    }

    public void saveLat(String lat) {
        getPreferences().edit().putString(PrefKeys.LAT, lat).apply();
    }

    public String geLat() {
        return getPreferences().getString(PrefKeys.LAT, "");
    }

    public void saveLogni(String logni) {
        getPreferences().edit().putString(PrefKeys.LONG, logni).apply();
    }

    public String geLogni() {
        return getPreferences().getString(PrefKeys.LONG, "");
    }

    public void saveBookingDate(String bookingDate) {
        getPreferences().edit().putString(PrefKeys.DATE, bookingDate).apply();
    }

    public String getBookingDate() {
        return getPreferences().getString(PrefKeys.DATE, "");
    }

    public void saveBookingTime(String bookingTime) {
        getPreferences().edit().putString(PrefKeys.TIME, bookingTime).apply();
    }

    public String getBookingTime() {
        return getPreferences().getString(PrefKeys.TIME, "");
    }

    public void saveCartDetails(List<String> subServiceIdList, String userId) {
        HashMap<String, List<String>> cartHashList = new HashMap<String, List<String>>();
        Gson gson = new Gson();
        cartHashList.put(userId, subServiceIdList);
        String json = gson.toJson(cartHashList);
        getPreferences().edit().putString(PrefKeys.CART_DETAILS, json).apply();
    }


    public List<String> getCartList(String userId) {
        Gson gson = new Gson();
        String json = getPreferences().getString(PrefKeys.CART_DETAILS, null);
        if (json == null)
            return new ArrayList<>();
        Type type = new TypeToken<HashMap<String, List<String>>>() {
        }.getType();
        HashMap<String, List<String>> transactionModelHashList = gson.fromJson(json, type);
        if (transactionModelHashList.containsKey(userId))
            return transactionModelHashList.get(userId);
        else
            return new ArrayList<>();
    }

    public void clearSharedPref() {
        getPreferences().edit().clear().commit();
    }
}
