package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompactActivity {

    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.signup)
    void onRegisterClick() {
        goToActivity(RegisterActivity.class);
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("LOGIN");
    }
}