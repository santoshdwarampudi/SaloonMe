package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.saloonme.R;
import com.saloonme.interfaces.IFeedUploadView;
import com.saloonme.model.response.FeedUploadResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.FeedPresenter;
import com.saloonme.presenters.RegisterPresenter;
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

public class FeedUploadActivity extends BaseAppCompactActivity implements AdapterView.OnItemSelectedListener, IFeedUploadView {

    private FeedPresenter feedPresenter;
    @BindView(R.id.postEt)
    EditText postEt;
    @BindView(R.id.nameEt)
    EditText nameEt;
    @BindView(R.id.attachTv)
    TextView attachTv;
    @BindView(R.id.postTv)
    TextView postTv;
    @BindView(R.id.visibilitySpinner)
    Spinner visibilitySpinner;
    @BindView(R.id.seletedImage)
    ImageView seletedImage;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    private MultipartBody.Part mImage;
    String visible="";
    String[] visibility = { "Public", "Private"};
    @Override
    public int getActivityLayout() {
        return R.layout.activity_feed_upload;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tv_heading.setText("Add Feed");
        visibilitySpinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,visibility);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        visibilitySpinner.setAdapter(aa);
        nameEt.setText(PrefUtils.getInstance().getUserName());

        feedPresenter = new FeedPresenter(APIClient.getAPIService(), this);
    }

    @OnClick(R.id.attachTv)
    void onAttachClick() {
        FilePickerBottomSheetFragment.start(
                (file, uri, compressedFilePath, mimeType, extension) -> {
                    mImage = MultipartBody.Part.createFormData(
                            "feed_img",
                            String.format(Locale.US, "file%s", extension),
                            RequestBody.create(MediaType.parse(mimeType),
                                    new File(compressedFilePath)));
                    attachTv.setText("Attachment added");
                    seletedImage.setVisibility(View.VISIBLE);
                    seletedImage.setImageBitmap(BitmapFactory.decodeFile(compressedFilePath));
                }).display(getSupportFragmentManager());
    }

    @OnClick(R.id.postTv)
    void onPostTvClick(){
        if (ValidationUtil.isNullOrEmpty(nameEt.getText().toString())) {
            showToast("Please enter name");
        }else if (ValidationUtil.isNullOrEmpty(postEt.getText().toString())){
            showToast("Please enter message");
        }else{
            if (visible.equalsIgnoreCase("Public")){
                feedPresenter.uploadFeedData(nameEt.getText().toString(), "",postEt.getText().toString(),
                        PrefUtils.getInstance().getUserId(),mImage,"2");
            }else{
                feedPresenter.uploadFeedData(nameEt.getText().toString(), "",postEt.getText().toString(),
                        PrefUtils.getInstance().getUserId(),mImage,"1");
            }

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        visible=visibilitySpinner.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onFeedUploadSuccess(FeedUploadResponse feedUploadResponse) {

        Toast.makeText(this, feedUploadResponse.getMessage(), Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onFeedUploadFailed() {

    }
}