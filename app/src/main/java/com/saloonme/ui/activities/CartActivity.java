package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.saloonme.R;
import com.saloonme.interfaces.ICartDetails;
import com.saloonme.model.response.ViewCartResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.CartDetailsPresenter;
import com.saloonme.presenters.ProductViewPresenter;
import com.saloonme.ui.adapters.CartAdapter;
import com.saloonme.ui.adapters.CommentsAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class CartActivity extends BaseAppCompactActivity implements ICartDetails {

    @BindView(R.id.cart_rv)
    RecyclerView cartRv;
    private CartDetailsPresenter cartDetailsPresenter;
    String order_id;
    private CartAdapter cartAdapter;

    @OnClick(R.id.iv_menu)
    void onBackClicked() {
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_cart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cartDetailsPresenter = new CartDetailsPresenter(APIClient.getAPIService(), this);

        order_id=getIntent().getStringExtra("order_id");
        if (order_id!=null)
            cartDetailsPresenter.cartData(order_id);
        initRecyclerView();

    }

    private void initRecyclerView() {
        cartAdapter = new CartAdapter(getContext());
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        cartRv.setLayoutManager(saloonListManager);
        cartRv.setAdapter(cartAdapter);
        cartRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void onCartViewSuccess(ViewCartResponseData viewCartResponseData) {
        cartAdapter.setData(viewCartResponseData);
    }

    @Override
    public void onCartViewFailed() {

    }
}