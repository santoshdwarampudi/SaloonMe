package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IRegisterView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.request.LoginRequest;
import com.saloonme.model.request.RegisterRequest;
import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.RegisterResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.RegisterPresenter;
import com.saloonme.util.ValidationUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseAppCompactActivity implements IRegisterView {
    private RegisterPresenter registerPresenter;
    private String gender;
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
    @BindView(R.id.spinner_gender)
    Spinner spinner_gender;

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
                    phoneNumber_tv.getText().toString(), gender);
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
        tv_heading.setText(getString(R.string.register));
        registerPresenter = new RegisterPresenter(APIClient.getAPIService(), this);
        setDataToGender();
    }

    private void setDataToGender() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Male");
        arrayList.add("Female");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(arrayAdapter);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!ValidationUtil.isNullOrEmpty(parent.getItemAtPosition(position).toString())) {
                    if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("Male")) {
                        gender = "1";
                    } else {
                        gender = "2";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onRegisterSuccess(RegisterResponse registerResponse) {
        if (registerResponse != null) {
            registerPresenter.getRegisterOtp(phoneNumber_tv.getText().toString());
        } else {
            showToast("Failed to register,try again");
        }
    }

    @Override
    public void onRegisterFailed() {
        showToast("Failed to register,try again");
    }

    @Override
    public void getOtpSuccess(BaseResponse baseResponse) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EXTRA_DETAILS, baseResponse.getData());
        goToActivity(OtpVerificationActivity.class, bundle);
    }

    @Override
    public void getOtpFailed() {
        showToast("Failed to register,try again");
    }
}