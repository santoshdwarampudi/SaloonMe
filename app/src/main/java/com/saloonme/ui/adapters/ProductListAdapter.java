package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.model.response.ProductsResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter {
    private Context context;
    private List<ProductsResponseData> productsResponseDataList;
    private ProductListItemListener productListItemListener;

    public ProductListAdapter(Context context, List<ProductsResponseData> productsResponseDataList, ProductListItemListener productListItemListener) {
        this.context = context;
        this.productsResponseDataList = productsResponseDataList;
        this.productListItemListener = productListItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product)
        ImageView iv_product;
        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_product_brand_name)
        TextView tv_product_brand_name;
        @BindView(R.id.tv_product_price)
        TextView tv_product_price;
        @BindView(R.id.tv_product_discount_price)
        TextView tv_product_discount_price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ProductListItemListener {
        void onProductClick(ProductsResponseData productsResponseData);

        void onAddClick(ProductsResponseData productsResponseData);

        void onRemoveClick(ProductsResponseData productsResponseData);
    }
}
