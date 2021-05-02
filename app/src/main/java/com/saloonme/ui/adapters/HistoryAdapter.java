package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private boolean isHistory;

    public HistoryAdapter(Context context, ItemListener itemListener, boolean isHistory) {
        this.context = context;
        this.itemListener = itemListener;
        this.isHistory = isHistory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cancel)
        TextView tv_cancel;
        @BindView(R.id.tv_status_value)
        TextView tv_status_value;
        @BindView(R.id.tv_add_review)
        TextView tv_add_review;

        @OnClick(R.id.tv_add_review)
        void onAddReviewClick() {

        }

        @OnClick(R.id.tv_cancel)
        void onCancelClick() {

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData() {
            if (isHistory) {
                tv_add_review.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.GONE);
                tv_status_value.setText("COMPLETED");
            } else {
                tv_add_review.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.VISIBLE);
                tv_status_value.setText("CONFIRMED");
            }
        }
    }

    public interface ItemListener {
        void onAddReviewClick();

        void onCancelClick();
    }
}
