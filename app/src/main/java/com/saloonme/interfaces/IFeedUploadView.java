package com.saloonme.interfaces;


import com.saloonme.model.response.FeedUploadResponse;

public interface IFeedUploadView
    extends IActivityBaseView {
        void onFeedUploadSuccess(FeedUploadResponse feedUploadResponse);

        void onFeedUploadFailed();
}
