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

public class HomeServicesAdapter extends RecyclerView.Adapter<HomeServicesAdapter.ViewHolder> {
    private Context context;
    private SaloonListAdapter.ItemListener itemListener;
    private boolean showBookNowOption;

    public HomeServicesAdapter(Context context, SaloonListAdapter.ItemListener itemListener, boolean showBookNowOption) {
        this.context = context;
        this.itemListener = itemListener;
        this.showBookNowOption = showBookNowOption;
    }

    @NonNull
    @Override
    public HomeServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_services_item, parent, false);
        return new HomeServicesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServicesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
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

    }

    public interface ItemListener {
        void onItemClick();
    }
}

