package com.saloonme.ui.adapters;

import android.content.Context;
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
import com.saloonme.model.response.SliderResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private Context context;
    private String imagePath;
    private ItemListener itemListener;
    private List<SliderResponseData> saloonDetailsImageResponseDataList;

    public SliderAdapter(Context context, String imagePath, ItemListener itemListener) {
        this.context = context;
        this.imagePath = imagePath;
        this.itemListener = itemListener;
    }

    public void setData(List<SliderResponseData> saloonDetailsImageResponseDataList) {
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
        return saloonDetailsImageResponseDataList != null ? saloonDetailsImageResponseDataList.size() : 0;
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

        public void setData(SliderResponseData saloonDetailsImageResponseData) {
            Glide.with(context).load(
                    saloonDetailsImageResponseData.getImagePath())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_banner);
        }

    }

    public interface ItemListener {
        void onHorizontalItemClick();
    }
}
