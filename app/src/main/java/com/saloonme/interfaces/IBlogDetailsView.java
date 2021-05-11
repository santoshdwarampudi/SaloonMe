package com.saloonme.interfaces;

import com.saloonme.model.response.BlogDetailsResponse;

public interface IBlogDetailsView extends IActivityBaseView {
    void onBlogDetailsSuccess(BlogDetailsResponse blogDetailsResponse);

    void onBlogDetailsFailed();
}
