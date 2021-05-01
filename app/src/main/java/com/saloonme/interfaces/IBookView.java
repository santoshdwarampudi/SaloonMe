package com.saloonme.interfaces;

import com.saloonme.model.response.ExpertsListResponse;
import com.saloonme.model.response.SaloonListResponse;

public interface IBookView extends IActivityBaseView {
    void getBarbersSuccess(ExpertsListResponse expertsListResponse);

    void getBarbersFailed();

    void getSaloonDetailsSuccess(SaloonListResponse saloonListResponse);

    void getSaloonDetailsFailed();
}
