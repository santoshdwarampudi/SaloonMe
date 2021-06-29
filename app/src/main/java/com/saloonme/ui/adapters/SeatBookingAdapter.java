package com.saloonme.ui.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.interfaces.APIConstants;
import com.saloonme.model.response.ExpertsListResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SeatBookingAdapter extends RecyclerView.Adapter<SeatBookingAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<ExpertsListResponseData> expertsListResponseDataList;

    public SeatBookingAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<ExpertsListResponseData> expertsListResponseDataList) {
        this.expertsListResponseDataList = expertsListResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.seat_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(expertsListResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return expertsListResponseDataList != null ? expertsListResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_seat)
        ImageView iv_seat;
        @BindView(R.id.iv_barber)
        ImageView iv_barber;
        @BindView(R.id.tv_barberName)
        TextView tv_barberName;
        @BindView(R.id.seat_card)
        CardView seat_card;

        @OnClick(R.id.seat_card)
        void onBarberSelect() {
            if (itemListener != null) {
                itemListener.onBarberSelect(expertsListResponseDataList.get(getAdapterPosition()), getAdapterPosition());
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(ExpertsListResponseData expertsListResponseData) {
            tv_barberName.setText(expertsListResponseData.getName());
            if (expertsListResponseData.getStatus().equalsIgnoreCase("1")) {
                iv_seat.setImageDrawable(context.getDrawable(R.drawable.chairafter));
            } else {
                iv_seat.setImageDrawable(context.getDrawable(R.drawable.chairbefore));
            }
            if (expertsListResponseData.isSelected()) {
                seat_card.setBackgroundColor(ContextCompat.getColor(context, R.color.red_bg_light));
            } else {
                seat_card.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }
            Glide.with(context).load(
                    expertsListResponseData.getProfileImage())

                    .apply(new RequestOptions().error(R.drawable.ic_person).
                            placeholder(R.drawable.ic_person)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_barber);
        }
    }

    public interface ItemListener {
        void onBarberSelect(ExpertsListResponseData expertsListResponseData, int position);
    }
}
