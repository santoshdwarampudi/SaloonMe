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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private Context mContext;


    public FeedAdapter(Context mContext) {
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_feed, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userNameTv)
        TextView userNameTv;
        @BindView(R.id.isLikedIv)
        ImageView isLikedIv;


        @OnClick(R.id.isLikedIv)
        void onLikeClick() {
        }
        @OnClick(R.id.isCommentIv)
        void onCommentClick() {

        }
        @OnClick(R.id.isForwardIv)
        void onForwardClick() {

        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

