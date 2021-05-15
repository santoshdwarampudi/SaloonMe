package com.saloonme.interfaces;

import com.saloonme.model.response.RemoveCartResponse;

public interface IEditProfileView extends IActivityBaseView {
    void onEditProfileSuccess(RemoveCartResponse removeCartResponse);

    void onEditProfileFailed();
}
