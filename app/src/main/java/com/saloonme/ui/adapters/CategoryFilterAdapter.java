package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder> {
    private Context context;
    private boolean showBookNowOption;

    public CategoryFilterAdapter(Context context, boolean showBookNowOption) {
        this.context = context;

        this.showBookNowOption = showBookNowOption;
    }

    @NonNull
    @Override
    public CategoryFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_fiter_item, parent, false);
        return new CategoryFilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFilterAdapter.ViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemCb)
        CheckBox itemCb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            itemCb.setText("Hair Style " + (getAdapterPosition() + 1));
        }
    }

}
