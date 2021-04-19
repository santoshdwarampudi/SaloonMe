package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseAppCompactActivity {

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
    }
}