package com.saloonme.interfaces;

import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;

public interface ISaloonServiceView extends IActivityBaseView {
    void saloonServiceSuccess(SaloonServiceResponse saloonServiceResponse);

    void saloonServiceFailed();

    void saloonSubServiceSuccess(SaloonSubServiceResponse saloonSubServiceResponse);

    void saloonSubServiceFailed();
}
