package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.model.response.UserOrderDetailsList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context context;
    private boolean isHistory;
    private List<UserOrderDetailsList> userOrderDetailsLists;

    public OrdersAdapter(Context context ) {
        this.context = context;
    }


    public void setDataToOrders(List<UserOrderDetailsList> userOrderDetailsLists) {
        this.userOrderDetailsLists = userOrderDetailsLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orders_model, parent, false);
        return new OrdersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {

            holder.setDataToOrders(userOrderDetailsLists.get(position));
    }

    @Override
    public int getItemCount() {

            return userOrderDetailsLists != null ? userOrderDetailsLists.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date_and_time)
        TextView tv_date_and_time;

        @BindView(R.id.tv_booking_id)
        TextView tv_booking_id;

        @BindView(R.id.tv_payment_status)
        TextView tv_payment_status;
        @BindView(R.id.tv_order_status)
        TextView tv_order_status;


        @BindView(R.id.tv_view_booking)
        TextView tv_view_booking;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setDataToOrders(UserOrderDetailsList completedDetail) {
            tv_date_and_time.setText(completedDetail.getOrderDateTime());
            tv_booking_id.setText(completedDetail.getOrderId());
            tv_payment_status.setText(completedDetail.getPaymentStatus());
            tv_order_status.setText(completedDetail.getOrderStatus());

        }


    }
}
