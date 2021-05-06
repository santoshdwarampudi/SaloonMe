package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.model.response.CompletedDetail;
import com.saloonme.model.response.UpcomingDetail;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private boolean isHistory;
    private List<UpcomingDetail> upcomingDetailList;
    private List<CompletedDetail> completedDetailList;

    public HistoryAdapter(Context context, ItemListener itemListener, boolean isHistory) {
        this.context = context;
        this.itemListener = itemListener;
        this.isHistory = isHistory;
    }

    public void setDataToUpComing(List<UpcomingDetail> upcomingDetailList) {
        this.upcomingDetailList = upcomingDetailList;
        notifyDataSetChanged();
    }

    public void setDataToHistory(List<CompletedDetail> completedDetailList) {
        this.completedDetailList = completedDetailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isHistory)
            holder.setDataToHistory(completedDetailList.get(position));
        else
            holder.setDataToupComing(upcomingDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        if (isHistory)
            return completedDetailList != null ? completedDetailList.size() : 0;
        else
            return upcomingDetailList != null ? upcomingDetailList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_cancel)
        TextView tv_cancel;
        @BindView(R.id.tv_status_value)
        TextView tv_status_value;
        @BindView(R.id.tv_add_review)
        TextView tv_add_review;
        @BindView(R.id.tv_confirm_id)
        TextView tv_confirm_id;
        @BindView(R.id.tv_reschdule)
        TextView tv_reschdule;
        @BindView(R.id.tv_saloon)
        TextView tv_saloon;
        @BindView(R.id.tv_booking_price_value)
        TextView tv_booking_price_value;
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_view_booking)
        TextView tv_view_booking;

        @OnClick(R.id.tv_reschdule)
        void onReschduleClick() {
            if (itemListener != null)
                itemListener.onReschduleClick(upcomingDetailList.get(getAdapterPosition()));
        }

        @OnClick(R.id.tv_view_booking)
        void onViewBooking() {
            if (itemListener != null)
                itemListener.onViewBookingClick(upcomingDetailList.get(getAdapterPosition()));
        }

        @OnClick(R.id.tv_add_review)
        void onAddReviewClick() {
            if (itemListener != null)
                itemListener.onAddReviewClick(completedDetailList.get(getAdapterPosition()));
        }

        @OnClick(R.id.tv_cancel)
        void onCancelClick() {
            if (itemListener != null)
                itemListener.onCancelClick(upcomingDetailList.get(getAdapterPosition()));
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setDataToHistory(CompletedDetail completedDetail) {
            tv_add_review.setVisibility(View.VISIBLE);
            tv_reschdule.setVisibility(View.GONE);
            tv_view_booking.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.GONE);
            tv_status_value.setText("COMPLETED");
            tv_confirm_id.setText("Booking Id - " + completedDetail.getBookingId());
            tv_saloon.setText(completedDetail.getSalonName());
            tv_booking_price_value.setText(completedDetail.getTotalPrice());
            tv_time.setText(completedDetail.getBookingTime());
            tv_date.setText(completedDetail.getServiceDate());
        }

        void setDataToupComing(UpcomingDetail upcomingDetail) {
            tv_add_review.setVisibility(View.GONE);
            tv_cancel.setVisibility(View.VISIBLE);
            tv_reschdule.setVisibility(View.VISIBLE);
            tv_view_booking.setVisibility(View.VISIBLE);
            tv_status_value.setText("UPCOMING");
            tv_confirm_id.setText("Booking Id - " + upcomingDetail.getBookingId());
            tv_saloon.setText(upcomingDetail.getSalonName());
            tv_booking_price_value.setText(upcomingDetail.getTotalPrice());
            tv_time.setText(upcomingDetail.getServiceTime());
            tv_date.setText(upcomingDetail.getServiceDate());
        }
    }

    public interface ItemListener {
        void onAddReviewClick(CompletedDetail completedDetail);

        void onCancelClick(UpcomingDetail upcomingDetail);

        void onReschduleClick(UpcomingDetail upcomingDetail);

        void onViewBookingClick(UpcomingDetail upcomingDetail);
    }
}
