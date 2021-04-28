package com.saloonme.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.model.response.SaloonDetailsImageResponseData;
import com.saloonme.model.response.SaloonListResponseData;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OffersHorizontalAdapter extends RecyclerView.Adapter<OffersHorizontalAdapter.ViewHolder> {
    private Context context;
    private String imagePath;
    private ItemListener itemListener;
    private List<SaloonDetailsImageResponseData> saloonDetailsImageResponseDataList;

    public OffersHorizontalAdapter(Context context, String imagePath, ItemListener itemListener) {
        this.context = context;
        this.imagePath = imagePath;
        this.itemListener = itemListener;
    }

    public void setData(List<SaloonDetailsImageResponseData> saloonDetailsImageResponseDataList) {
        this.saloonDetailsImageResponseDataList = saloonDetailsImageResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productimagesmodel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (saloonDetailsImageResponseDataList != null)
            holder.setData(saloonDetailsImageResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonDetailsImageResponseDataList != null ? saloonDetailsImageResponseDataList.size() : 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView iv_banner;

        @OnClick(R.id.iv_banner)
        void onBannerClick() {
            if (itemListener != null)
                itemListener.onHorizontalItemClick();
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SaloonDetailsImageResponseData saloonDetailsImageResponseData) {
            Glide.with(context).load(APIConstants.IMAGE_BASE_URL +
                    saloonDetailsImageResponseData.getStoreImg())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_banner);
        }

    }

    public interface ItemListener {
        void onHorizontalItemClick();
    }
}
