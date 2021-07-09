package com.saloonme.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;
import com.saloonme.model.response.SaloonListResponseData;
import com.saloonme.model.response.SearchResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<SaloonListResponseData> searchResponseDataList;

    public SearchAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void updateSearchResponse(List<SaloonListResponseData> searchResponseDataList) {
        this.searchResponseDataList = searchResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(searchResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResponseDataList != null ? searchResponseDataList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.tv_searchItem)
        TextView tv_searchItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void Bind(SaloonListResponseData searchResponseData) {
            tv_searchItem.setText(searchResponseData.getStoreName());
            tv_searchItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemListener!=null){
                        itemListener.onSearchItemClicked(searchResponseData);
                    }
                }
            });
        }
    }

    public interface ItemListener {
        void onSearchItemClicked(SaloonListResponseData searchResponseData);
    }
}
