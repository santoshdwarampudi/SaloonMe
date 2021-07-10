package com.saloonme.interfaces;

import com.saloonme.model.response.BlogDetailsResponse;
import com.saloonme.model.response.TrendingHairStyleResponse;

public interface IBlogDetailsView extends IActivityBaseView {
    void onBlogDetailsSuccess(BlogDetailsResponse blogDetailsResponse);

    void onBlogDetailsFailed();

    void getTrendingHairStyleSuccess(TrendingHairStyleResponse trendingHairStyleResponse);

    void getTrendingHairStyleFailed();
}
