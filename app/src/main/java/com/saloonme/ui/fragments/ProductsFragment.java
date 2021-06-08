package com.saloonme.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;
import com.saloonme.interfaces.IProdcutListView;
import com.saloonme.model.response.ProductsResponse;
import com.saloonme.model.response.ProductsResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.HomePresenter;
import com.saloonme.presenters.ProductsListPresenter;
import com.saloonme.ui.activities.FeedUploadActivity;
import com.saloonme.ui.activities.ProductViewActivity;
import com.saloonme.ui.adapters.ProductListAdapter;

import butterknife.BindView;

public class ProductsFragment extends BaseFragment implements IProdcutListView, ProductListAdapter.ProductListItemListener {

    private View view;
    @BindView(R.id.rv_productItems)
    RecyclerView rv_productItems;
   private ProductListAdapter productListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ProductsListPresenter productsListPresenter;
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
        productsListPresenter = new ProductsListPresenter(APIClient.getAPIService(), this);
        initRecyclerView();
        productsListPresenter.getProductList();
        return view;
    }

    private void initRecyclerView() {
        productListAdapter = new ProductListAdapter(getActivity(),null,this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,
                false);
        rv_productItems.setLayoutManager(linearLayoutManager);
        rv_productItems.setItemAnimator(new DefaultItemAnimator());
        rv_productItems.setAdapter(productListAdapter);
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

       productListAdapter.setData(productsResponse.getData());
    }

    @Override
    public void getProductsListFailed() {
        showToast("Failed to get the products");
    }

    @Override
    public void onProductClick(ProductsResponseData productsResponseData) {

        Intent i = new Intent(getContext(), ProductViewActivity.class);
        i.putExtra("product_id",productsResponseData.getProdId());
        startActivity(i);
    }

    @Override
    public void onAddClick(ProductsResponseData productsResponseData) {

    }

    @Override
    public void onRemoveClick(ProductsResponseData productsResponseData) {

    }
}