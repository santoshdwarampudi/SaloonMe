package com.saloonme.ui.activities;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.interfaces.IEditProfileView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.ProfileResponseData;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.EditProfilePresenter;
import com.saloonme.ui.fragments.FilePickerBottomSheetFragment;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.io.File;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProfileActivity extends BaseAppCompactActivity implements IEditProfileView {
    private EditProfilePresenter editProfilePresenter;
    private MultipartBody.Part mImage;
    private ProfileResponseData profileResponseData;
    @BindView(R.id.firstName_tv)
    EditText firstName_tv;
    @BindView(R.id.lastName_tv)
    EditText lastName_tv;
    @BindView(R.id.email_tv)
    EditText email_tv;
    @BindView(R.id.phoneNumber_tv)
    EditText phoneNumber_tv;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.iv_profile)
    ImageView iv_profile;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.iv_profile)
    void onProfileClick() {
        FilePickerBottomSheetFragment.start(
                (file, uri, compressedFilePath, mimeType, extension) -> {
                    mImage = MultipartBody.Part.createFormData(
                            "profile_pic",
                            String.format(Locale.US, "file%s", extension),
                            RequestBody.create(MediaType.parse(mimeType),
                                    new File(compressedFilePath)));
                    iv_profile.setImageBitmap(BitmapFactory.decodeFile(compressedFilePath));
                }).display(getSupportFragmentManager());
    }

    @OnClick(R.id.update_btn)
    void onUpdateClick() {
        if (ValidationUtil.isNullOrEmpty(firstName_tv.getText().toString())) {
            showToast("Please enter First Name");
        } else if (ValidationUtil.isNullOrEmpty(lastName_tv.getText().toString())) {
            showToast("Please enter Last Name");
        } else if (ValidationUtil.isNullOrEmpty(email_tv.getText().toString())) {
            showToast("Please enter Email");
        } else if (ValidationUtil.isNullOrEmpty(phoneNumber_tv.getText().toString())) {
            showToast("Please enter Mobile Number");
        } else {
            editProfilePresenter.updateProfile(firstName_tv.getText().toString(),
                    lastName_tv.getText().toString(), email_tv.getText().toString(),
                    phoneNumber_tv.getText().toString(), PrefUtils.getInstance().getUserId(),
                    mImage);
        }
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_edit_profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Edit Profile");
        profileResponseData = (ProfileResponseData) getIntent().getSerializableExtra
                (StringConstants.EXTRA_DETAILS);
        if (profileResponseData == null) {
            showToast("Something went wrong");
            finish();
        }
        updateViewData(profileResponseData);
        editProfilePresenter = new EditProfilePresenter(APIClient.getAPIService(),
                this);
    }

    private void updateViewData(ProfileResponseData profileResponseData) {
        firstName_tv.setText(profileResponseData.getFirstName());
        lastName_tv.setText(profileResponseData.getLastName());
        email_tv.setText(profileResponseData.getEmailAddress());
        phoneNumber_tv.setText(profileResponseData.getMobileNumber());
        if (!ValidationUtil.isNullOrEmpty(profileResponseData.getProfilePic())) {
            Glide.with(this).load(profileResponseData.getProfilePic())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_profile);
        }

    }

    @Override
    public void onEditProfileSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to update the profile");
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
    public void onEditProfileFailed() {
        showToast("Failed to update the profile");
    }
}