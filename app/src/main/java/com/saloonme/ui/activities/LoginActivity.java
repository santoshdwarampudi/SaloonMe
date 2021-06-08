package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.ILoginView;
import com.saloonme.interfaces.IProfileView;
import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.response.LoginResponse;
import com.saloonme.model.response.ProfileResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.UserBookingDetailsResponse;
import com.saloonme.model.response.UserReviewsResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.LoginPresenter;
import com.saloonme.presenters.ProfilePresenter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompactActivity implements ILoginView, IProfileView {

    private LoginPresenter loginPresenter;
    private ProfilePresenter profilePresenter;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.email_tv)
    EditText email_tv;
    @BindView(R.id.password_tv)
    EditText password_tv;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.signup)
    void onRegisterClick() {
        goToActivity(RegisterActivity.class);
    }

    @OnClick(R.id.fogot_pwd)
    void onForgotPwdClick() {
        goToActivity(ForgotPasswordActivity.class);
    }

    @OnClick(R.id.login_btn)
    void onLoginClick() {
        if (ValidationUtil.isNullOrEmpty(email_tv.getText().toString())) {
            showToast("Please enter UserId");
        } else if (ValidationUtil.isNullOrEmpty(password_tv.getText().toString())) {
            showToast("Please enter Password");
        } else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassword(password_tv.getText().toString());
            loginRequest.setUserId(email_tv.getText().toString());
            loginPresenter.loginUser(email_tv.getText().toString(), password_tv.getText().toString());
        }
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("LOGIN");
        loginPresenter = new LoginPresenter(this, APIClient.getAPIService());
        profilePresenter = new ProfilePresenter(APIClient.getAPIService(), this);
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        if (loginResponse != null && loginResponse.getData() != null &&
                loginResponse.getData().size() > 0) {
            showToast(loginResponse.getMessage());
            PrefUtils.getInstance().saveIsLogin(true);
            PrefUtils.getInstance().saveUserId(loginResponse.getData().get(0).getUserId());
            PrefUtils.getInstance().saveToken(loginResponse.getData().get(0).getToken());
            profilePresenter.getProfileDetails(PrefUtils.getInstance().getUserId(),
                    PrefUtils.getInstance().geToken());
            finish();
            goToActivity(MainActivity.class);
        } else {
            showToast("Failed to login,check credentials and try again");
        }

    }

    @Override
    public void onLoginFailed() {

    }

    @Override
    public void getProfileDetailsSuccess(ProfileResponse profileResponse) {
        PrefUtils.getInstance().saveUserName(profileResponse.getData().get(0).getFirstName());
    }

    @Override
    public void getProfileDetailsFailed() {

    }

    @Override
    public void getUserBookingDetailsSuccess(UserBookingDetailsResponse userBookingDetailsResponse) {

    }

    @Override
    public void getUserBookingDetailsFailed() {

    }

    @Override
    public void cancelBookingSuccess(RemoveCartResponse removeCartResponse) {

    }

    @Override
    public void cancelBookingFailed() {

    }

    @Override
    public void reschduleBookingSuccess(RemoveCartResponse removeCartResponse) {

    }

    @Override
    public void reschduleBookingFailed() {

    }

    @Override
    public void getUserReviewsSuccess(UserReviewsResponse userReviewsResponse) {

    }

    @Override
    public void getUserReviewsFailed() {

    }
}