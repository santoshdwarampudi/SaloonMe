package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeatBookingAdapter extends RecyclerView.Adapter<SeatBookingAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;

    public SeatBookingAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seat_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seat)
        ImageView iv_seat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData() {
            if (getAdapterPosition() % 2 == 0) {
                iv_seat.setImageDrawable(context.getDrawable(R.drawable.chairafter));
            } else {
                iv_seat.setImageDrawable(context.getDrawable(R.drawable.chairbefore));
            }
        }
    }

    public interface ItemListener {

    }
}
