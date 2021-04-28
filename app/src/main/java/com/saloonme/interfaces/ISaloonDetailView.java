package com.saloonme.interfaces;

import com.saloonme.model.response.SaloonDetailsImageResponse;
import com.saloonme.model.response.SaloonReviewResponse;

public interface ISaloonDetailView extends IActivityBaseView {
    void getSaloonImageSuccess(SaloonDetailsImageResponse saloonDetailsImageResponse);

    void getSaloonImageFailed();

    void getSaloonReviewSuccess(SaloonReviewResponse saloonReviewResponse);

    void getSaloonReviewFailed();

}
