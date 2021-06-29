package com.saloonme.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.saloonme.R;
import com.saloonme.interfaces.ICartDetails;
import com.saloonme.model.request.PlaceOrderRequest;
import com.saloonme.model.response.BaseResponse;
import com.saloonme.model.response.ViewCartResponseData;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.CartDetailsPresenter;
import com.saloonme.presenters.ProductViewPresenter;
import com.saloonme.ui.adapters.CartAdapter;
import com.saloonme.ui.adapters.CommentsAdapter;
import com.saloonme.util.PrefUtils;
import com.saloonme.util.ValidationUtil;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class CartActivity extends BaseAppCompactActivity implements ICartDetails, PaymentResultListener {
    private static final String TAG = "CartActivity";
    @BindView(R.id.cart_rv)
    RecyclerView cartRv;
    private CartDetailsPresenter cartDetailsPresenter;
    String order_id;
    private CartAdapter cartAdapter;
    private String grand_total = "";


    @OnClick(R.id.check_out)
    void onCheckoutClick() {
        startPayment();
    }

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

        order_id = getIntent().getStringExtra("order_id");
        if (order_id != null)
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

    private void startPayment() {
        final Activity activity = this;

        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "SaloonMe");
            options.put("description", "Payment");
            options.put("send_sms_hash", true);
            options.put("allow_rotation", true);
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            // double amount = Double.parseDouble(tv_total_payment_value.getText().toString());
            options.put("amount", 100);

            JSONObject preFill = new JSONObject();
            preFill.put("email", PrefUtils.getInstance().geEmailId());
            preFill.put("contact", PrefUtils.getInstance().getMobileNumber());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onCartViewSuccess(ViewCartResponseData viewCartResponseData) {
        cartAdapter.setData(viewCartResponseData);
        if (viewCartResponseData != null && viewCartResponseData.getOrderDetails() != null &&
                viewCartResponseData.getOrderDetails().size() > 0) {
            grand_total = viewCartResponseData.getOrderDetails().get(0).getGrandTotal();
        }

    }

    @Override
    public void onCartViewFailed() {

    }

    @Override
    public void onPlaceOrderSuccess(BaseResponse baseResponse) {
        showToast(baseResponse.getMessage());
    }

    @Override
    public void onPlaceOrderFailed() {

    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " +
                    razorpayPaymentID, Toast.LENGTH_SHORT).show();
            cartDetailsPresenter.placeOrder(PrefUtils.getInstance().getUserId(), grand_total);
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}