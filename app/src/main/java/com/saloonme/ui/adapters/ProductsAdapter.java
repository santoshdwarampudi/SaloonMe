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
import com.saloonme.model.response.BookingItemsResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<BookingItemsResponseData> bookingItemsResponseDataList;

    public ProductsAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<BookingItemsResponseData> bookingItemsResponseDataList) {
        this.bookingItemsResponseDataList = bookingItemsResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(bookingItemsResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookingItemsResponseDataList != null ? bookingItemsResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_product)
        ImageView iv_product;
        @BindView(R.id.tv_product)
        TextView tv_product;
        @BindView(R.id.tv_quantity)
        TextView tv_quantity;
        @BindView(R.id.tv_price)
        TextView tv_price;

        @OnClick(R.id.tv_remove_cart)
        void onRemoveClick() {
            if (itemListener != null) {
                itemListener.onRemoveClick(bookingItemsResponseDataList.get(getAdapterPosition()));
            }
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(BookingItemsResponseData bookingItemsResponseData) {
            tv_product.setText(bookingItemsResponseData.getServiceName());
            tv_price.setText("Price :" + bookingItemsResponseData.getServicePrice());
        }
    }

    public interface ItemListener {
        void onRemoveClick(BookingItemsResponseData bookingItemsResponseData);
    }
}
