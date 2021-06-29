package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.model.response.SaloonDetailsImageResponseData;
import com.saloonme.model.response.UserFeedPhotsList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFeedPhotosAdapter extends RecyclerView.Adapter<UserFeedPhotosAdapter.ViewHolder> {
    private Context context;

    private List<UserFeedPhotsList> saloonDetailsImageResponseDataList;

    public UserFeedPhotosAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<UserFeedPhotsList> saloonDetailsImageResponseDataList) {
        this.saloonDetailsImageResponseDataList = saloonDetailsImageResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserFeedPhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photos_model, parent, false);
        return new UserFeedPhotosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(saloonDetailsImageResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonDetailsImageResponseDataList != null ?
                saloonDetailsImageResponseDataList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setData(UserFeedPhotsList saloonDetailsImageResponseData) {
            Glide.with(context).load(
                    saloonDetailsImageResponseData.getFeedImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
        }
    }

    public interface ItemListener {

    }

}
