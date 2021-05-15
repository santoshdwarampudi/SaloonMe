package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IForgotPasswordView;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ForgotPasswordPresenter;
import com.saloonme.util.ValidationUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgotPasswordActivity extends BaseAppCompactActivity implements IForgotPasswordView {

    private ForgotPasswordPresenter forgotPasswordPresenter;
    @BindView(R.id.email_tv)
    EditText email_tv;
    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @OnClick(R.id.forgot_pwd_btn)
    void onForgotPwdClick() {
        if (ValidationUtil.isNullOrEmpty(email_tv.getText().toString())) {
            showToast("Please enter email");
            return;
        }
        forgotPasswordPresenter.forgotPassword(email_tv.getText().toString());

    }

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Forgot Password");
        forgotPasswordPresenter = new ForgotPasswordPresenter(APIClient.getAPIService(),
                this);
    }

    @Override
    public void forgotPasswordSetSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to set the password");
            return;
        }
        if (removeCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(removeCartResponse.getMessage());
            return;
        }
        showToast(removeCartResponse.getMessage());
        finish();
    }

    @Override
    public void forgotPasswordSetFailed() {
        showToast("Failed to set the password");
    }
}