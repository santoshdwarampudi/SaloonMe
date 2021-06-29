package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.model.response.ProductsResponseData;
import com.saloonme.model.response.SaloonListResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{
    private Context context;
    private List<ProductsResponseData> productsResponseDataList;
    private ProductListItemListener productListItemListener;

    public ProductListAdapter(Context context, List<ProductsResponseData> productsResponseDataList, ProductListItemListener productListItemListener) {
        this.context = context;
        this.productsResponseDataList = productsResponseDataList;
        this.productListItemListener = productListItemListener;
    }

    public void setData(List<ProductsResponseData> productsResponseDataList) {
        this.productsResponseDataList = productsResponseDataList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(productsResponseDataList.get(position));
        holder.productCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productListItemListener.onProductClick(productsResponseDataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsResponseDataList != null ? productsResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product)
        ImageView iv_product;
        @BindView(R.id.tv_product_name)
        TextView tv_product_name;
        @BindView(R.id.tv_add_cart)
        TextView tv_add_cart;
        @BindView(R.id.tv_product_brand_name)
        TextView tv_product_brand_name;
        @BindView(R.id.tv_product_price)
        TextView tv_product_price;
        @BindView(R.id.tv_product_discount_price)
        TextView tv_product_discount_price;
        @BindView(R.id.productCl)
        ConstraintLayout productCl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(ProductsResponseData productsResponseData) {

            Glide.with(context).load(productsResponseData.getProdImg())
                    .placeholder(R.drawable.ic_product_place_holder)
                    .error(R.drawable.ic_product_place_holder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_product);
            tv_product_name.setText(productsResponseData.getProdName());
            tv_product_brand_name.setText(productsResponseData.getProdBrand());
            tv_product_price.setText(String.format("₹%s", productsResponseData.getProdPrice()));
            tv_product_discount_price.setText(String.format("₹%s", productsResponseData.getProdDisPrice()));
        }
    }

    public interface ProductListItemListener {
        void onProductClick(ProductsResponseData productsResponseData);

        void onAddClick(ProductsResponseData productsResponseData);

        void onRemoveClick(ProductsResponseData productsResponseData);
    }
}
