package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IRatingView;

import butterknife.BindView;
import butterknife.OnClick;

public class AddReviewActivity extends BaseAppCompactActivity implements IRatingView {

    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.comments_et)
    EditText comments_et;
    @BindView(R.id.review_rating)
    RatingBar review_rating;

    @OnClick(R.id.iv_menu)
    void onBackClicked() {
        finish();
    }

    @OnClick(R.id.submit_tv)
    void onSubmitClick() {

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_add_review;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Write a Review");
    }
}