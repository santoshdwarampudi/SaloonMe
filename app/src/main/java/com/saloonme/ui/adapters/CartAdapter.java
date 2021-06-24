package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saloonme.R;
import com.saloonme.model.response.CartResponseData;
import com.saloonme.model.response.FeedComment;
import com.saloonme.model.response.ViewCartResponseData;
import com.saloonme.ui.activities.ProductViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private Context mContext;
    private ViewCartResponseData cartResponseData;
    public CartAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public void setData(ViewCartResponseData cartResponseDataList) {
        this.cartResponseData = cartResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(cartResponseData);
    }

    @Override
    public int getItemCount() {
        return cartResponseData!= null ? cartResponseData.getOrderDetails().size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cart_item_name)
        TextView cart_item_name;
        @BindView(R.id.cart_item_mrp)
        TextView cart_item_mrp;
        @BindView(R.id.cart_item_price)
        TextView cart_item_price;
        @BindView(R.id.cart_item_status)
        TextView cart_item_status;
        @BindView(R.id.cart_item_quantity)
        TextView cart_item_quantity;
        @BindView(R.id.cart_item_image)
        ImageView cart_item_image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(ViewCartResponseData cartResponseData) {

            cart_item_quantity.setText(cartResponseData.getCartItems().get(getAdapterPosition()).getProductQuantity());
            cart_item_status.setText(cartResponseData.getOrderDetails().get(getAdapterPosition()).getOrderStatus());
            cart_item_mrp.setText(cartResponseData.getCartItems().get(getAdapterPosition()).getProductPrice());
            cart_item_price.setText(cartResponseData.getOrderDetails().get(getAdapterPosition()).getGrandTotal());
            cart_item_name.setText(cartResponseData.getCartItems().get(getAdapterPosition()).getProductName());
        }

    }
}
