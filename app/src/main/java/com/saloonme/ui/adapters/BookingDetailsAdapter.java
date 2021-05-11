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
import com.saloonme.model.response.BookingServices;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingDetailsAdapter extends RecyclerView.Adapter<BookingDetailsAdapter.ViewHolder> {
    private Context context;
    private List<BookingServices> bookingServicesList;

    public BookingDetailsAdapter(Context context, List<BookingServices> bookingServicesList) {
        this.context = context;
        this.bookingServicesList = bookingServicesList;
    }

    public void setData(List<BookingServices> bookingServicesList) {
        this.bookingServicesList = bookingServicesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.service_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(bookingServicesList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookingServicesList != null ? bookingServicesList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.service_total)
        TextView service_total;
        @BindView(R.id.service_duration)
        TextView service_duration;
        @BindView(R.id.service_price)
        TextView service_price;
        @BindView(R.id.service_name)
        TextView service_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(BookingServices bookingServices) {
            service_name.setText(bookingServices.getServiceName());
            service_price.setText("Rs " + bookingServices.getServicePrice());
            service_duration.setText(bookingServices.getServiceDuration());
            service_total.setText("Rs " + bookingServices.getServicePrice());
        }
    }
}
