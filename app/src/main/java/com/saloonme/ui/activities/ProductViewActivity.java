package com.saloonme.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.saloonme.R;
import com.saloonme.interfaces.IProductView;
import com.saloonme.model.response.CartResponseData;
import com.saloonme.model.response.ProductViewResponse;
import com.saloonme.model.response.ProductViewResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.FeedPresenter;
import com.saloonme.presenters.ProductViewPresenter;
import com.saloonme.util.PrefUtils;

import java.util.List;

import butterknife.BindView;

public class ProductViewActivity extends BaseAppCompactActivity implements IProductView, View.OnClickListener {

    private static final String RUPEE_SYMBOL = "₹ ";
    int quantity = 0;

    @BindView(R.id.product_name)
    TextView productName;
    @BindView(R.id.mrp_value)
    TextView mrpValue;
    @BindView(R.id.price_value)
    TextView priceValue;
    @BindView(R.id.save_value)
    TextView saveValue;
    @BindView(R.id.stock_Tv)
    TextView stockTv;
    @BindView(R.id.quantity_value_Tv)
    TextView quantityValueTv;
    @BindView(R.id.decrement_Tv)
    ImageView decrementTv;
    @BindView(R.id.increment_Tv)
    ImageView incrementTv;
    @BindView(R.id.product_iv)
    ImageView productIv;
    @BindView(R.id.add_to_cart)
    TextView addToCart;
    @BindView(R.id.product_discount)
    TextView productDiscount;
    @BindView(R.id.collapsing_toolbar)
    Toolbar collapsingToolbar;
    private ProductViewPresenter productViewPresenter;
    String product_id;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_product_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setSupportActionBar(collapsingToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setNavigationOnClickListener(v -> finish());

        setTitle(null);
        productViewPresenter = new ProductViewPresenter(APIClient.getAPIService(), this);

        product_id = getIntent().getStringExtra("product_id");
        if (product_id != null)
            productViewPresenter.productData(product_id);
        incrementTv.setOnClickListener(this);
        decrementTv.setOnClickListener(this);
        addToCart.setOnClickListener(this);

    }


    @Override
    public void onProductViewSuccess(List<ProductViewResponseData> productViewResponse) {
        Log.e("token", PrefUtils.getInstance().geToken());
        if (null != productViewResponse.get(0).getProdImg())
            Glide.with(ProductViewActivity.this).
                    load(productViewResponse.get(0).getProdImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder).into(productIv);
        productName.setText(productViewResponse.get(0).getProdName());
        mrpValue.setText(RUPEE_SYMBOL + productViewResponse.get(0).getProdPrice());
        int disCountedPrice = (Integer.parseInt(productViewResponse.get(0).getProdPrice())
                * Integer.parseInt(productViewResponse.get(0).getProdDiscount())) / 100;

        priceValue.setText("" + RUPEE_SYMBOL + disCountedPrice);
        int saveRs = Integer.parseInt(productViewResponse.get(0).getProdPrice()) - disCountedPrice;
        saveValue.setText("" + RUPEE_SYMBOL + saveRs);
        stockTv.setText(productViewResponse.get(0).getProdStock());

        if (null != productViewResponse.get(0).getProdDiscount() &&
                !productViewResponse.get(0).getProdDiscount().equalsIgnoreCase("0"))
            productDiscount.setVisibility(View.VISIBLE);
        productDiscount.setText(productViewResponse.get(0).getProdDiscount() + "%");

    }

    @Override
    public void onProductViewFailed() {
        showToast("Failed to fetch the item");
    }

    @Override
    public void onAddToCartSuccess(List<CartResponseData> cartResponseData) {

        Log.e("token", PrefUtils.getInstance().geToken());
        Intent i = new Intent(getContext(), CartActivity.class);
        i.putExtra("order_id", cartResponseData.get(0).getOrderId());
        startActivity(i);
    }

    @Override
    public void onAddToCartFailed() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.increment_Tv:
                increment(incrementTv);
                break;
            case R.id.decrement_Tv:
                decrement(decrementTv);
                break;
            case R.id.add_to_cart:
                productViewPresenter.addToCart(PrefUtils.getInstance().getUserId(), product_id);
                break;
        }

    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity = quantity - 1;
            display(quantity);
        }
    }

    private void display(int number) {
        quantityValueTv.setText("" + number);
    }
}