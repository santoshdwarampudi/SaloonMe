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
import com.saloonme.model.response.SaloonListResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeServicesAdapter extends RecyclerView.Adapter<HomeServicesAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<SaloonListResponseData> saloonListResponseDataList;

    public HomeServicesAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<SaloonListResponseData> saloonListResponseDataList) {
        this.saloonListResponseDataList = saloonListResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_services_item, parent, false);
        return new HomeServicesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServicesAdapter.ViewHolder holder, int position) {
        holder.setData(saloonListResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonListResponseDataList != null ? saloonListResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;
        @BindView(R.id.tv_discount)
        TextView tv_discount;
        @BindView(R.id.tv_saloons_count)
        TextView tv_saloons_count;


        @OnClick(R.id.card_menu)
        void onSaloonClick() {
            if (itemListener != null) {
                itemListener.onItemClick();
            }
        }


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SaloonListResponseData saloonListResponseData) {
            Glide.with(context).load(
                    saloonListResponseData.getStoreImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
        }

    }

    public interface ItemListener {
        void onItemClick();
    }
}

