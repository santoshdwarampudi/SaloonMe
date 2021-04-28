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
import com.saloonme.interfaces.APIConstants;
import com.saloonme.model.response.SaloonDetailsImageResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private Context context;
    private ItemListener itemListener;
    private List<SaloonDetailsImageResponseData> saloonDetailsImageResponseDataList;

    public PhotosAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<SaloonDetailsImageResponseData> saloonDetailsImageResponseDataList) {
        this.saloonDetailsImageResponseDataList = saloonDetailsImageResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photos_model, parent, false);
        return new ViewHolder(view);
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

        void setData(SaloonDetailsImageResponseData saloonDetailsImageResponseData) {
            Glide.with(context).load(APIConstants.IMAGE_BASE_URL +
                    saloonDetailsImageResponseData.getStoreImg())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
        }
    }

    public interface ItemListener {

    }
}
