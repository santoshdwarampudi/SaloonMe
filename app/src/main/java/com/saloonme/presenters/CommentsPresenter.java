package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.IClickAndShareView;
import com.saloonme.interfaces.ICommentsView;
import com.saloonme.model.response.AddCommentResponse;
import com.saloonme.model.response.CommentsResponse;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsPresenter {
    private ApiInterface apiInterface;
    private ICommentsView iCommentsView;

    public CommentsPresenter(ApiInterface apiInterface, ICommentsView iCommentsView) {
        this.apiInterface = apiInterface;
        this.iCommentsView = iCommentsView;
    }

    public void getComments(String feedSno) {
        iCommentsView.showProgressDialog("Getting Comments....");
        Call<CommentsResponse> commentsResponseCall = apiInterface.getUserCommentss(feedSno);
        commentsResponseCall.enqueue(new Callback<CommentsResponse>() {
            @Override
            public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {
                iCommentsView.dismissProgress();
                iCommentsView.commentListFetchedSuccess(response.body().getData().getFeedComments());
            }

            @Override
            public void onFailure(Call<CommentsResponse> call, Throwable t) {
                iCommentsView.dismissProgress();
                iCommentsView.commentListFetchedFailed();
            }
        });
    }


    public void addComment(String feedSno,String feedComment,String commentReplayId,String userId) {
        iCommentsView.showProgressDialog("Adding Comment....");
        Call<AddCommentResponse> addCommentResponseCall = apiInterface.addComment(feedSno,feedComment,commentReplayId, userId);
        addCommentResponseCall.enqueue(new Callback<AddCommentResponse>() {
            @Override
            public void onResponse(Call<AddCommentResponse> call, Response<AddCommentResponse> response) {
                iCommentsView.dismissProgress();
                iCommentsView.addCommentSuccess(response.body());
            }

            @Override
            public void onFailure(Call<AddCommentResponse> call, Throwable t) {
                iCommentsView.dismissProgress();
                iCommentsView.addCommentFailed();
            }
        });
    }
}
