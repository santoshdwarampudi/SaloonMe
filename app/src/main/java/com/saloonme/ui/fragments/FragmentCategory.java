package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
import com.saloonme.interfaces.ISaloonServiceView;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.SaloonServicePresenter;
import com.saloonme.ui.adapters.CategoryFilterAdapter;
import com.saloonme.ui.adapters.HomeServicesAdapter;
import com.saloonme.ui.adapters.OffersHorizontalAdapter;
import com.saloonme.ui.adapters.SaloonListAdapter;
import com.saloonme.ui.adapters.TrendingListAdapter;
import com.saloonme.util.CirclePagerIndicatorDecoration;

import java.util.Timer;

import butterknife.BindView;

public class FragmentCategory extends BaseFragment implements ISaloonServiceView {

    public static final String SALOON_ID = "saloonId";
    public static final String SERVICE_ID = "serviceId";
    @BindView(R.id.filterRv)
    RecyclerView filterRv;
    private CategoryFilterAdapter categoryFilterAdapter;
    private SaloonServicePresenter saloonServicePresenter;
    private String saloonId, serviceId;
    private View view;

    public static FragmentCategory newInstance(String saloonId, String serviceId) {
        Bundle args = new Bundle();
        args.putString(SALOON_ID, saloonId);
        args.putString(SERVICE_ID, serviceId);
        FragmentCategory fragment = new FragmentCategory();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_category;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        saloonServicePresenter = new SaloonServicePresenter(APIClient.getAPIService(),
                this);
        initRecyclerview();
        if (getArguments() != null) {
            saloonId = getArguments().getString(SALOON_ID);
            serviceId = getArguments().getString(SERVICE_ID);
            saloonServicePresenter.getSaloonSubServices(saloonId, serviceId);
        }
        return view;
    }

    private void initRecyclerview() {

        categoryFilterAdapter = new CategoryFilterAdapter(getContext());
        LinearLayoutManager categoryFilterListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        filterRv.setLayoutManager(categoryFilterListManager);
        filterRv.setAdapter(categoryFilterAdapter);
        filterRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void saloonServiceSuccess(SaloonServiceResponse saloonServiceResponse) {

    }

    @Override
    public void saloonServiceFailed() {

    }

    @Override
    public void saloonSubServiceSuccess(SaloonSubServiceResponse saloonSubServiceResponse) {
        if (saloonSubServiceResponse == null) {
            showToast("Failed to get the sub services");
            return;
        }
        if (saloonSubServiceResponse.getStatus().contains("fail")) {
            showToast(saloonSubServiceResponse.getMessage());
            return;
        }
        if (saloonSubServiceResponse.getData() == null || saloonSubServiceResponse.getData().size() == 0) {
            showToast(saloonSubServiceResponse.getMessage());
            return;
        }
        categoryFilterAdapter.setData(saloonSubServiceResponse.getData());
    }

    @Override
    public void saloonSubServiceFailed() {
        showToast("Failed to get the sub services");
    }
}