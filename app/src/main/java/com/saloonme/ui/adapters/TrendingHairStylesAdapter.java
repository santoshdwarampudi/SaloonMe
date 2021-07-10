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
import com.saloonme.model.response.TrendingHairStyleResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrendingHairStylesAdapter extends RecyclerView.Adapter<TrendingHairStylesAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<TrendingHairStyleResponseData> trendingHairStyleResponseDataList;

    public TrendingHairStylesAdapter(Context context, ItemListener itemListener, List<TrendingHairStyleResponseData> trendingHairStyleResponseDataList) {
        this.context = context;
        this.itemListener = itemListener;
        this.trendingHairStyleResponseDataList = trendingHairStyleResponseDataList;
    }

    public void setData(List<TrendingHairStyleResponseData> trendingHairStyleResponseDataList) {
        this.trendingHairStyleResponseDataList = trendingHairStyleResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_hair_style_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(trendingHairStyleResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return trendingHairStyleResponseDataList != null ? trendingHairStyleResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;
        @BindView(R.id.tv_saloon)
        TextView tv_saloon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(TrendingHairStyleResponseData trendingHairStyleResponseData) {
            Glide.with(context).load(trendingHairStyleResponseData.getTrenHairImgs())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
            tv_saloon.setText(trendingHairStyleResponseData.getTrenHairName());
        }
    }


    public interface ItemListener {
        void onTrendingHairStyleItemClick(TrendingHairStyleResponseData trendingHairStyleResponseData);
    }
}
