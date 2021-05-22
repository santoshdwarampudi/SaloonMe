package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
import com.saloonme.interfaces.ISaloonServiceView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.request.AddCartRequest;
import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.SaloonServicePresenter;
import com.saloonme.ui.activities.BookActivity;
import com.saloonme.ui.adapters.CategoryFilterAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FragmentCategory extends BaseFragment implements ISaloonServiceView,
        CategoryFilterAdapter.ServiceClickListener {

    public static final String SALOON_ID = "saloonId";
    public static final String SERVICE_ID = "serviceId";
    @BindView(R.id.filterRv)
    RecyclerView filterRv;
    private CategoryFilterAdapter categoryFilterAdapter;
    private SaloonServicePresenter saloonServicePresenter;
    private String saloonId, serviceId;
    private boolean isBookNowClicked;
    private View view;
    private int positionToUpadte;
    private List<SaloonSubServiceResponseData> saloonSubServiceResponseData;

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
        if (getArguments() != null) {
            saloonId = getArguments().getString(SALOON_ID);
            serviceId = getArguments().getString(SERVICE_ID);
        }
        initRecyclerview();
        if (!ValidationUtil.isNullOrEmpty(saloonId) && !ValidationUtil.isNullOrEmpty(serviceId))
            saloonServicePresenter.getSaloonSubServices(saloonId, serviceId);
        return view;
    }

    private void initRecyclerview() {

        categoryFilterAdapter = new CategoryFilterAdapter(getContext(), this);
        LinearLayoutManager categoryFilterListManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        filterRv.setLayoutManager(categoryFilterListManager);
        filterRv.setAdapter(categoryFilterAdapter);
        filterRv.setNestedScrollingEnabled(false);
        List<String> serviceIds = PrefUtils.getInstance().
                getCartList(PrefUtils.getInstance().getUserId());
        categoryFilterAdapter.setServiceIdsList(serviceIds);
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
        saloonSubServiceResponseData = saloonSubServiceResponse.getData();
        categoryFilterAdapter.setData(saloonSubServiceResponse.getData());
    }

    @Override
    public void saloonSubServiceFailed() {
        showToast("Failed to get the sub services");
    }

    @Override
    public void addToCartSuccess(AddCartResponse addCartResponse) {
        if (addCartResponse == null) {
            showToast("Failed to add to cart");
            return;
        }
        if (addCartResponse.getData() == null) {
            showToast(addCartResponse.getMessage());
            return;
        }
        if (addCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(addCartResponse.getMessage());
            return;
        }
        if (isBookNowClicked) {
            Bundle bundle = new Bundle();
            bundle.putString(StringConstants.EXTRA_DETAILS, saloonId);
            goToActivity(BookActivity.class, bundle);
            return;
        }
        showToast(addCartResponse.getMessage());
        saloonSubServiceResponseData.get(positionToUpadte).setAddedToCart(true);
        categoryFilterAdapter.notifyItemChanged(positionToUpadte);
        List<String> subServiceIdsList = PrefUtils.getInstance().getCartList
                (PrefUtils.getInstance().getUserId());
        if (subServiceIdsList == null) {
            subServiceIdsList = new ArrayList<>();
        }
        if (!subServiceIdsList.contains(
                saloonSubServiceResponseData.get(positionToUpadte).getServiceId()))
            subServiceIdsList.add(saloonSubServiceResponseData.get(positionToUpadte).getServiceId());
        PrefUtils.getInstance().saveCartDetails(subServiceIdsList,
                PrefUtils.getInstance().getUserId());

    }

    @Override
    public void addToCartFailed() {

        showToast("Failed to add to cart");
    }

    @Override
    public void removeCartSuccess(RemoveCartResponse removeCartResponse) {
        if (removeCartResponse == null) {
            showToast("Failed to remove from cart");
            return;
        }
        if (removeCartResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(removeCartResponse.getMessage());
            return;
        }
        showToast(removeCartResponse.getMessage());
        saloonSubServiceResponseData.get(positionToUpadte).setAddedToCart(false);
        categoryFilterAdapter.notifyItemChanged(positionToUpadte);
        List<String> subServiceIdsList = PrefUtils.getInstance().getCartList
                (PrefUtils.getInstance().getUserId());
        if (subServiceIdsList != null && subServiceIdsList.size() > 0 &&
                subServiceIdsList.contains(saloonSubServiceResponseData.
                        get(positionToUpadte).getServiceId())) {
            subServiceIdsList.remove(saloonSubServiceResponseData.
                    get(positionToUpadte).getServiceId());
        }
        PrefUtils.getInstance().saveCartDetails(subServiceIdsList,
                PrefUtils.getInstance().getUserId());
    }

    @Override
    public void removeCartFailed() {
        showToast("Failed to remove from cart");
    }

    @Override
    public void onAddToCartClicked(SaloonSubServiceResponseData saloonSubServiceResponseData,
                                   int position) {
        positionToUpadte = position;
        AddCartRequest addCartRequest = new AddCartRequest();
        addCartRequest.setSalonId(saloonId);
        addCartRequest.setUserId(PrefUtils.getInstance().getUserId());
        List<String> subServiceId = new ArrayList<>();
        subServiceId.add(saloonSubServiceResponseData.getServiceId());
        addCartRequest.setSubServiceId(subServiceId);
        saloonServicePresenter.addToCart(addCartRequest);
    }

    @Override
    public void onRemoveToCartClicked(SaloonSubServiceResponseData saloonSubServiceResponseData,
                                      int position) {
        positionToUpadte = position;
        saloonServicePresenter.removeCart(PrefUtils.getInstance().getUserId(),
                saloonSubServiceResponseData.getServiceId());
    }

    @Override
    public void onBookNowClicked(SaloonSubServiceResponseData saloonSubServiceResponseData,
                                 int position) {
        isBookNowClicked = true;
        positionToUpadte = position;
        AddCartRequest addCartRequest = new AddCartRequest();
        addCartRequest.setSalonId(saloonId);
        addCartRequest.setUserId(PrefUtils.getInstance().getUserId());
        List<String> subServiceId = new ArrayList<>();
        subServiceId.add(saloonSubServiceResponseData.getServiceId());
        addCartRequest.setSubServiceId(subServiceId);
        saloonServicePresenter.addToCart(addCartRequest);
    }
}