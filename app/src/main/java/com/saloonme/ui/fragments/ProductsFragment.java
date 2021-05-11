package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
import com.saloonme.interfaces.IProdcutListView;
import com.saloonme.model.response.ProductsResponse;

import butterknife.BindView;

public class ProductsFragment extends BaseFragment implements IProdcutListView {

    private View view;
    @BindView(R.id.rv_productItems)
    RecyclerView rv_productItems;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
    }

    @Override
    public void getProductsListSuccess(ProductsResponse productsResponse) {
        if (productsResponse == null) {
            showToast("Failed to get the products");
            return;
        }
        if (productsResponse.getStatus().toLowerCase().contains("fail")) {
            showToast(productsResponse.getMessage());
            return;
        }
        if (productsResponse.getData() == null || productsResponse.getData().size() == 0) {
            showToast(productsResponse.getMessage());
            return;
        }
    }

    @Override
    public void getProductsListFailed() {
        showToast("Failed to get the products");
    }
}