package com.saloonme.ui.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.saloonme.R;
import com.saloonme.interfaces.IBaseView;


import java.util.List;

public class BaseBottomSheetDialogFragment
        extends BottomSheetDialogFragment
        implements IBaseView {

    private IBaseView mBaseInterface;

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(requireContext(), getTheme()); // this doesn't call onViewCreated for fragments extending this
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void showProgressDialog(String msg) {

    }

    @Override
    public void dismissProgress() {
        mBaseInterface.dismissProgress();
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public boolean isUsable() {
        return false;
    }

    @Override
    public boolean isProgressVisible() {
        return false;
    }


}
