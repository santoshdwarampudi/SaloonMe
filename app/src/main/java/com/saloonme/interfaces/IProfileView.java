package com.saloonme.interfaces;

import com.saloonme.model.response.ProfileResponse;

public interface IProfileView extends IActivityBaseView {
    void getProfileDetailsSuccess(ProfileResponse profileResponse);

    void getProfileDetailsFailed();
}
