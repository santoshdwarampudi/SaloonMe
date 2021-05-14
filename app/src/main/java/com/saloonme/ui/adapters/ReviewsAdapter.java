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
import com.saloonme.model.response.UserReviewsResponseData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private Context context;
    private ItemListener itemListener;
    private List<SaloonReviewResponseData> saloonReviewResponseDataList;
    private List<UserReviewsResponseData> userReviewsResponseDataList;
    private boolean isUserReview;

    public ReviewsAdapter(Context context, ItemListener itemListener, boolean isUserReview) {
        this.context = context;
        this.itemListener = itemListener;
        this.isUserReview = isUserReview;
    }

    public void setData(List<SaloonReviewResponseData> saloonReviewResponseDataList) {
        this.saloonReviewResponseDataList = saloonReviewResponseDataList;
        notifyDataSetChanged();
    }

    public void setDataToUserReviews(List<UserReviewsResponseData> userReviewsResponseDataList) {
        this.userReviewsResponseDataList = userReviewsResponseDataList;
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
        if (!isUserReview)
            holder.Bind(saloonReviewResponseDataList.get(position));
        else
            holder.BindUserReviews(userReviewsResponseDataList.get(position));
    }

    @Override
    public int getItemCount() {
        if (!isUserReview)
            return saloonReviewResponseDataList != null ? saloonReviewResponseDataList.size() : 0;
        else
            return userReviewsResponseDataList != null ? userReviewsResponseDataList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_reviewcount)
        TextView tv_reviewcount;
        @BindView(R.id.tv_description)
        TextView tv_description;
        @BindView(R.id.tv_follow)
        TextView tv_follow;
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
            if (isUserReview) {
                tv_follow.setVisibility(View.GONE);
                tv_reviewcount.setVisibility(View.GONE);
            } else {
                tv_follow.setVisibility(View.VISIBLE);
                tv_reviewcount.setVisibility(View.VISIBLE);
            }
        }

        public void BindUserReviews(UserReviewsResponseData userReviewsResponseData) {
            tv_description.setText(userReviewsResponseData.getReviewComments());
            tv_reviewcount.setText("Rating :" + userReviewsResponseData.getRating());
            if (isUserReview) {
                tv_follow.setVisibility(View.GONE);
                tv_reviewcount.setVisibility(View.GONE);
            } else {
                tv_follow.setVisibility(View.VISIBLE);
                tv_reviewcount.setVisibility(View.VISIBLE);
            }
        }
    }

    public interface ItemListener {

    }
}
