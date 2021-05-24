package com.saloonme.ui.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.saloonme.R;
import com.saloonme.interfaces.ICommentsView;
import com.saloonme.model.request.ReviewRequest;
import com.saloonme.model.response.AddCommentResponse;
import com.saloonme.model.response.CommentListResponse;
import com.saloonme.model.response.FeedComment;
import com.saloonme.model.response.FeedListResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.CommentsPresenter;
import com.saloonme.presenters.ReviewPresenter;
import com.saloonme.ui.adapters.BookingDetailsAdapter;
import com.saloonme.ui.adapters.CommentsAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentsActivity extends BaseAppCompactActivity implements ICommentsView {

    @BindView(R.id.commentsRv)
    RecyclerView commentsRv;
    @BindView(R.id.newComment)
    EditText newComment;
    @BindView(R.id.icSendCommentTc)
    ImageView icSendCommentTc;
    CommentsPresenter commentsPresenter;
    CommentsAdapter commentsAdapter;
    String feedSno;
    @OnClick(R.id.iv_menu)
    void onBackClicked() {
        finish();
    }

    @Override
    public int getActivityLayout() {
        return  R.layout.activity_comments;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        commentsPresenter = new CommentsPresenter(APIClient.getAPIService(), this);
        feedSno =getIntent().getStringExtra("feed_sno");
        initRecyclerView();
        commentsPresenter.getComments(feedSno);
    }
    @OnClick(R.id.icSendCommentTc)
    void onSubmitClick() {
        if (ValidationUtil.isNullOrEmpty(newComment.getText().toString())) {
            showToast("Please enter comment to submit");
        } else {
          if (feedSno!=null && !feedSno.isEmpty() )
            commentsPresenter.addComment(feedSno,newComment.getText().toString(),"",PrefUtils.getInstance().getUserId());
        }
    }
    private void initRecyclerView() {
        commentsAdapter = new CommentsAdapter(getContext());
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        commentsRv.setLayoutManager(saloonListManager);
        commentsRv.setAdapter(commentsAdapter);
        commentsRv.setNestedScrollingEnabled(false);
    }




    @Override
    public void commentListFetchedSuccess(List<FeedComment> feedCommentList) {
        if (feedCommentList == null) {
            showToast("Failed to get the Comments...");
            return;
        }
        commentsAdapter.setData(feedCommentList);
    }

    @Override
    public void commentListFetchedFailed() {
        showToast("Failed to get the Comments");
    }

    @Override
    public void addCommentSuccess(AddCommentResponse addCommentResponse) {
        newComment.getText().clear();
        if (addCommentResponse == null) {
            showToast("failed to add the comment");
            return;
        }
        if (addCommentResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(addCommentResponse.getMessage());
            return;
        }
        showToast(addCommentResponse.getMessage());
        commentsPresenter.getComments(feedSno);
      //  finish();
    }

    @Override
    public void addCommentFailed() {

    }
}