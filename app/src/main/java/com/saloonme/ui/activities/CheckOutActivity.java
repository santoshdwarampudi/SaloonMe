package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.saloonme.R;
import com.saloonme.ui.adapters.ProductsAdapter;
import com.saloonme.ui.adapters.ReviewsAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckOutActivity extends BaseAppCompactActivity implements ProductsAdapter.ItemListener {
    private ProductsAdapter productsAdapter;
    @BindView(R.id.rv_products)
    RecyclerView rv_products;
    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @OnClick(R.id.iv_menu)
    void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_pay)
    void onPayClick() {

    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_check_out;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv_heading.setText("Checkout");
        setUpProductsRecyclerview();
    }

    private void setUpProductsRecyclerview() {
        productsAdapter = new ProductsAdapter(getContext(), this);
        LinearLayoutManager saloonListManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv_products.setLayoutManager(saloonListManager);
        rv_products.setAdapter(productsAdapter);
        rv_products.setNestedScrollingEnabled(false);
    }
}