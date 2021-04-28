package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IRegisterView;
import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.RegisterPresenter;
import com.saloonme.util.ValidationUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseAppCompactActivity implements IRegisterView {
    private RegisterPresenter registerPresenter;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.firstName_tv)
    EditText firstName_tv;
    @BindView(R.id.lastName_tv)
    EditText lastName_tv;
    @BindView(R.id.email_tv)
    EditText email_tv;
    @BindView(R.id.phoneNumber_tv)
    EditText phoneNumber_tv;
    @BindView(R.id.password_tv)
    EditText password_tv;
    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.register_btn)
    void onRegClick() {
        if (ValidationUtil.isNullOrEmpty(firstName_tv.getText().toString())) {
            showToast("Please enter first name");
        } else if (ValidationUtil.isNullOrEmpty(lastName_tv.getText().toString())) {
            showToast("Please enter last name");
        } else if (ValidationUtil.isNullOrEmpty(email_tv.getText().toString())) {
            showToast("Please enter email");
        } else if (ValidationUtil.isNullOrEmpty(phoneNumber_tv.getText().toString())) {
            showToast("Please enter phone number");
        } else if (ValidationUtil.isNullOrEmpty(password_tv.getText().toString())) {
            showToast("Please enter password");
        } else {
            RegisterRequest registerRequest = new RegisterRequest();
            registerRequest.setEmailAddress(email_tv.getText().toString());
            registerRequest.setFirstName(firstName_tv.getText().toString());
            registerRequest.setMobileNumber(phoneNumber_tv.getText().toString());
            registerRequest.setPassword(password_tv.getText().toString());
            registerPresenter.registerUser(firstName_tv.getText().toString(), lastName_tv.getText()
                            .toString(), password_tv.getText().toString(), email_tv.getText().toString(),
                    password_tv.getText().toString());
        }
    }

    @OnClick(R.id.login)
    void onLoginClick() {
        goToActivity(LoginActivity.class);
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("REGISTER");
        registerPresenter = new RegisterPresenter(APIClient.getAPIService(), this);
    }

    @Override
    public void onRegisterSuccess(RegisterResponse registerResponse) {
        if (registerResponse != null) {
            showToast(registerResponse.getMessage());
            goToActivity(LoginRequest.class);
            finish();
        } else {
            showToast("Failed to register,try again");
        }

    }

    @Override
    public void onRegisterFailed() {

    }
}