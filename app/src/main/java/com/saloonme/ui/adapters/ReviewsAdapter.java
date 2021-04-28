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
import com.saloonme.model.response.SaloonReviewResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private Context context;
    private ItemListener itemListener;
    private List<SaloonReviewResponseData> saloonReviewResponseDataList;

    public ReviewsAdapter(Context context, ItemListener itemListener) {
        this.context = context;
        this.itemListener = itemListener;
    }

    public void setData(List<SaloonReviewResponseData> saloonReviewResponseDataList) {
        this.saloonReviewResponseDataList = saloonReviewResponseDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reviews_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(saloonReviewResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return saloonReviewResponseDataList != null ? saloonReviewResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_reviewcount)
        TextView tv_reviewcount;
        @BindView(R.id.tv_description)
        TextView tv_description;
        @BindView(R.id.iv_user)
        ImageView iv_user;

        @OnClick(R.id.tv_follow)
        void onFollowClick() {

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void Bind(SaloonReviewResponseData saloonReviewResponseData) {
            tv_description.setText(saloonReviewResponseData.getReviewComments());
            tv_reviewcount.setText("Rating :" + saloonReviewResponseData.getRating());
        }
    }

    public interface ItemListener {

    }
}
