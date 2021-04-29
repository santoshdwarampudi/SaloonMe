package com.saloonme.presenters;

import com.saloonme.interfaces.ApiInterface;
import com.saloonme.interfaces.ISaloonServiceView;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaloonServicePresenter {
    private ApiInterface apiInterface;
    private ISaloonServiceView iSaloonServiceView;

    public SaloonServicePresenter(ApiInterface apiInterface, ISaloonServiceView iSaloonServiceView) {
        this.apiInterface = apiInterface;
        this.iSaloonServiceView = iSaloonServiceView;
    }

    public void getSaloonMainServices() {
        iSaloonServiceView.showProgressDialog("Getting Services...");
        Call<SaloonServiceResponse> saloonServiceResponseCall = apiInterface.getSaloonServices();
        saloonServiceResponseCall.enqueue(new Callback<SaloonServiceResponse>() {
            @Override
            public void onResponse(Call<SaloonServiceResponse> call, Response<SaloonServiceResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonServiceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonServiceResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonServiceFailed();
            }
        });
    }

    public void getSaloonSubServices(String saloonId, String serviceId) {
        iSaloonServiceView.showProgressDialog("Getting Sub Services...");
        Call<SaloonSubServiceResponse> saloonSubServiceResponseCall = apiInterface.
                getSubServices(saloonId, serviceId);
        saloonSubServiceResponseCall.enqueue(new Callback<SaloonSubServiceResponse>() {
            @Override
            public void onResponse(Call<SaloonSubServiceResponse> call, Response<SaloonSubServiceResponse> response) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonSubServiceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SaloonSubServiceResponse> call, Throwable t) {
                iSaloonServiceView.dismissProgress();
                iSaloonServiceView.saloonSubServiceFailed();
            }
        });
    }
}
