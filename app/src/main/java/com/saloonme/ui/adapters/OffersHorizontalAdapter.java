package com.saloonme.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saloonme.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OffersHorizontalAdapter extends RecyclerView.Adapter<OffersHorizontalAdapter.ViewHolder> {
    private Context context;
    //private List<OffersResponseData> offersResponseDataList;
    private String imagePath;
    private ItemListener itemListener;

    public OffersHorizontalAdapter(Context context, String imagePath, ItemListener itemListener) {
        this.context = context;
        this.imagePath = imagePath;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.productimagesmodel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //  holder.setData(offersResponseDataList.get(position));
        holder.setData();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    /*public void setData(List<OffersResponseData > offersResponseDataList, String imagePath) {
        this.offersResponseDataList = offersResponseDataList;
        this.imagePath = imagePath;
        notifyDataSetChanged();
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView iv_banner;

        @OnClick(R.id.iv_banner)
        void onBannerClick() {
            if (itemListener != null)
                itemListener.onHorizontalItemClick();
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
           /* Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            iv_banner.setBackgroundColor(color);*/
        }

       /* public void setData(OffersResponseData menuProductsImagesResponse) {
            if (menuProductsImagesResponse != null && imagePath != null && !imagePath.isEmpty()) {
                String image = imagePath + "/" + menuProductsImagesResponse.getImage();
                Glide.with(context).load(image)
                        .apply(new RequestOptions().placeholder(R.drawable.ic_horizontalbanner).error(R.drawable.ic_horizontalbanner)
                                .diskCacheStrategy(DiskCacheStrategy.ALL))
                        .into(iv_banner);

            }
        }*/
    }

    public interface ItemListener {
        void onHorizontalItemClick();
    }
}
