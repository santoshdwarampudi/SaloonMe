package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.saloonme.R;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.ProfileResponseData;
import com.saloonme.util.ValidationUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseAppCompactActivity {

    private ProfileResponseData profileResponseData;
    @BindView(R.id.confirm_password_tv)
    EditText confirm_password_tv;
    @BindView(R.id.password_tv)
    EditText password_tv;
    @BindView(R.id.showOrHidePwdIv)
    ImageView showOrHidePwdIv;
    @BindView(R.id.confirm_showOrHidePwdIv)
    ImageView confirm_showOrHidePwdIv;

    @OnClick(R.id.confirm_showOrHidePwdIv)
    void confirmShowPasswordClick() {

    }

    @OnClick(R.id.showOrHidePwdIv)
    void showPasswordClick() {

    }

    @OnClick(R.id.change_pwd_btn)
    void changePasswordClick() {
        if (ValidationUtil.isNullOrEmpty(password_tv.getText().toString())) {
            showToast("Please enter password");
            return;
        }
        if (ValidationUtil.isNullOrEmpty(confirm_password_tv.getText().toString())) {
            showToast("Please enter confirm password");
            return;
        }
        if (!password_tv.getText().toString().equals(confirm_password_tv.getText().toString())) {
            showToast("Passwords did not match");
            return;
        }
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileResponseData = (ProfileResponseData) getIntent().getSerializableExtra(StringConstants.EXTRA_DETAILS);
        if (profileResponseData == null) {
            showToast("Something went wrong");
            finish();
        }
    }
}