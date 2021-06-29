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
import com.saloonme.model.response.PromotionsResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrendingListAdapter extends RecyclerView.Adapter<TrendingListAdapter.ViewHolder> {
    private Context context;
    private TrendingItemListener itemListener;
    private List<PromotionsResponseData> promotionsResponseDataList;

    public TrendingListAdapter(Context context, TrendingItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<PromotionsResponseData> promotionsResponseDataList) {
        this.promotionsResponseDataList = promotionsResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrendingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_list_item, parent, false);
        return new TrendingListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingListAdapter.ViewHolder holder, int position) {
        holder.setData(promotionsResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return promotionsResponseDataList != null ? promotionsResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;
        @BindView(R.id.tv_deals)
        TextView tv_deals;
        @BindView(R.id.tv_offer)
        TextView tv_offer;


        @OnClick(R.id.card_menu)
        void onTrendingClick() {
            if (itemListener != null) {
                //itemListener.onTrendingClick();
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(PromotionsResponseData promotionsResponseData) {
            tv_deals.setText(promotionsResponseData.getPromoTitle());
            tv_offer.setText(promotionsResponseData.getDescription());
            Glide.with(context).load( promotionsResponseData.getPromoImage())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
        }
    }

    public interface TrendingItemListener {
        void onTrendingClick();
    }
}
