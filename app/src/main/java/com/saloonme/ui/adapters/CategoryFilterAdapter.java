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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.model.response.SaloonSubServiceResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder> {
    private Context context;
    private List<SaloonSubServiceResponseData> saloonSubServiceResponseDataList;
    private List<String> serviceIds;
    private ServiceClickListener serviceClickListener;

    public CategoryFilterAdapter(Context context, ServiceClickListener serviceClickListener) {
        this.context = context;
        this.serviceClickListener = serviceClickListener;
    }

    public void setData(List<SaloonSubServiceResponseData> saloonSubServiceResponseDataList) {
        this.saloonSubServiceResponseDataList = saloonSubServiceResponseDataList;
        notifyDataSetChanged();
    }

    public void setServiceIdsList(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }

    @NonNull
    @Override
    public CategoryFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_fiter_item, parent, false);
        return new CategoryFilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFilterAdapter.ViewHolder holder, int position) {
        holder.setData(saloonSubServiceResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonSubServiceResponseDataList != null ? saloonSubServiceResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_service)
        ImageView iv_service;
        @BindView(R.id.tv_service_name)
        TextView tv_service_name;
        @BindView(R.id.tv_duration)
        TextView tv_duration;
        @BindView(R.id.tv_amount)
        TextView tv_amount;
        @BindView(R.id.tv_discount)
        TextView tv_discount;
        @BindView(R.id.tv_add_cart)
        TextView tv_add_cart;
        @BindView(R.id.tv_bookNow)
        TextView tv_bookNow;

        @OnClick(R.id.tv_bookNow)
        void onBookNowClick() {
            if (serviceClickListener != null) {
                serviceClickListener.onBookNowClicked(saloonSubServiceResponseDataList.
                        get(getAdapterPosition()));
            }
        }

        @OnClick(R.id.tv_add_cart)
        void onAddCartClick() {
            if (serviceClickListener != null) {
                if (tv_add_cart.getText().toString().equalsIgnoreCase(context.getResources()
                        .getString(R.string.add_to_cart))) {
                    serviceClickListener.onAddToCartClicked(saloonSubServiceResponseDataList.
                            get(getAdapterPosition()), getAdapterPosition());
                } else {
                    serviceClickListener.onRemoveToCartClicked(saloonSubServiceResponseDataList.
                            get(getAdapterPosition()), getAdapterPosition());
                }

            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SaloonSubServiceResponseData saloonSubServiceResponseData) {
            tv_service_name.setText(saloonSubServiceResponseData.getServiceName());
            tv_duration.setText("Duration : " + saloonSubServiceResponseData.getServiceDuration());
            tv_amount.setText("Amount :" + saloonSubServiceResponseData.getSaloonPrice());
            tv_discount.setText("Discount :" + saloonSubServiceResponseData.getSaloonDiscount());
            if (saloonSubServiceResponseData.isAddedToCart()) {
                tv_add_cart.setText(R.string.remove_cart);
            } else {
                if (serviceIds != null && serviceIds.size() > 0 &&
                        serviceIds.contains(saloonSubServiceResponseData.getServiceId())) {
                    tv_add_cart.setText(R.string.remove_cart);
                } else {
                    tv_add_cart.setText(R.string.add_to_cart);
                }

            }
            Glide.with(context).load(
                    APIConstants.IMAGE_BASE_URL + saloonSubServiceResponseData.getImage())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_service);
        }
    }

    public interface ServiceClickListener {
        void onAddToCartClicked(SaloonSubServiceResponseData saloonSubServiceResponseData, int position);

        void onRemoveToCartClicked(SaloonSubServiceResponseData saloonSubServiceResponseData, int position);

        void onBookNowClicked(SaloonSubServiceResponseData saloonSubServiceResponseData);
    }

}
