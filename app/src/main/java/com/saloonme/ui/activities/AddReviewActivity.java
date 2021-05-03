package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.interfaces.IRatingView;
import com.saloonme.model.request.ReviewRequest;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.ReviewPresenter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AddReviewActivity extends BaseAppCompactActivity implements IRatingView {
    private ReviewPresenter reviewPresenter;
    private String bookingId, saloonId;
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
        if (ValidationUtil.isNullOrEmpty(comments_et.getText().toString())) {
            showToast("Please enter review to submit");
        } else {
            ReviewRequest reviewRequest = new ReviewRequest();
            reviewRequest.setBookingId(bookingId);
            reviewRequest.setComments(comments_et.getText().toString());
            reviewRequest.setRating(review_rating.getRating() + "");
            reviewRequest.setSalonId(saloonId);
            reviewRequest.setUserId(PrefUtils.getInstance().getUserId());
            reviewPresenter.addReview(reviewRequest);
        }
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_add_review;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Write a Review");
        reviewPresenter = new ReviewPresenter(APIClient.getAPIService(), this);
    }

    @Override
    public void onAddReviewSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("failed to add the review");
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
    public void onAddReviewFailed() {
        showToast("failed to add the review");
    }
}