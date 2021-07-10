package com.saloonme.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.StringConstants;

import butterknife.BindView;
import butterknife.OnClick;

public class OtpVerificationActivity extends BaseAppCompactActivity {
    private String otp;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.verify_btn)
    TextView verify_btn;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.et_otp)
    EditText et_otp;

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.verify_btn)
    void onVerifyOtpClick() {
        if (et_otp.getText().toString().isEmpty()) {
            showToast("Please enter otp");
            return;
        }
        if (!et_otp.getText().toString().equals(otp)) {
            showToast("Wrong otp");
            return;
        }
        goToActivity(LoginActivity.class);
        finishAffinity();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_otp_verification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText(getString(R.string.verify_otp));
        otp = getIntent().getStringExtra(StringConstants.EXTRA_DETAILS);
    }
}