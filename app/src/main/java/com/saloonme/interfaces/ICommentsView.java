package com.saloonme.interfaces;

import com.saloonme.model.response.AddCommentResponse;
import com.saloonme.model.response.CommentListResponse;
import com.saloonme.model.response.FavouriteResponse;
import com.saloonme.model.response.FeedComment;
import com.saloonme.model.response.FeedListResponse;

import java.util.List;

public interface ICommentsView extends IActivityBaseView{
    void commentListFetchedSuccess(List<FeedComment> feedCommentList);

    void commentListFetchedFailed();

    void addCommentSuccess(AddCommentResponse addCommentResponse);

    void addCommentFailed();
}
