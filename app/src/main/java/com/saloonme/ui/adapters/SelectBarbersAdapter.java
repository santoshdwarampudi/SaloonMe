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
import com.saloonme.model.response.ExpertsListResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectBarbersAdapter extends RecyclerView.Adapter<SelectBarbersAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<ExpertsListResponseData> expertsListResponseDataList;

    public SelectBarbersAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<ExpertsListResponseData> expertsListResponseDataList) {
        this.expertsListResponseDataList = expertsListResponseDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.barber_select_model, parent, false);
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
        @BindView(R.id.iv_barber)
        ImageView iv_barber;
        @BindView(R.id.tv_barberName)
        TextView tv_barberName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(ExpertsListResponseData expertsListResponseData) {

        }
    }

    public interface ItemListener {

    }
}
