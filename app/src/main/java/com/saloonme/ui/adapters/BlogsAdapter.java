package com.saloonme.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saloonme.R;
import com.saloonme.model.response.BlogDetailsResponseData;
import com.saloonme.model.response.PromotionsResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.ViewHolder> {
    private Context context;
    private List<BlogDetailsResponseData> blogDetailsResponseDataList;
    private BlogItemListener blogItemListener;

    public BlogsAdapter(Context context, List<BlogDetailsResponseData> blogDetailsResponseDataList, BlogItemListener blogItemListener) {
        this.context = context;
        this.blogDetailsResponseDataList = blogDetailsResponseDataList;
        this.blogItemListener = blogItemListener;
    }
    public void setData(List<BlogDetailsResponseData> blogDetailsResponseDataList){
        this.blogDetailsResponseDataList=blogDetailsResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(blogDetailsResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return blogDetailsResponseDataList != null ? blogDetailsResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_saloon)
        ImageView iv_saloon;
        @BindView(R.id.tv_deals)
        TextView tv_deals;
        @BindView(R.id.tv_offer)
        TextView tv_offer;


       /* @OnClick(R.id.card_menu)
        void onTrendingClick() {
            if (ite != null) {
                //itemListener.onTrendingClick();
            }
        }*/

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(BlogDetailsResponseData blogDetailsResponseData) {
            tv_deals.setText(blogDetailsResponseData.getBlogTitle());
            tv_offer.setText(blogDetailsResponseData.getBlogDes());
            Glide.with(context).load(blogDetailsResponseData.getBlogImg())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(iv_saloon);
        }
    }

    public interface BlogItemListener {
        void onBlogClick();
    }
}
