package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.model.response.SaloonSubServiceResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ViewHolder> {
    private Context context;
    private List<SaloonSubServiceResponseData> saloonSubServiceResponseDataList;

    public CategoryFilterAdapter(Context context) {
        this.context = context;

    }

    public void setData(List<SaloonSubServiceResponseData> saloonSubServiceResponseDataList) {
        this.saloonSubServiceResponseDataList = saloonSubServiceResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_fiter_item, parent, false);
        return new CategoryFilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryFilterAdapter.ViewHolder holder, int position) {
        holder.setData(saloonSubServiceResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonSubServiceResponseDataList != null ? saloonSubServiceResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemCb)
        CheckBox itemCb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(SaloonSubServiceResponseData saloonSubServiceResponseData) {
            itemCb.setText(saloonSubServiceResponseData.getServiceName());
        }
    }

}
