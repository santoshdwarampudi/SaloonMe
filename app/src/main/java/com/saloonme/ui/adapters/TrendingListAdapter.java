package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrendingListAdapter extends RecyclerView.Adapter<TrendingListAdapter.ViewHolder> {
    private Context context;
    private TrendingItemListener itemListener;
    private boolean showBookNowOption;

    public TrendingListAdapter(Context context, TrendingItemListener itemListener, boolean showBookNowOption) {
        this.context = context;
        this.itemListener = itemListener;
        this.showBookNowOption = showBookNowOption;
    }

    @NonNull
    @Override
    public TrendingListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_list_item, parent, false);
        return new TrendingListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TrendingListAdapter.ViewHolder holder, int position) {
    }
    @Override
    public int getItemCount() {
        return 7;
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
                itemListener.onTrendingClick();
            }
        }
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface TrendingItemListener {
        void onTrendingClick();
    }
}
