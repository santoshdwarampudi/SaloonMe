package com.saloonme.interfaces;

import android.app.Dialog;
import android.content.Context;


public interface IActivityBaseView {

    void showProgressDialog(String msg);

    void dismissProgress();

    boolean isProgressVisible();

    void showToast(String msg);

    Context getContext();

    void showAlertDialog(String title, String alertMsg, OnPositiveClick onPositiveClick);

    void showAlertDialog(String title, String alertMsg);

    interface OnPositiveClick {
        void onClick();
    }

}
